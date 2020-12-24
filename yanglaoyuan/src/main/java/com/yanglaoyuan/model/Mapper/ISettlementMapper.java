package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Settlement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 退住结算Mapper
 * @author: ChenZhiPeng
 * @create: 2020-12-24 09:54
 */
@Mapper
@Repository
public interface ISettlementMapper {

    Settlement selectByseid(Integer seid);

    List<Settlement> selectByPage(@Param("tomname") String tomname);

}
