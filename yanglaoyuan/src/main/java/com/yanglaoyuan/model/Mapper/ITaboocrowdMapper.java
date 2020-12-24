package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Taboocrowd;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 禁忌人群Mapper接口
 * @author: YeJin Tan
 * @create: 2020-12-22 14:43
 */
@Repository
@Mapper
public interface ITaboocrowdMapper {
    //查询所有禁忌人群
    List<Taboocrowd> selectTaboocrowdAll();
    //根据食物id查询该食物的禁忌人群
    List<Taboocrowd> selectTaboocrowdByFid(Integer f_id);

    /*根据主键插叙疾病信息*/
    Taboocrowd bytcId(Integer id);
}
