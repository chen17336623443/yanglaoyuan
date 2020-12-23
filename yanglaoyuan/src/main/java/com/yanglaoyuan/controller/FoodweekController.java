package com.yanglaoyuan.controller;

import com.yanglaoyuan.pojo.Foodweek;
import com.yanglaoyuan.service.FoodweekServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 食物周次Controller
 * @author: YeJin Tan
 * @create: 2020-12-23 11:16
 */
@RestController
@RequestMapping("/foodweek")
public class FoodweekController {
    @Autowired
    FoodweekServices fws;

    //根据套餐id查询食物周次
    @RequestMapping("/allByFpid")
    public List<Foodweek> selectFoodweekByFpId(Integer fp_id){
        return fws.selectFoodweekByFpId(fp_id);
    }
}
