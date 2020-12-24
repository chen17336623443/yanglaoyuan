package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Thenursinglevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 护理级别Mapper接口
 * @author: JiaWei Liu
 * @create: 2020-12-23 17:20
 */
@Mapper
@Repository
public interface IThenursinglevelMapper {
    //护理级别查询所有
    List<Thenursinglevel> selectall();

    //根据主键查询护理级别
    Thenursinglevel selectbyid(Integer id);

    Thenursinglevel selectByomidThen(@Param("omid")Integer omid);
}
