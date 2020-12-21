package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Foodtype;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 食物类别Mapper接口
 * @author: YeJin Tan
 * @create: 2020-12-21 16:07
 */
@Repository
@Mapper
public interface IFoodtypeMapper {
    //查询所有食物类别
    List<Foodtype> selectFoodtypeAll();
}
