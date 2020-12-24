package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Mapper.IFooddeliveredMapper;
import com.yanglaoyuan.pojo.Fooddelivered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 送餐Service
 * @author: YeJin Tan
 * @create: 2020-12-24 02:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FooddeliveredServices {
    @Autowired
    IFooddeliveredMapper fdmapper;

    //送餐新增
    public Integer insertFooddelivered(Fooddelivered fdel){
        return fdmapper.insertFooddelivered(fdel);
    }
    //查询所有送餐记录
    public PageInfo<Fooddelivered> selectFooddeliveredAll(Integer pageNo,Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<Fooddelivered> list=fdmapper.selectFooddeliveredAll();
        PageInfo<Fooddelivered> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
}
