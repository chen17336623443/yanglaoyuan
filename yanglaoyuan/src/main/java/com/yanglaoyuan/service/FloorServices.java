package com.yanglaoyuan.service;

import com.yanglaoyuan.model.Dao.IFloorDao;
import com.yanglaoyuan.model.Dao.IPublicshouseDao;
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

    public List<Floor> selectByFid(Integer fid){
        return mapper.selectByFid(fid);
    }
    public Floor selectfid(Integer fid){
        return mapper.selectfid(fid);
    }
    public List<Floor> selectByFidlc(Integer fid){
        return mapper.selectByFidlc(fid);
    }
    public List<Floor> selectByFidfj(Integer fid){
        return mapper.selectByFidfj(fid);
    }


    public int deldetByFid(Integer fid){
        return mapper.deldetByFid(fid);
    }

    public Floor insertLou2(Floor floor){
        return dao.save(floor);
    }

    public int updateNameByFid(Integer fid,String fname){
        return mapper.updateNameByFid(fid,fname);
    }

    public List<Floor> selectByFtype(){
        return mapper.selectByFtype();
    }

}
