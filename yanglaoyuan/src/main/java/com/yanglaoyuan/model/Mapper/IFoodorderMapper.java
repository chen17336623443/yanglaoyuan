package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Foodorder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 点餐Mapper接口
 * @author: YeJin Tan
 * @create: 2020-12-23 14:12
 */
@Repository
@Mapper
public interface IFoodorderMapper {
    //新增点餐
    Integer insertFoodorder(@Param("forder")Foodorder forder);
    //查询所有点餐
    List<Foodorder> selectFoodorderAll();
    //送餐改变状态
    Integer updateFoodorderState(Integer fo_id);
    //根据id查询点餐数据  送餐查询
    Foodorder selectFoodorderByFoId(Integer fo_id);
}
