package com.yanglaoyuan.service;

import com.yanglaoyuan.model.Mapper.IFoodweekMapper;
import com.yanglaoyuan.pojo.Foodweek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 食物周次Service
 * @author: YeJin Tan
 * @create: 2020-12-23 11:15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FoodweekServices {
    @Autowired
    IFoodweekMapper fwmapper;

    //根据套餐id查询食物周次
    public List<Foodweek> selectFoodweekByFpId(Integer fp_id){
        return fwmapper.selectFoodweekByFpId(fp_id);
    }
}
