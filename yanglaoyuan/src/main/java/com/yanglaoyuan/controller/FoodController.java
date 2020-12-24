package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.config.MyResult;
import com.yanglaoyuan.pojo.Food;
import com.yanglaoyuan.pojo.Foodtype;
import com.yanglaoyuan.pojo.Taboocrowd;
import com.yanglaoyuan.service.FoodServices;
import com.yanglaoyuan.service.FoodtypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //查询所有食物 不带分页
    @RequestMapping("/allNotPage")
    public List<Food> selectFoodAll2(){
        return fs.selectFoodAll2();
    }

    //所有食物类型
    @RequestMapping("/alltype")
    public List<Foodtype> selectFoodtypeAll(){
        return fts.selectFoodtypeAll();
    }

    //修改食物
    @RequestMapping("/update")
    /**
     *@Description 方法是:updateFood
     *@Param 参数是:[food]
     *@Return 返回类型是:java.lang.Integer
     *@Author tanyejin
     *@Date 2020-12-22 9:33
     */
    public Integer updateFood(@RequestBody Food food){
        //如果食物有禁忌人群则添加关联
        if(food.getTaboocrowds().size()>0){
            fs.deleteFoodTabs(food.getfId());
            List<Map<String,Integer>> list=new ArrayList<>();
            for (Taboocrowd t : food.getTaboocrowds()) {
                Map<String,Integer> map=new HashMap<>();
                map.put("tc_id", t.getTcId());
                map.put("f_id", food.getfId());
                list.add(map);
            }
            fs.insertFoodTabs(list);
        }
        int result=fs.updateFood(food);
        return result>0?food.getfId():result;
    }

    //修改食物图片
    @RequestMapping("/updateImg")
    /**
     *@Description 方法是:updateFoodImg
     *@Param 参数是:[file, f_id]
     *@Return 返回类型是:java.lang.Integer
     *@Author tanyejin
     *@Date 2020-12-22 9:40
     */
    public Integer updateFoodImg(MultipartFile file,@RequestParam("f_id") Integer f_id){
        System.out.println("文件名："+file.getOriginalFilename()+",id是："+f_id);
        String path="D:/Yanglaoyuan/yanglaoyuan/yanglaoyuanvue/static/imgs/"+file.getOriginalFilename();
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int result=fs.updateFoodImg(f_id,file.getOriginalFilename());
        return result;
    }

    //食物组合查询
    @RequestMapping("/comQuery")
    /**
     *@Description 方法是:comQueryFood
     *@Param 参数是:[ft_id, f_name, f_remark]
     *@Return 返回类型是:java.util.List<com.yanglaoyuan.pojo.Food>
     *@Author tanyejin
     *@Date 2020-12-22 10:11
     */
    public PageInfo<Food> comQueryFood(@RequestParam(value = "ft_id",required = false)Integer ft_id,
                                   @RequestParam(value = "f_name",required = false)String f_name,
                                   @RequestParam(value = "f_remark",required = false)String f_remark,
                                   @RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize")Integer pageSize){
        return fs.comQueryFood(ft_id, f_name, f_remark,pageNo,pageSize);
    }

    //食物新增
    @RequestMapping("/insert")
    /**
     *@Description 方法是:insertFood
     *@Param 参数是:[food]
     *@Return 返回类型是:java.lang.Integer
     *@Author tanyejin
     *@Date 2020-12-22 10:28
     */
    public Integer insertFood(@RequestBody Food food){
        int result=fs.insertFood(food);
        if(food.getTaboocrowds().size()>0){
            fs.deleteFoodTabs(food.getfId());
            List<Map<String,Integer>> list=new ArrayList<>();
            for (Taboocrowd t : food.getTaboocrowds()) {
                Map<String,Integer> map=new HashMap<>();
                map.put("tc_id", t.getTcId());
                map.put("f_id", food.getfId());
                list.add(map);
            }
            fs.insertFoodTabs(list);
        }
        return result>0?food.getfId():result;
    }
    
    //食物删除
    @RequestMapping("/delete")
    /**
     *@Description 方法是:deleteFood
     *@Param 参数是:[f_id]
     *@Return 返回类型是:java.lang.Integer
     *@Author tanyejin
     *@Date 2020-12-22 10:44
     */
    public Integer deleteFood(Integer f_id){
        return fs.deleteFood(f_id);
    }
    
    //查询所有禁忌人群
    @RequestMapping("/tabAll")
    /**
     *@Description 方法是:selectTaboocrowdAll
     *@Param 参数是:[]
     *@Return 返回类型是:java.util.List<com.yanglaoyuan.pojo.Taboocrowd>
     *@Author tanyejin
     *@Date 2020-12-22 15:07
     */
    public List<Taboocrowd> selectTaboocrowdAll(){
        return fs.selectTaboocrowdAll();
    }

    //点餐 组合查询食物
    @RequestMapping("/groupFood")
    public List<Food> groupFood(@RequestParam(value = "fPiquancy",required = false) String fPiquancy,@RequestParam(value = "fTaste",required = false) String fTaste,
                                @RequestParam(value = "maxPrice",required = false) Integer maxPrice,
                               @RequestParam(value = "minPrice",required = false) Integer minPrice,
                                @RequestParam(value = "taboocrowds",required = false) String taboocrowds){
        List<Integer> list=new ArrayList<>();
        if(taboocrowds!=null){
            String[] array=taboocrowds.split(",");
            for (String s : array) {
                list.add(new Integer(s));
            }
        }
        List<Food> foods=fs.groupFood(fPiquancy,fTaste,maxPrice, minPrice, list);
        return foods;

    }
}
