package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Bedtype;
import com.yanglaoyuan.pojo.Floor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 楼房Mapper
 * @author: LiuJingZhao
 * @create: 2020-12-21 20:20
 */
@Mapper
@Repository
public interface IFloorMapper {
    List<Floor> selectAll();

    int insertLou(Floor floor);

    Floor selectfid(Integer fid);

    int deldetByFid(Integer fid);

    List<Floor> selectByFid(Integer fid);
    List<Floor> selectByFidlc(Integer fid);
    List<Floor> selectByFidfj(Integer fid);

    List<Floor> selectByFtype();

    int updateNameByFid(Integer fid,String fname);

    Bedtype bytype(Integer id);
}
