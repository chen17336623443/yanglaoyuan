package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Nursingtype;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 护理类别Mapper接口
 * @author: JiaWei Liu
 * @create: 2020-12-22 21:24
 */
@Mapper
@Repository
public interface INursingtypeMapper {
    //护理类别查询所有
    List<Nursingtype> selectall();

    //根据主键查询护理类别
    Nursingtype selectbyid(Integer ntid);

    //根据主键修改护理类型
    Integer updateNur(String name,String other,Integer ntid);
}
