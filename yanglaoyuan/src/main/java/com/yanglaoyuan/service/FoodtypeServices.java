package com.yanglaoyuan.service;

import com.yanglaoyuan.model.Mapper.IFoodtypeMapper;
import com.yanglaoyuan.pojo.Foodtype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 食物类别Services
 * @author: YeJin Tan
 * @create: 2020-12-21 16:52
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FoodtypeServices {
    @Autowired
    IFoodtypeMapper ftmapper;
    //查询所有食物类别
    public List<Foodtype> selectFoodtypeAll(){
        return ftmapper.selectFoodtypeAll();
    }
}
