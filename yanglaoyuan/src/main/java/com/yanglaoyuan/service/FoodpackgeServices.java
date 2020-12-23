package com.yanglaoyuan.service;

import com.yanglaoyuan.model.Mapper.IFoodpackgeMapper;
import com.yanglaoyuan.pojo.Foodpackge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 食物套餐Service
 * @author: YeJin Tan
 * @create: 2020-12-22 21:17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FoodpackgeServices {
    @Autowired
    IFoodpackgeMapper fpmapper;

    //查询所有套餐
    public List<Foodpackge> selectFoodpackgeAll(){
        return fpmapper.selectFoodpackgeAll();
    }

    //新增套餐
    public Integer insertFoodpackge(Foodpackge fpackge){
        return fpmapper.insertFoodpackge(fpackge);
    }

    //修改套餐
    public Integer updateFoodpackge(Foodpackge fpackge){
        return fpmapper.updateFoodpackge(fpackge);
    }
}
