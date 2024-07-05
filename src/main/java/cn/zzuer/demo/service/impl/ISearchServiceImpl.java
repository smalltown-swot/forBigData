package cn.zzuer.demo.service.impl;

import cn.zzuer.demo.mapper.LawMapper;
import cn.zzuer.demo.pojo.Law;
import cn.zzuer.demo.pojo.LawDoc;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.zzuer.demo.pojo.PageResult;
import cn.zzuer.demo.pojo.RequestParams;
import cn.zzuer.demo.service.ISearchService;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ISearchServiceImpl extends ServiceImpl<LawMapper, Law> implements ISearchService {
    @Autowired
    private RestHighLevelClient client;

    @Override
    public PageResult search(RequestParams params){
            //1. 准备request，这个是哪个数据库下的东西
            SearchRequest request = new SearchRequest("law");
            //2. 准备DSL
            //2.1 关键字搜搜
            String key = params.getKey();
            System.out.println(key);
            //为空全查询
            if (key==null||key.equals("")) {
                request.source().query(QueryBuilders.matchAllQuery());
            }else {
                request.source().query(QueryBuilders.matchQuery("all",key));
            }

            //2.2 分页
            //int page = params.getPage();
            //int size = params.getSize();
            //先不管分页试试
            //request.source().from((page-1)*size).size(size);

            //3. 发送请求
            SearchResponse response = null;
        try {
            response = client.search(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            System.out.println("————————————"+key+"——————————————————————");
            throw new RuntimeException(e);
        }
        //4. 解析响应
        System.out.println(key);
        return handleResponse(response);

    }

    @Override
    public PageResult search(String caseName) throws IOException {

        SearchRequest request = new SearchRequest("law");
        request.source()
                //字段名，搜索内容
                .highlighter(new HighlightBuilder().field("caseName")
                        .requireFieldMatch(false))
                .query(QueryBuilders.matchQuery("caseName",caseName))
                .size(1000);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return handleResponse(response);
    }

    @Override
    public PageResult searchAll(String key) throws IOException {
        SearchRequest request = new SearchRequest("law");
        request.source()
                .highlighter(new HighlightBuilder().field("text")
                        .requireFieldMatch(false))
                .query(QueryBuilders.matchQuery("text",key))
                .size(1000);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return handleResponse(response);
    }

    @Override
    public PageResult getByCaseId(String caseId) throws IOException {
        SearchRequest request = new SearchRequest("law");
        System.out.println("__"+ caseId +"__");
        request.source()
                .highlighter(new HighlightBuilder().field("caseId")
                        .requireFieldMatch(false))
                .query(QueryBuilders.matchQuery("caseId", caseId))
                .size(1000);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println(response);
        return handleResponse(response);
    }

    //高亮回来debug一下，然后，那啥，精准搜索带上去
    //基础功能做做就行了


    //高亮结果解析
    private PageResult handleResponse(SearchResponse response) {
        //解析
        //1. 拿到hits
        SearchHits searchHits = response.getHits();
        //2. 总量
        long total = searchHits.getTotalHits().value;
        //测试语句
        System.out.println("共搜索到"+total+"条数据");
        //3. 拿到文档数组
        SearchHit[] hits = searchHits.getHits();
        List<LawDoc> laws = new ArrayList<>();
        //4. 遍历数组并返回结果
        for (SearchHit hit : hits) {
            //获取相应的source，source是源文档，不自带高亮
            String json = hit.getSourceAsString();
            //反序列化
            LawDoc lawDoc = JSON.parseObject(json, LawDoc.class);
            //获取高亮结果
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            //caseName高亮
            if (!CollectionUtils.isEmpty(highlightFields)) {
                //根据字段名获取高亮结果，这个key是字段名，和源文档的字段名相对应
                HighlightField highlightField = highlightFields.get("caseName");
                if (highlightField!=null) {
                    //获取高亮值
                    String name = highlightField.getFragments()[0].string();
                    //覆盖高亮结果
                    lawDoc.setCaseName(name);
                }
                HighlightField textField = highlightFields.get("text");
                if (textField!=null) {
                    //获取高亮值
                    String text = textField.getFragments()[0].string();
                    lawDoc.setText(text);
                }
            }
            laws.add(lawDoc);
            //输出检验
            System.out.println(lawDoc);
        }
        //封装数据并返回
        return new PageResult(total,laws);
    }
}
