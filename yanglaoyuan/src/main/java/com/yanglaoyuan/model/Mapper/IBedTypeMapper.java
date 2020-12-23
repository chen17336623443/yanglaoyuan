package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Bedtype;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: LiuJingZhao
 * @create: 2020-12-23 09:34
 */
@Mapper
@Repository
public interface IBedTypeMapper {

    List<Bedtype> selectAll();

}
