package com.yanglaoyuan.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Dao.IrelationDao;
import com.yanglaoyuan.model.Mapper.IConsultMapper;
import com.yanglaoyuan.model.Mapper.IRelationMapper;
import com.yanglaoyuan.pojo.Consult;
import com.yanglaoyuan.pojo.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationServices {
    @Autowired
    IRelationMapper mapper;
    @Autowired
    IrelationDao dao;

    /*新增家属记录*/
    public Integer addrelation(Relation d){
        return  mapper.addrelation(d);
    }

    /*根据老人id查询老人家属列表*/

    public PageInfo<Relation> selectbyomid(Integer no,Integer size,Integer omid){
        PageHelper.startPage(no,size);
        List<Relation> selectbyomid = mapper.selectbyomid(omid);
        PageInfo<Relation> info = new PageInfo<>(selectbyomid);
        return info;
    }


    /*删除老人家属信息*/
    public Integer delete(Integer id){
        return  mapper.delete(id);
    }

    /*修改老人家属信息*/
    public Relation update(Relation d){
        return  dao.save(d);
    }

}
