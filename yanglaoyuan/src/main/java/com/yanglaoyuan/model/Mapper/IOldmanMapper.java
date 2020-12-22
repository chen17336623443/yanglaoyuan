package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Oldman;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface IOldmanMapper {
    /**
     * @program: yanglaoyuan
     * @description: 老人表mapper
     * @author: ChenZhiPeng
     * @create: 2020-12-21 16:17
     */


    List<Oldman> selectoldmanAll();

    List<Oldman> selectoldmanByid(Integer acid);

    /*根据老人主键查询*/
    Oldman selectOldmanByomid(Integer omid);

    /*查询未外出的老人*/
    List<Oldman> selectNotOut();

}
