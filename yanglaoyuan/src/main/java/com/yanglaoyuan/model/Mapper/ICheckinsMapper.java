package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Checkin;
import com.yanglaoyuan.pojo.Checkins;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 入住缴费Mapper接口
 * @author: chenxing
 * @create: 2020-12-21 21:19
 **/
@Mapper
@Repository
public interface ICheckinsMapper {

    List<Checkins> checkinsall(@Param("d") Checkins d,@Param("shengribegin") String shengribegin,@Param("shengriend")String shengriend,@Param("tomName")String tomName);
/*新增登记记录*/
    Integer insertcheckins(@Param("d") Checkins d);

    /*删除登记信息*/
    Integer delete(Integer  id);
}
