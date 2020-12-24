package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Stayback;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 退住登记Mapper
 * @author: ChenZhiPeng
 * @create: 2020-12-24 10:02
 */
@Repository
@Mapper
public interface IStaybackMapper {

    List<Stayback> selectByseid(@Param("seid") Integer seid);

}
