package com.yanglaoyuan.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Mapper.IConsultMapper;
import com.yanglaoyuan.pojo.Consult;
import com.yanglaoyuan.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultServices {
    @Autowired
    IConsultMapper  iConsultMapper;
    public PageInfo<Consult> consultall(Integer pageNum, Integer pageSize){

        PageHelper.startPage(pageNum,pageSize);
        List<Consult> consultall = iConsultMapper.consultall();
        PageInfo<Consult> info = new PageInfo<>(consultall);

        return info;
    }
}
