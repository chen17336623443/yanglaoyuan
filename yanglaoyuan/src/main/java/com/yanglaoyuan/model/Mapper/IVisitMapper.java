package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Visit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 来访记录Mapper
 * @author: ChenZhiPeng
 * @create: 2020-12-23 09:54
 */
@Mapper
@Repository
public interface IVisitMapper {

    List<Visit> selectByPage(@Param("tomname") String tomname,
                             @Param("vname") String vname,
                             @Param("time1") String time1,
                             @Param("time2") String time2);

    Integer leaveVisit(@Param("vid")Integer vid,
                       @Param("leavetime")Timestamp leavetime);

    Visit selectByvid(@Param("vid")Integer vid);

    Integer updateVisit(@Param("vId")Integer vId,
                        @Param("vName")String vName,
                        @Param("vPhone")String vPhone,
                        @Param("vReason")String vReason,
                        @Param("vRelationship")Integer vRelationship,
                        @Param("vRemarks")String vRemarks);
}
