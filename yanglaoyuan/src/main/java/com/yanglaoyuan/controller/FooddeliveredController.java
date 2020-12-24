package com.yanglaoyuan.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Fooddelivered;
import com.yanglaoyuan.service.FooddeliveredServices;
import com.yanglaoyuan.service.FoodorderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
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
    @Autowired
    FoodorderServices fos;

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
        //先新增送餐数据
        fds.insertFooddelivered(fdel);
        //再把点餐状态修改为已送餐
        return fos.updateFoodorderState(fdel.getFoodorderByFoId().getFoId());
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

    //组合查询送餐记录
    @RequestMapping("/groupQuery")
    /**
     *@Description 方法是:gruopQueryFooddelivered
     *@Param 参数是:[fdAddress, startTime, endTime, pageNo, pageSize]
     *@Return 返回类型是:com.github.pagehelper.PageInfo<com.yanglaoyuan.pojo.Fooddelivered>
     *@Author tanyejin
     *@Date 2020-12-24 13:22
     */
    public PageInfo<Fooddelivered> gruopQueryFooddelivered(@RequestParam(value = "fdAddress",required = false) String fdAddress, @RequestParam(value = "startTime",required = false) Timestamp startTime, 
                                                           @RequestParam(value = "endTime",required = false) Timestamp endTime, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        PageInfo<Fooddelivered> pageInfo=fds.gruopQueryFooddelivered(fdAddress, startTime, endTime, pageNo, pageSize);
        System.out.println(pageInfo.getList().size());
        return pageInfo;
    }
}
