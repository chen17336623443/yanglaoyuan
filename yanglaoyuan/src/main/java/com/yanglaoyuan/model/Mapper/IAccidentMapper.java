package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Accident;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 意外登记Mapper
 * @author: ChenZhiPeng
 * @create: 2020-12-23 19:17
 */
@Repository
@Mapper
public interface IAccidentMapper {

    List<Accident> selectByPage(@Param("tomname") String tomname);

    Accident selectByaccid(@Param("accid")Integer accid);

    Integer updateAccident(@Param("accid")Integer accid,
                           @Param("accdescribe")String accdescribe,
                           @Param("accreason")String accreason,
                           @Param("acctime") Timestamp acctime,
                           @Param("acctype")Integer acctype);

    Integer deleteAccident(@Param("accid")Integer accid);

}
