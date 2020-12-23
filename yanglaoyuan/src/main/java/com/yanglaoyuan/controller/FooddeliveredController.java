package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Fooddelivered;
import com.yanglaoyuan.service.FooddeliveredServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 送餐Controller
 * @author: YeJin Tan
 * @create: 2020-12-24 02:35
 */
@RestController
@RequestMapping("/fooddeli")
public class FooddeliveredController {
    @Autowired
    FooddeliveredServices fds;

    //送餐新增
    @RequestMapping("/insert")
    /**
     *@Description 方法是:insertFooddelivered
     *@Param 参数是:[fdel]
     *@Return 返回类型是:java.lang.Integer
     *@Author tanyejin
     *@Date 2020-12-24 2:37
     */
    public Integer insertFooddelivered(@RequestBody Fooddelivered fdel){
        return fds.insertFooddelivered(fdel);
    }

    //查询所有送餐记录
    @RequestMapping("/all")
    /**
     *@Description 方法是:selectFooddeliveredAll
     *@Param 参数是:[pageNo, pageSize]
     *@Return 返回类型是:com.github.pagehelper.PageInfo<com.yanglaoyuan.pojo.Fooddelivered>
     *@Author tanyejin
     *@Date 2020-12-24 2:40
     */
    public PageInfo<Fooddelivered> selectFooddeliveredAll(@RequestParam("pageNo")Integer pageNo,@RequestParam("pageSize")Integer pageSize){
        return fds.selectFooddeliveredAll(pageNo, pageSize);
    }
}
