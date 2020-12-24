package com.yanglaoyuan.service;

import com.yanglaoyuan.model.Dao.IStaybackDao;
import com.yanglaoyuan.model.Mapper.IStaybackMapper;
import com.yanglaoyuan.pojo.Stayback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author ChenZhiPeng
 * @date 2020/12/24 10:02
 */
@Service
@Repository
public class StaybackServices {
    @Autowired
    IStaybackMapper mapper;
    @Autowired
    IStaybackDao dao;

    public Integer insert(Stayback stayback){
        return dao.save(stayback).getStId();
    }
}
