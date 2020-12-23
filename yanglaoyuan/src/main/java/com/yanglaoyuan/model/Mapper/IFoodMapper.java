package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Food;
import com.yanglaoyuan.pojo.Taboocrowd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    //修改食物
    Integer updateFood(@Param("food")Food food);
    //修改食物图片
    Integer updateFoodImg(@Param("f_id")Integer f_id,@Param("f_img")String f_img);
    //组合查询食物
    List<Food> comQueryFood(@Param("ft_id")Integer ft_id,@Param("f_name")String f_name,@Param("f_remark")String f_remark);
    //新增食物
    Integer insertFood(@Param("food") Food food);
    //删除食物
    Integer deleteFood(Integer f_id);

    //删除食物的禁忌人群(删除中间表)
    Integer deleteFoodTabs(Integer f_id);
    //给食物添加禁忌人群
    Integer insertFoodTabs(List<Map<String,Integer>> listParam);
}
