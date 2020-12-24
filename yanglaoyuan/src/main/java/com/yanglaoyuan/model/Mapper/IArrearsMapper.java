package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Arrears;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 欠费管理Mapper接口
 * @author: chenxing
 * @create: 2020-12-23 16:04
 **/
@Mapper
@Repository
public interface IArrearsMapper {

    /**
     * 查询所有
     * @Description 方法是:a
     * @Param 参数是:a
     * @Return 返回类型是:a
     * @Author chenxing
     * @Date 2020/12/23 16:06
     */
    List<Arrears> selectall();

    /**
     * 多条件查询
     * @Description 方法:a
     * @Param 参是:a
     * @Return 返回型是:a
     * @Author chenxing
     * @Date 2020/12/23 20:42
     */
    List<Arrears> selectbykwy(String name, String sfz, String cwh, String qfje,String kdate,String jdate,String zt);

}
