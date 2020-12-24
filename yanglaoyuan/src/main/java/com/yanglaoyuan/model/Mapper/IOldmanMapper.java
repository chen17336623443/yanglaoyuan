package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Oldman;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    /*查询未出院的老人*/
    List<Oldman> selectNotSettle();

    //护工离职 老人外键删除
    int del(Integer id);

    //护工安排老人
    int updateid(Integer acid,Integer omid);

    //查询没有安排护工的老人
    List<Oldman> selectnoacid();

/*修改老人的护理套餐*/
    Integer updatetsl(@Param("omid") Integer omid, @Param("id") Integer id);

    /*给老人设置餐饮套餐*/
    Integer updatefp(@Param("omid") Integer omid, @Param("id") Integer id);

    //查询所有点过餐的老人
    List<Oldman> selectOldmanByHaveFoid();



}
