package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Thenursinglevel;
import com.yanglaoyuan.pojo.Thenursingleveldetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 护理级别详细Mapper接口
 * @author: JiaWei Liu
 * @create: 2020-12-24 01:29
 */
@Mapper
@Repository
public interface IThenursingleveldetailsMapper {
    List<Thenursingleveldetails> selectbyid(Integer tslid);
}
