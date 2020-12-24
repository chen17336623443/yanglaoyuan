package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Dao.IBedTypeDao;
import com.yanglaoyuan.model.Mapper.IBedTypeMapper;
import com.yanglaoyuan.pojo.Bedtype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: LiuJingZhao
 * @create: 2020-12-23 09:33
 */
@Service
public class BedTypeServices {
    @Autowired
    IBedTypeMapper mapper;

    @Autowired
    IBedTypeDao dao;

    public PageInfo<Bedtype> selectBypage(Integer no, Integer size){
        PageHelper.startPage(no,size);
        List<Bedtype> list=mapper.selectAll();
        PageInfo<Bedtype> info=new PageInfo<>(list);
        System.out.println(info);
        return info;
    }

    public List<Bedtype> selectAll(){
        return mapper.selectAll();
    }

    public Bedtype insert(Bedtype bedtype){
        return dao.save(bedtype);
    }

    public int delete(Integer byid){
        dao.deleteById(byid);
        return 1;
    }
}
