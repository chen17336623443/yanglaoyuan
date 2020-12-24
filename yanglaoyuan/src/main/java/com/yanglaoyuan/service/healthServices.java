package com.yanglaoyuan.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Dao.IhealthDao;
import com.yanglaoyuan.model.Mapper.IConsultMapper;
import com.yanglaoyuan.model.Mapper.IHealthMapper;
import com.yanglaoyuan.pojo.Consult;
import com.yanglaoyuan.pojo.Health;
import jdk.internal.org.objectweb.asm.Handle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class healthServices {
    @Autowired
    IHealthMapper mapper;
    @Autowired
    IhealthDao dao;

    /*根据老人主键查询对应健康信息*/
    public List<Health> byomid(Integer omid){
        return  mapper.byomid(omid);
    }

    /*新增或者修改*/
    public  Health save(Health d){
        return  dao.save(d);

    }
}
