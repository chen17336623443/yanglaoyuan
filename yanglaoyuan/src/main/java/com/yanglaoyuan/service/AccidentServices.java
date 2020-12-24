package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Dao.IAccidentDao;
import com.yanglaoyuan.model.Mapper.IAccidentMapper;
import com.yanglaoyuan.pojo.Accident;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author ChenZhiPeng
 * @date 2020/12/23 19:18
 */
@Repository
@Service
public class AccidentServices {
    @Autowired
    IAccidentDao dao;
    @Autowired
    IAccidentMapper mapper;

    public PageInfo<Accident> selectByPage(Integer no,Integer size,String tomname){
        PageHelper.startPage(no,size);
        List<Accident> list=mapper.selectByPage(tomname);
        PageInfo<Accident> info=new PageInfo<>(list);
        return info;
    }

    /*新增意外*/
    public Integer insertAccident(Accident accident){
        return dao.save(accident).getAccId();
    }

    /*根据id查询*/
    public Accident selectByaccid(Integer accid){
        return mapper.selectByaccid(accid);
    }

    /*根据id修改*/
    public Integer updateAccident(Integer accid,String accdescribe,String accreason,Timestamp acctime, Integer acctype){
        return mapper.updateAccident(accid,accdescribe,accreason,acctime,acctype);
    }

    /*根据id删除*/
    public Integer deleteAccident(Integer accid){
        return mapper.deleteAccident(accid);
    }

}
