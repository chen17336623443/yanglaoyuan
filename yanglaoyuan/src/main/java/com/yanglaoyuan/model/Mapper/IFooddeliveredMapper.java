package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Fooddelivered;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 送餐Mapper接口
 * @author: YeJin Tan
 * @create: 2020-12-24 02:22
 */
@Repository
@Mapper
public interface IFooddeliveredMapper {
    //送餐新增
    Integer insertFooddelivered(@Param("fdel") Fooddelivered fdel);
    //查询所有送餐记录
    List<Fooddelivered> selectFooddeliveredAll();
}
