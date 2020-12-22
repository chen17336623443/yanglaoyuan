package com.yanglaoyuan.controller;

import com.yanglaoyuan.config.MyResult;
import com.yanglaoyuan.pojo.Oldman;
import com.yanglaoyuan.service.OldmanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ChenZhiPeng
 * @date 2020/12/21 16:25
 */
@RestController
@RequestMapping("/oldman")
public class OldmanController {
    @Autowired
    OldmanServices oldmanServices;

    @RequestMapping("/selectAll")
    public List<Oldman> selectAll(){
        return oldmanServices.selectAll();
    }

    /*查询未外出的老人*/
    @PostMapping("/selectNotOut")
    public List<Oldman> selectNotOut(){
        return oldmanServices.selectNotOut();
    }
}
