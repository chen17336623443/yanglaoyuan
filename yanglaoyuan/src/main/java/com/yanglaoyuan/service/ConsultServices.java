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
    public PageInfo<Consult> consultall(Integer pageNum, Integer pageSize,String name,String begin, String end){

        PageHelper.startPage(pageNum,pageSize);
        List<Consult> consultall = iConsultMapper.consultall(name,begin,end);
        PageInfo<Consult> info = new PageInfo<>(consultall);

        return info;
    }



    /*新增咨询信息*/
    public  Integer inserconsult(Consult d){
        return  iConsultMapper.inserconsult( d);
    }


    /*修改咨询信息*/
    public  Integer updateconsult(Consult  d){
        return  iConsultMapper.updateconsult(d);
    }


    /*根据主键删除咨询信息*/

    public Integer delectconsult(Integer cid){
        return  iConsultMapper.delectconsult(cid);
    }

}
