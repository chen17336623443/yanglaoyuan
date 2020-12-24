package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Mapper.IFoodorderMapper;
import com.yanglaoyuan.pojo.Foodorder;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 点餐Service
 * @author: YeJin Tan
 * @create: 2020-12-23 14:33
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FoodorderServices {
    @Autowired
    IFoodorderMapper fomapper;

    //新增点餐
    public Integer insertFoodorder(Foodorder forder){
        return fomapper.insertFoodorder(forder);
    }

    //查询所有的点餐信息
    public PageInfo<Foodorder> selectFoodorderAll(Integer pageNo,Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<Foodorder> list=fomapper.selectFoodorderAll();
        PageInfo<Foodorder> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
    //送餐改变状态
    public Integer updateFoodorderState(Integer fo_id){
        return fomapper.updateFoodorderState(fo_id);
    }
}
