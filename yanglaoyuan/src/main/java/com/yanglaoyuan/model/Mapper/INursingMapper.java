package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Nursing;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 护理实体Mapper接口
 * @author: JiaWei Liu
 * @create: 2020-12-22 21:44
 */
@Mapper
@Repository
public interface INursingMapper {
    //查询所有护理
    List<Nursing> selectall();
    //根据护理类别查询护理
    List<Nursing> selectbyid(Integer id);
    //删除护理项目
    Integer del(Integer id);
    //修改护理项目
    Integer upd(String nuname,String numonry,String nutime,String nuother,Integer nuid);
    //根据id查询实体
    Nursing selectid(Integer id);

}
