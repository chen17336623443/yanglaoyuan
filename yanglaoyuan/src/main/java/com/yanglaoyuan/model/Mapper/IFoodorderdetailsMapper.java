package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Foodorderdetails;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 点餐详情Mapper接口
 * @author: YeJin Tan
 * @create: 2020-12-23 15:21
 */
@Repository
@Mapper
public interface IFoodorderdetailsMapper {

    //根据点餐id查询点餐详情
    List<Foodorderdetails> selectFoodorderdetailsByFoId(Integer fo_id);
    //点餐详情新增
    Integer insertFoodorderdetails(List<Foodorderdetails> params);

}
