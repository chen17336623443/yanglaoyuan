package com.yanglaoyuan.controller;

import com.yanglaoyuan.pojo.Foodpackge;
import com.yanglaoyuan.service.FoodpackgeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 食物套餐Controller
 * @author: YeJin Tan
 * @create: 2020-12-22 21:19
 */
@RestController
@RequestMapping("/foodpackge")
public class FoodpackgeController {
    @Autowired
    FoodpackgeServices fps;

    //查询所有套餐
    @RequestMapping("/all")
    /**
     *@Description 方法是:selectFoodpackgeAll
     *@Param 参数是:[]
     *@Return 返回类型是:java.util.List<com.yanglaoyuan.pojo.Foodpackge>
     *@Author tanyejin
     *@Date 2020-12-22 21:22
     */
    public List<Foodpackge> selectFoodpackgeAll(){
        return fps.selectFoodpackgeAll();
    }
    //新增套餐
    @RequestMapping("/insert")
    /**
     *@Description 方法是:insertFoodpackge
     *@Param 参数是:[fpackge]
     *@Return 返回类型是:java.lang.Integer
     *@Author tanyejin
     *@Date 2020-12-22 21:44
     */
    public Integer insertFoodpackge(@ModelAttribute Foodpackge fpackge){
        return fps.insertFoodpackge(fpackge);
    }

    //修改套餐
    @RequestMapping("/update")
    /**
     *@Description 方法是:updateFoodpackge
     *@Param 参数是:[fpackge]
     *@Return 返回类型是:java.lang.Integer
     *@Author tanyejin
     *@Date 2020-12-22 21:45
     */
    public Integer updateFoodpackge(@ModelAttribute Foodpackge fpackge){
        return fps.updateFoodpackge(fpackge);
    }

    //给套餐修改图片
    @RequestMapping("/updateImg")
    /**
     *@Description 方法是:updateFoodpackgeImg
     *@Param 参数是:[fp_id, file]
     *@Return 返回类型是:java.lang.Integer
     *@Author tanyejin
     *@Date 2020-12-23 11:09
     */
    public Integer updateFoodpackgeImg(@RequestParam("fp_id") Integer fp_id, MultipartFile file){
        System.out.println("文件名："+file.getOriginalFilename()+",id是："+fp_id);
        String path="D:/Yanglaoyuan/yanglaoyuan/yanglaoyuanvue/static/imgs/"+file.getOriginalFilename();
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int result=fps.updateFoodpackgeImg(fp_id, file.getOriginalFilename());
        return result;
    }
}
