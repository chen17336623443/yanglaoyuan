package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Dao.IDepositDao;
import com.yanglaoyuan.model.Mapper.IDepositMapper;
import com.yanglaoyuan.pojo.Deposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 预存管理业务类
 * @author: chenxing
 * @create: 2020-12-21 16:27
 **/
@Service
public class DepositServices {

    @Autowired
    IDepositDao dao;
    @Autowired
    IDepositMapper mapper;

    /**
     * 修改余额
     * @Description 方法:a
     * @Param 参是:a
     * @Return 返回型是:a
     * @Author chenxing
     * @Date 2020/12/23 15:31
     */
    public void updatemoney(BigDecimal depMoney,Integer omid){
        mapper.updatemoney(depMoney,omid);
    }

    /**
     * 修改
     * @Description 方法:a
     * @Param 参是:a
     * @Return 返回型是:a
     * @Author chenxing
     * @Date 2020/12/22 20:15
     */
    public void update(BigDecimal depMoney, Timestamp depDate, String depPayment,
                       String depPaytype, String depRemarks, Integer omid){
        mapper.upadtedep(depMoney, depDate, depPayment, depPaytype, depRemarks, omid);
    }

    /**
     * 新增
     * @Description 方法:doinsert
     * @Param 参数是:Deposit
     * @Return 返回型是:Integer
     * @Author chenxing
     * @Date 2020/12/21 16:31
     */
    public Integer doinsert(Deposit d){
        try{
            dao.save(d);
            return d.getDepId();
        }catch (Exception e){
            System.out.println("异常："+e);
            return -1;
        }
    }

    /**
     * 根据id查询
     * @Description 方法:a
     * @Param 参是:a
     * @Return 返回型是:a
     * @Author chenxing
     * @Date 2020/12/22 19:17
     */
    public Deposit selectbyid(Integer omid){
        return mapper.selectbyid(omid);
    }

    /**
     * 分页查询
     * @Description 方法:selectByPager
     * @Param 参数是:pageNo,pageSize
     * @Return 返回型是:PageInfo<Deposit>
     * @Author chenxing
     * @Date 2020/12/21 16:41
     */
    public PageInfo<Deposit> selectByPager(Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<Deposit> list = mapper.selectalls();
        return  new PageInfo<>(list);
    }

    /**
     * 多条件分页查询
     * @Description 方法:selectByPagers
     * @Param 参是:a
     * @Return 返回型是:a
     * @Author chenxing
     * @Date 2020/12/22 10:12
     */
    public PageInfo<Deposit> selectByPagers(Integer pageNo, Integer pageSize,String name,String phone,String card){
        PageHelper.startPage(pageNo,pageSize);
        List<Deposit> list = mapper.selectbykwy("%"+name+"%",phone,card);
        return  new PageInfo<>(list);
    }


}
