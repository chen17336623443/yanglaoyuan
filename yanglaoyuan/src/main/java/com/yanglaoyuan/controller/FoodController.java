package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.config.MyResult;
import com.yanglaoyuan.pojo.Food;
import com.yanglaoyuan.pojo.Foodtype;
import com.yanglaoyuan.service.FoodServices;
import com.yanglaoyuan.service.FoodtypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 食物Controller
 * @author: YeJin Tan
 * @create: 2020-12-21 16:55
 */
@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    FoodServices fs;
    @Autowired
    FoodtypeServices fts;

    //查询所有食物

    /**
     *@Description 方法是:selectFoodAll
     *@Param 参数是:[]
     *@Return 返回类型是:com.yanglaoyuan.pojo.MyResult
     *@Author tanyejin
     *@Date 2020-12-21 17:02
     */
    @RequestMapping ("/all")
    public PageInfo<Food> selectFoodAll(String pageNo,String pageSize){
        return fs.selectFoodAll(new Integer(pageNo), new Integer(pageSize));
    }

    @RequestMapping("/alltype")
    public List<Foodtype> selectFoodtypeAll(){
        return fts.selectFoodtypeAll();
    }
}
