package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Arrears;
import com.yanglaoyuan.service.ArrearsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @program: yanglaoyuan
 * @description: 欠费管理controller
 * @author: chenxing
 * @create: 2020-12-23 16:16
 **/
@RestController
@RequestMapping("/arrears")
@CrossOrigin
public class ArrearsController {

    @Autowired
    ArrearsService as;

    @RequestMapping("/pager")
    public PageInfo<Arrears> selectBypager(@RequestParam("no") Integer no, @RequestParam(value="size",required = false) Integer size){
        Integer pageSize = 5;
        if(size!=null){
            pageSize = size;
        }
        return as.selectBypager(no,size);
    }

    @RequestMapping("/pagers")
    public PageInfo<Arrears> selectBypagers(@RequestParam("no") Integer no, @RequestParam("size") Integer size,
                                            @RequestParam("name") String name, @RequestParam("sfz") String sfz,
                                            @RequestParam("cwh") String cwh, @RequestParam("qfje") String qfje,
                                            @RequestParam("kdate") String kdate, @RequestParam("jdate") String jdate,
                                            @RequestParam("zt") String zt){
        Integer pageSize = 5;
        if(size!=null){
            pageSize = size;
        }
        return as.selectBypagers(no, size, name, sfz, cwh, qfje, kdate, jdate, zt);
    }

}
