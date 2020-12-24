package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Costflow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 费用流水Mapper接口
 * @author: chenxing
 * @create: 2020-12-22 11:25
 **/
@Mapper
@Repository
public interface ICostflowMapper {
    
    /**
     * 查询所有
     * @Description 方法是a
     * @Param 参数:a
     * @Return 返回类是:a
     * @Author chenxing
     * @Date 2020/12/22 15:37
     */
    List<Costflow> selectall();

    /**
     * 多条件查询
     * @Description 方法:a
     * @Param 参是:a
     * @Return 返回型是:a
     * @Author chenxing
     * @Date 2020/12/22 15:37
     */
    List<Costflow> selectbykwy(String name,String lx,String kdate,String jdate);

    /**
     * 根据老人id查询
     * @Description 方法是:a
     * @Param 参数是:a
     * @Return 返回类型是:a
     * @Author chenxing
     * @Date 2020/12/23 19:19
     */
    List<Costflow> selectbyomid(Integer omid,String type);

    List<Costflow> selectByomidOld(Integer omid);

}
