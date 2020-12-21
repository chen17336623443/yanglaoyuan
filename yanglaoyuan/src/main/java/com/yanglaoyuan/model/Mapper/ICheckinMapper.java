package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Checkin;
import org.apache.ibatis.annotations.Mapper;
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
public interface ICheckinMapper {

    List<Checkin> selectall();

}
