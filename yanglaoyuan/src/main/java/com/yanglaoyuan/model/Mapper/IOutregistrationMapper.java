package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Outregistration;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 外出登记表mapper
 * @author: ChenZhiPeng
 * @create: 2020-12-21 20:58
 */
@Repository
@Mapper
public interface IOutregistrationMapper {

    List<Outregistration> selectByParam(@Param("tomName")String tomName,@Param("outtrue")String outtrue,@Param("time1")String time1,@Param("time2")String time2);

}
