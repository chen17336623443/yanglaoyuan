package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Checkins;
import com.yanglaoyuan.pojo.Consult;
import com.yanglaoyuan.pojo.User;
import com.yanglaoyuan.service.ConsultServices;
import com.yanglaoyuan.service.UserServices;
import com.yanglaoyuan.service.checkinsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("/checkins")
public class CheckinsController {


    @Autowired
    checkinsServices services;
@RequestMapping("all")
    public PageInfo<Checkins> consultall(@RequestBody Checkins checkins, @RequestParam("no") Integer pageNum , @RequestParam("size") Integer pageSize, @RequestParam("begin") String begin, @RequestParam("end") String end,
                                         @RequestParam(value = "tomName",required = false) String tomName){
        Integer pageNo = (pageNum!=null && pageNum>=1)?pageNum:1;
        Integer size = (pageSize!=null)?pageSize:10;
        return services.consultall(pageNo,size,checkins,begin,end,tomName);
    }

    /*新增登记入住*/
    @RequestMapping("insertcheckins")
    public Integer insertcheckins(@RequestBody Checkins d){
        System.out.println(d.getCheckinend()+"-------------"+d.getCheckinbegin());
        d.setCheckinnew(new Timestamp(System.currentTimeMillis()));
        return  services.insertcheckins(d);
    }

    /*删除登记入住信息*/
    @RequestMapping("delete")
    public  Integer delete(Integer id){
        return  services.delete(id);
    };

}
