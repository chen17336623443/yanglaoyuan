package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Dao.IOutregistrationDao;
import com.yanglaoyuan.model.Mapper.IOutregistrationMapper;
import com.yanglaoyuan.pojo.Outregistration;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author ChenZhiPeng
 * @date 2020/12/21 21:18
 */
@Service
@Repository
public class OutregistrationServices {
    @Autowired
    IOutregistrationMapper mapper;
    @Autowired
    IOutregistrationDao dao;

    public PageInfo<Outregistration> selectBypage(Integer no,Integer size,String tomName, Integer outtrue, String time1,String time2){
        PageHelper.startPage(no,size);
        List<Outregistration> list=mapper.selectByParam(tomName,outtrue,time1,time2);
        PageInfo<Outregistration> info=new PageInfo<>(list);
        return info;
    }

    /*新增外出记录*/
    public Integer insertOutregistration(Outregistration outregistration){
        Outregistration o=dao.save(outregistration);
        return o.getOutId();
    }

    /*修改外出状态*/
    public Integer updateOutregistration(Integer outid){
        Integer i=mapper.updateOutregistration(outid,new Timestamp(System.currentTimeMillis()));
        return i;
    }

    /*修改延期*/
    public Integer updateOutReturnestimate(Integer outid,Timestamp outReturnestimate){
        Integer i=mapper.updateOutReturnestimate(outid,outReturnestimate);
        return i;
    }
}
