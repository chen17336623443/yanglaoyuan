package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Foodorder;
import com.yanglaoyuan.pojo.Foodorderdetails;
import com.yanglaoyuan.service.FoodServices;
import com.yanglaoyuan.service.FoodorderServices;
import com.yanglaoyuan.service.FoodorderdetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: yanglaoyuan
 * @description: 点餐Controller
 * @author: YeJin Tan
 * @create: 2020-12-23 14:35
 */
@RestController
@RequestMapping("/foodorder")
public class FoodorderController {
    @Autowired
    FoodorderServices fos;
    @Autowired
    FoodServices fs;
    @Autowired
    FoodorderdetailsServices fods;

    //新增点餐
    @RequestMapping("/insert")
    /**
     *@Description 方法是:insertFoodorder
     *@Param 参数是:[forder]
     *@Return 返回类型是:java.lang.Integer
     *@Author tanyejin
     *@Date 2020-12-23 14:37
     */
    public Integer insertFoodorder(@RequestBody Foodorder forder){
        //先新增点餐表
        int resultA=fos.insertFoodorder(forder);
        if(resultA>0){
            //再把新增的点餐主键加到点餐详情中去
            for (Foodorderdetails fod : forder.getFoodorderdetails()) {
                fod.setFoodorder(forder);
            }
            //新增点餐详情表
            fods.insertFoodorderdetails(forder.getFoodorderdetails());
        }
        return resultA;
    }
    //查询所有的点餐信息
    @RequestMapping("/all")
    /**
     *@Description 方法是:selectFoodorderAll
     *@Param 参数是:[pageNo, pageSize]
     *@Return 返回类型是:com.github.pagehelper.PageInfo<com.yanglaoyuan.pojo.Foodorder>
     *@Author tanyejin
     *@Date 2020-12-24 2:02
     */
    public PageInfo<Foodorder> selectFoodorderAll(@RequestParam("pageNo")Integer pageNo,@RequestParam("pageSize")Integer pageSize){
        return fos.selectFoodorderAll(pageNo, pageSize);
    }

    //送餐改变状态
    @RequestMapping("/updateState")
    /**
     *@Description 方法是:updateFoodorderState
     *@Param 参数是:[fo_id]
     *@Return 返回类型是:java.lang.Integer
     *@Author tanyejin
     *@Date 2020-12-24 2:22
     */
    public Integer updateFoodorderState(Integer fo_id){
        return fos.updateFoodorderState(fo_id);
    }
}
