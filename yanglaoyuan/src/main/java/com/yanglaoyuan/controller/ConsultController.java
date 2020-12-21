package com.yanglaoyuan.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Consult;
import com.yanglaoyuan.pojo.MyResult;
import com.yanglaoyuan.pojo.User;
import com.yanglaoyuan.pojo.Yanzhen;
import com.yanglaoyuan.service.ConsultServices;
import com.yanglaoyuan.service.UserServices;
import com.yanglaoyuan.service.yanzhenServices;
import com.yanglaoyuan.utils.ResultUtil;
import com.yanglaoyuan.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consult")
public class ConsultController {
    @Autowired
    ConsultServices services;
@RequestMapping("all")
    public PageInfo<Consult> consultall(@RequestParam("no") Integer pageNum ,@RequestParam("size") Integer pageSize){
        Integer pageNo = (pageNum!=null && pageNum>=1)?pageNum:1;
        Integer size = (pageSize!=null)?pageSize:10;
        return services.consultall(pageNo,size);
    }

}
