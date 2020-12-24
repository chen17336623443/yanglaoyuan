package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Register;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 缴费mapper接口
 * @author: chenxing
 * @create: 2020-12-23 09:35
 **/
@Mapper
@Repository
public interface IRegisterMapper {

    /**
     * 查询所有
     * @Description 方法是a
     * @Param 参数:a
     * @Return 返回类是:a
     * @Author chenxing
     * @Date 2020/12/23 9:38
     */
    List<Register> selectall();

    /**
     * 多条件查询
     * @Description 方法是a
     * @Param 参数:a
     * @Return 返回类是:a
     * @Author chenxing
     * @Date 2020/12/23 11:27
     */
    List<Register> selectbykwy(String name,String kdate,String jdate);

}
