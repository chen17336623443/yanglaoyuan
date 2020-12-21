package com.yanglaoyuan.service;

import com.yanglaoyuan.model.Mapper.FloorMapper;
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
    FloorMapper mapper;
    public List<Floor> selectAll(){
        return mapper.selectAll();
    }
}
