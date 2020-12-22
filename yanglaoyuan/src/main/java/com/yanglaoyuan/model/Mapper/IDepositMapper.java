package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Deposit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 预存管理mapper接口
 * @author: chenxing
 * @create: 2020-12-21 16:24
 **/
@Mapper
@Repository
public interface IDepositMapper {

    /**
     * 预存查询所有
     * @Description 方法是:a
     * @Param 参数是:a
     * @Return 返回类型是:a
     * @Author chenxing
     * @Date 2020/12/21 16:25
     */
    List<Deposit> selectalls();

    /**
     * @Description 方法:a
     * @Param 参是:a
     * @Return 返回型是:a
     * @Author chenxing
     * @Date 2020/12/22 10:02
     */
    List<Deposit> selectbykwy(String name,String phone,String card);

    /**
     * @Description 方法:a
     * @Param 参是:a
     * @Return 返回型是:a
     * @Author chenxing
     * @Date 2020/12/22 19:13
     */
    Deposit selectbyid(Integer omid);

    void upadtedep(BigDecimal depMoney, Timestamp depDate,String depPayment,String depPaytype,String depRemarks,Integer omid);

}
