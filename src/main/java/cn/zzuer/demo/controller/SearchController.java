package cn.zzuer.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.zzuer.demo.pojo.PageResult;
import cn.zzuer.demo.pojo.RequestParams;
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

    @GetMapping("/{caseName}")
    public PageResult getByCaseName(@PathVariable String caseName) throws IOException {
        return searchService.search(caseName);
    }

}
