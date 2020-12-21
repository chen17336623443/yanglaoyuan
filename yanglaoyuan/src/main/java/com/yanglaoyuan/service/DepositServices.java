package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Dao.IDepositDao;
import com.yanglaoyuan.model.Mapper.IDepositMapper;
import com.yanglaoyuan.pojo.Deposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @Description 方法:selectByPager
     * @Param 参数是:pageNo,pageSize
     * @Return 返回型是:PageInfo<Deposit>
     * @Author chenxing
     * @Date 2020/12/21 16:41
     */
    public PageInfo<Deposit> selectByPager(Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<Deposit> list = mapper.selectall();
        return  new PageInfo<>(list);
    }

    public List<Deposit> select(){
        System.out.println("list:"+mapper.selectall());
        return mapper.selectall();
    }

}
