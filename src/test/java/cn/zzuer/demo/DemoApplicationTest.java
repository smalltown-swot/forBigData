package cn.zzuer.demo;


import cn.zzuer.demo.pojo.Law;
import cn.zzuer.demo.pojo.LawDoc;
import cn.zzuer.demo.service.ISearchService;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class DemoApplicationTest {
    @Autowired
    private ISearchService searchService;
    private RestHighLevelClient client;

    @Test
    void testAddDocument() throws IOException {
        //根据id查询数据
        Law law = searchService.getById("京1");
        LawDoc lawDoc = new LawDoc(law);
        //准备request对象
        IndexRequest request = new IndexRequest("law").id(law.getCaseId());
        //发送json对象
        request.source(JSON.toJSONString(lawDoc), XContentType.JSON);
        //发送请求
        client.index(request, RequestOptions.DEFAULT);

    }
}
