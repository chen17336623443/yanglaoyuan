package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Dao.IVisitDao;
import com.yanglaoyuan.model.Mapper.IVisitMapper;
import com.yanglaoyuan.pojo.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author ChenZhiPeng
 * @date 2020/12/23 10:19
 */
@Repository
@Service
public class VisitServices {
    @Autowired
    IVisitMapper mapper;
    @Autowired
    IVisitDao dao;

    /*模糊分页查询*/
    public PageInfo<Visit> selectByPage(Integer no, Integer size, String tomname, String vname, String time1,String time2){
        PageHelper.startPage(no,size);
        List<Visit> list=mapper.selectByPage(tomname,vname,time1,time2);
        PageInfo<Visit> info=new PageInfo<>(list);
        return info;
    }

    /*新增来访记录*/
    public Integer insertVisit(Visit visit){
        return dao.save(visit).getvId();
    }

    /*来访人员离开*/
    public Integer leaveVisit(Integer vid){
        return mapper.leaveVisit(vid,new Timestamp(System.currentTimeMillis()));
    }

    /*根据id查询来访记录*/
    public Visit selectByvid(Integer vid){
        return mapper.selectByvid(vid);
    }

    /*修改来访记录*/
    public Integer updateVisit(Integer vId,String vName,String vPhone,String vReason,Integer vRelationship,String vRemarks){
        return mapper.updateVisit(vId,vName,vPhone,vReason,vRelationship,vRemarks);
    }
}
