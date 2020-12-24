package com.yanglaoyuan.service;

import com.yanglaoyuan.model.Dao.IBedDao;
import com.yanglaoyuan.model.Mapper.IBedMapper;
import com.yanglaoyuan.pojo.Bed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: LiuJingZhao
 * @create: 2020-12-23 14:29
 */
@Service
public class BedServices {
    @Autowired
    IBedDao dao;
    @Autowired
    IBedMapper mapper;

     public int insert(Integer byid,Integer fid ,String bname){
        return mapper.insert(byid,fid,bname);
    }
    public List<Bed> selectByFid(Integer fid){
         return mapper.selectByFid(fid);
    }
    public int delbybid(Integer bid){
        return mapper.delbybid(bid);
    }
    public int del(Integer fid){
        return mapper.del(fid);
    }

    public Bed selecyBybid(Integer bid){
         return mapper.selecyBybid(bid);
    }
}
