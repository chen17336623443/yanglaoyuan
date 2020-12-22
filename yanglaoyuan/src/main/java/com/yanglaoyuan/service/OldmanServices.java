package com.yanglaoyuan.service;

import com.yanglaoyuan.model.Dao.IOldmanDao;

import com.yanglaoyuan.model.Mapper.IOldmanMapper;
import com.yanglaoyuan.pojo.Oldman;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/12/21 16:18
 */

@Service
public class OldmanServices {
    @Autowired
    IOldmanDao dao;
    @Autowired
    IOldmanMapper mapper;

    public List<Oldman> selectAll(){
        return mapper.selectoldmanAll();
    }


    /*查询未外出的老人*/
    public List<Oldman> selectNotOut(){
        return mapper.selectNotOut();
    }

    /*根据id查询老人*/
    public Oldman selectByomid(Integer omid){
        return mapper.selectOldmanByomid(omid);
    }
}
