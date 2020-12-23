package com.yanglaoyuan.service;

import com.yanglaoyuan.model.Mapper.IFoodorderdetailsMapper;
import com.yanglaoyuan.pojo.Foodorderdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 点餐详情Service
 * @author: YeJin Tan
 * @create: 2020-12-23 15:42
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FoodorderdetailsServices {
    @Autowired
    IFoodorderdetailsMapper fodmapper;

    //根据点餐id查询点餐详情
    public List<Foodorderdetails> selectFoodorderdetailsByFoId(Integer fo_id){
        return fodmapper.selectFoodorderdetailsByFoId(fo_id);
    }
    //点餐详情新增
    public Integer insertFoodorderdetails(List<Foodorderdetails> params){
        return fodmapper.insertFoodorderdetails(params);
    }
}
