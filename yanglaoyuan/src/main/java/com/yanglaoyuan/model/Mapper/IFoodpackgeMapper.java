package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Foodpackge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 食物套餐Mapper接口
 * @author: YeJin Tan
 * @create: 2020-12-22 21:11
 */
@Repository
@Mapper
public interface IFoodpackgeMapper {
    //查询所有套餐
    List<Foodpackge> selectFoodpackgeAll();
    //新增套餐
    Integer insertFoodpackge(@Param("fpackge")Foodpackge fpackge);
    //修改套餐
    Integer updateFoodpackge(@Param("fpackge")Foodpackge fpackge);
    //删除套餐
    Integer deleteFoodpackge(Integer fp_id);
}
