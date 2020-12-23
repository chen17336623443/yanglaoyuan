package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Checkin;
import com.yanglaoyuan.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: yanglaoyuan
 * @description: 入住缴费controller
 * @author: chenxing
 * @create: 2020-12-21 21:33
 **/
@RestController
@RequestMapping("/checkin")
@CrossOrigin
public class CheckinController {

    @Autowired
    CheckinService cs;

    @RequestMapping("/pager")
    public PageInfo<Checkin> pager(Integer no, @RequestParam(required = false) Integer size){
        Integer pageSize = 5;
        if(size!=null){
            pageSize = size;
        }
        return cs.selectBypager(no,size);
    }

}
