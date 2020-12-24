package com.yanglaoyuan.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Mapper.ICheckinsMapper;
import com.yanglaoyuan.model.Mapper.IConsultMapper;
import com.yanglaoyuan.pojo.Checkins;
import com.yanglaoyuan.pojo.Consult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class checkinsServices {
    @Autowired
    ICheckinsMapper mapper;

    /*查询入住登记记录 模糊查询加多条件组合查询+分页查询*/
    public PageInfo<Checkins> consultall(Integer pageNum, Integer pageSize,Checkins checkins,String begin, String end,String tomName){

        PageHelper.startPage(pageNum,pageSize);
        List<Checkins> consultall = mapper.checkinsall(checkins,begin,end,tomName);
        PageInfo<Checkins> info = new PageInfo<>(consultall);

        return info;
    }

    /*新增登记入住*/
    public Integer insertcheckins(Checkins d){
        return  mapper.insertcheckins(d);
    }


    /*删除登记入住信息*/
    public  Integer delete(Integer id){
        return  mapper.delete(id);
    }



}
