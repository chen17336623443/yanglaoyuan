package com.yanglaoyuan.controller;

import com.yanglaoyuan.pojo.Thenursingleveldetails;
import com.yanglaoyuan.service.NursingServices;
import com.yanglaoyuan.service.ThenursinglevelService;
import com.yanglaoyuan.service.ThenursingleveldetailsServicrs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: JiaWei Liu
 * @create: 2020-12-24 01:34
 */
@RestController
@RequestMapping("/thvt")
public class ThenursingleveldetailsController {
    @Autowired
    ThenursingleveldetailsServicrs ts;
    @Autowired
    NursingServices ns;
    @Autowired
    ThenursinglevelService tvs;


    @PostMapping("/add/{tid}/{nuid}")
    public String add(@RequestBody Thenursingleveldetails t, @PathVariable("tid")Integer tid,@PathVariable("nuid")Integer nuid){
        t.setNursingByNuId(ns.selectid(nuid));
        t.setThenursinglevelByTslId(tvs.selectbyid(tid));
        return ts.add(t)==1?"新增级别护理成功！":"";
    }
}
