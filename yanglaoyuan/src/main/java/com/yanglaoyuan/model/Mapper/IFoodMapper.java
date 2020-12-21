package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Food;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 食物Mapper接口
 * @author: YeJin Tan
 * @create: 2020-12-21 16:20
 */
@Repository
@Mapper
public interface IFoodMapper {
    //查询所有食物
    List<Food> selectFoodAll();
    //根据类别id查询该类别下的所有食物
    List<Food> selectFoodAllByFoodtypeId(Integer ft_id);
}
