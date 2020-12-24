package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Nursingrecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 老人护理记录Mapper接口
 * @author: JiaWei Liu
 * @create: 2020-12-24 15:39
 */
@Mapper
@Repository
public interface INursingrecordMapper {
    //查询所有
    List<Nursingrecord> selectall();
    //根据老人id查询
    List<Nursingrecord> selectbyomid(Integer omid);
}
