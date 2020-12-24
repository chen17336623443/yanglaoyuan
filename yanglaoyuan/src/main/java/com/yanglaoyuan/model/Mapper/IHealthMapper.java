package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Consult;
import com.yanglaoyuan.pojo.Health;
import jdk.internal.org.objectweb.asm.Handle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IHealthMapper {
/*根据老人主键查询健康信息*/
    List<Health> byomid(Integer omid);




}
