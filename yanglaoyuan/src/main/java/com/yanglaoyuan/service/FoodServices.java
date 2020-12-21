package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Mapper.IFoodMapper;
import com.yanglaoyuan.pojo.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    //查询所有食物
    public PageInfo<Food> selectFoodAll(Integer pageNo,Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<Food> list=fmapper.selectFoodAll();
        PageInfo<Food> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
}
