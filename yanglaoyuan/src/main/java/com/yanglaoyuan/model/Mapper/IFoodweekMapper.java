package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Foodweek;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 食物周次Mapper接口
 * @author: YeJin Tan
 * @create: 2020-12-23 11:09
 */
@Repository
@Mapper
public interface IFoodweekMapper {
    //根据套餐id查询食物周次
    List<Foodweek> selectFoodweekByFpId(Integer fp_id);
}
