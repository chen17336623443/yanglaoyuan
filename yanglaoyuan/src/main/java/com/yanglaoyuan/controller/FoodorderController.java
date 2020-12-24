package com.yanglaoyuan.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Foodorder;
import com.yanglaoyuan.pojo.Foodorderdetails;
import com.yanglaoyuan.pojo.Oldman;
import com.yanglaoyuan.service.FoodServices;
import com.yanglaoyuan.service.FoodorderServices;
import com.yanglaoyuan.service.FoodorderdetailsServices;
import com.yanglaoyuan.service.OldmanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

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
    FoodorderServices fos;//点餐Service
    @Autowired
    FoodServices fs;//食物Service
    @Autowired
    FoodorderdetailsServices fods;//点餐详情Service
    @Autowired
    OldmanServices os;//老人Service

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

    //查询所有点过餐的老人
    @RequestMapping("/haveFoid")
    /**
     *@Description 方法是:selectOldmanByHaveFoid
     *@Param 参数是:[]
     *@Return 返回类型是:java.util.List<com.yanglaoyuan.pojo.Oldman>
     *@Author tanyejin
     *@Date 2020-12-24 14:08
     */
    public List<Oldman> selectOldmanByHaveFoid(){
        return os.selectOldmanByHaveFoid();
    }

    //组合查询点餐信息
    @RequestMapping("/groupQuery")
    /**
     *@Description 方法是:groupQuerytFoodorder
     *@Param 参数是:[om_id, fo_meals, startTime, endTime, pageNo, pageSize]
     *@Return 返回类型是:com.github.pagehelper.PageInfo<com.yanglaoyuan.pojo.Foodorder>
     *@Author tanyejin
     *@Date 2020-12-24 14:20
     */
    public PageInfo<Foodorder> groupQuerytFoodorder(@RequestParam(value = "om_id",required = false) Integer om_id, @RequestParam(value = "fo_meals",required = false) String fo_meals,
                                                    @RequestParam(value = "startTime",required = false) Timestamp startTime, @RequestParam(value = "endTime",required = false)Timestamp endTime, 
                                                    @RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize){
        PageInfo<Foodorder> pageInfo=fos.groupQuerytFoodorder(om_id, fo_meals, startTime, endTime, pageNo, pageSize);
        System.out.println(pageInfo.getList().size());
        return pageInfo;
    }
}
