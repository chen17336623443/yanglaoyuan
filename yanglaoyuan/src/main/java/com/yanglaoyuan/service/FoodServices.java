package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Mapper.IFoodMapper;
import com.yanglaoyuan.model.Mapper.ITaboocrowdMapper;
import com.yanglaoyuan.pojo.Food;
import com.yanglaoyuan.pojo.Taboocrowd;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @program: yanglaoyuan
 * @description: 食物Services
 * @author: YeJin Tan
 * @create: 2020-12-21 16:58
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FoodServices {
    @Autowired
    IFoodMapper fmapper;

    @Autowired
    ITaboocrowdMapper tcmapper;
    //查询所有食物
    public PageInfo<Food> selectFoodAll(Integer pageNo,Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<Food> list=fmapper.selectFoodAll();
        PageInfo<Food> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
    //修改食物
    public Integer updateFood(Food food){
        return fmapper.updateFood(food);
    }
    //修改食物图片
    public Integer updateFoodImg(Integer f_id,String f_img){
        return fmapper.updateFoodImg(f_id, f_img);
    }
    //组合查询食物
    public PageInfo<Food> comQueryFood(Integer ft_id,String f_name,String f_remark,Integer pageNo,Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<Food> list=fmapper.comQueryFood(ft_id, f_name, f_remark);
        PageInfo<Food> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
    //新增食物
    public Integer insertFood(Food food){
        return fmapper.insertFood(food);
    }
    //删除食物
    public Integer deleteFood(Integer f_id){
        return fmapper.deleteFood(f_id);
    }


    //查询所有禁忌人群
    public List<Taboocrowd> selectTaboocrowdAll(){
        return tcmapper.selectTaboocrowdAll();
    }

    //删除食物的禁忌人群(删除中间表)
    public Integer deleteFoodTabs(Integer f_id){
        return fmapper.deleteFoodTabs(f_id);
    }
    //给食物添加禁忌人群
    public Integer insertFoodTabs(List<Map<String,Integer>> listParam){
        return fmapper.insertFoodTabs(listParam);
    }
}
