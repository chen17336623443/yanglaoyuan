package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Acare;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: JiaWei Liu
 * @create: 2020-12-21 15:32
 */
@Mapper
@Repository
public interface IAcareMapper {
    //护工查询所有
    List<Acare> selectall();
}
