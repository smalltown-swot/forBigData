package cn.zzuer.demo.service;

import cn.zzuer.demo.pojo.Law;
import cn.zzuer.demo.pojo.LawDoc;
import cn.zzuer.demo.pojo.PageResult;
import cn.zzuer.demo.pojo.RequestParams;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;

public interface ISearchService extends IService<Law> {

    PageResult search(RequestParams params) throws IOException;
}
