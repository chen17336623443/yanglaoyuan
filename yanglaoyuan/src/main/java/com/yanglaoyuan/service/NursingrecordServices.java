package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Dao.INursingrecordDao;
import com.yanglaoyuan.model.Mapper.INursingrecordMapper;
import com.yanglaoyuan.pojo.Nursingrecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: JiaWei Liu
 * @create: 2020-12-24 16:22
 */
@Service
public class NursingrecordServices {
    @Autowired
    INursingrecordMapper inumapper;

    @Autowired
    INursingrecordDao inudao;
    //查询所有
   public PageInfo<Nursingrecord> selectall(Integer num,Integer size){
       PageHelper.startPage(num,size);
       List<Nursingrecord> selectall = inumapper.selectall();
       PageInfo<Nursingrecord> nursingrecordPageInfo = new PageInfo<>(selectall);
       return nursingrecordPageInfo;
   }
    //根据老人id查询
    public List<Nursingrecord> selectbyomid(Integer omid){
       return inumapper.selectbyomid(omid);
    }

    //新增
    public Integer add(Nursingrecord n){
        try {
            inudao.save(n);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
