package com.yanglaoyuan.service;

import com.yanglaoyuan.model.Dao.IFloorDao;
import com.yanglaoyuan.model.Mapper.IFloorMapper;
import com.yanglaoyuan.pojo.Floor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 楼房services
 * @author: LiuJingZhao
 * @create: 2020-12-21 20:19
 */
@Service
public class FloorServices {
    @Autowired
    IFloorMapper mapper;
    @Autowired
    IFloorDao dao;

    public List<Floor> selectAll(){
        return mapper.selectAll();
    }

    public Floor insertLou2(Floor floor){
        return dao.save(floor);
    }
}
