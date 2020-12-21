package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Floor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 楼房Mapper
 * @author: LiuJingZhao
 * @create: 2020-12-21 20:20
 */
@Mapper
@Repository
public interface FloorMapper {
    List<Floor> selectAll();
}
