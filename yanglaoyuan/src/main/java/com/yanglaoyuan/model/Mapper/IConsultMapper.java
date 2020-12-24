package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Consult;
import com.yanglaoyuan.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IConsultMapper {
    List<Consult> consultall(@Param("ename") String name,@Param("shengribegin") String begin, @Param("shengriend") String end);
/*新增咨询信息*/
    Integer inserconsult(@Param("d") Consult d);


    /*修改咨询信息00*/
    Integer updateconsult(@Param("d") Consult d);


    /*根据主键删除自选信息*/
    public Integer delectconsult(Integer cid);



}
