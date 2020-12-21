package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Dao.ICheckinDao;
import com.yanglaoyuan.model.Mapper.ICheckinMapper;
import com.yanglaoyuan.pojo.Checkin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 入住缴费业务类
 * @author: chenxing
 * @create: 2020-12-21 21:29
 **/
@Service
public class CheckinService {

    @Autowired
    ICheckinDao dao;
    @Autowired
    ICheckinMapper mapper;

    /**
     * @Description 方法:a
     * @Param 参是:a
     * @Return 返回型是:a
     * @Author chenxing
     * @Date 2020/12/21 21:31
     */
    public Integer doinsert(Checkin c){
        try{
            dao.save(c);
            return c.getCheId();
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
            return -1;
        }
    }

    /**
     * @Description 方法:a
     * @Param 参是:a
     * @Return 返回型是:a
     * @Author chenxing
     * @Date 2020/12/21 21:33
     */
    public PageInfo<Checkin> selectBypager(Integer pageNo,Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<Checkin> list = mapper.selectall();
        return  new PageInfo<>(list);
    }

}
