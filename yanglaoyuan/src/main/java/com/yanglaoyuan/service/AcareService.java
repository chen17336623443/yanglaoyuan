package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Dao.IAcareDao;
import com.yanglaoyuan.model.Mapper.IAcareMapper;
import com.yanglaoyuan.pojo.Acare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: JiaWei Liu
 * @create: 2020-12-21 15:53
 */
@Service
public class AcareService {
    @Autowired
    IAcareMapper am;
    @Autowired
    IAcareDao ad;

    //护工查询所有分页
    public PageInfo<Acare> selectall(Integer num){
        PageHelper.startPage(num,5);
        List<Acare> selectall = am.selectall();
        PageInfo<Acare> acarePageInfo = new PageInfo<>(selectall);
        return acarePageInfo;
    }
}
