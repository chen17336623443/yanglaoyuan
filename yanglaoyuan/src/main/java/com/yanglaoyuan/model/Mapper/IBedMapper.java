package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Bed;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: LiuJingZhao
 * @create: 2020-12-23 14:59
 */
@Mapper
@Repository
public interface IBedMapper {
    int insert(Integer byid,Integer fid ,String bname);
    List<Bed> selectByFid(Integer fid);

    /**
     * @Description 方法:a
     * @Param 参是:a
     * @Return 返回型是:a
     * @Author chenxing
     * @Date 2020/12/24 10:12
     */
    List<Bed> selectbyid(Integer fid);
    int del(Integer fid);
    int delbybid(Integer bid);
}
