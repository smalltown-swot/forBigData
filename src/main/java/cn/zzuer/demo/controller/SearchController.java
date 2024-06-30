package cn.zzuer.demo.controller;


import cn.zzuer.demo.pojo.Law;
import cn.zzuer.demo.pojo.LawDoc;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.zzuer.demo.pojo.PageResult;
import cn.zzuer.demo.pojo.RequestParams;
import cn.zzuer.demo.rest.NoFinalString;
import cn.zzuer.demo.service.ISearchService;

import java.io.IOException;


@RestController
@RequestMapping("/law")
public class SearchController {
    @Autowired
    private ISearchService searchService;

    @PostMapping("/list")
    public PageResult search(RequestParams params) throws IOException {
        //System.out.println("++++++++++++" + params);
        return searchService.search(params);
    }

}
