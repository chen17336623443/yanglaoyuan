package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Dao.IRegisterDao;
import com.yanglaoyuan.model.Mapper.IRegisterMapper;
import com.yanglaoyuan.pojo.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 缴费登记service
 * @author: chenxing
 * @create: 2020-12-23 09:54
 **/
@Service
public class RegisterService {

    @Autowired
    IRegisterDao dao;
    @Autowired
    IRegisterMapper mapper;

    /**
     * 新增
     * @Description 方法:a
     * @Param 参是:a
     * @Return 返回型是:a
     * @Author chenxing
     * @Date 2020/12/23 9:56
     */
    public Integer doinsert(Register r){
        try{
            dao.save(r);
            return r.getRegId();
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
            return -1;
        }
    }

    /**
     * 分页查询
     * @Description 方法:a
     * @Param 参是:a
     * @Return 返回型是:a
     * @Author chenxing
     * @Date 2020/12/23 9:58
     */
    public PageInfo<Register> selectBypager(Integer pagerNo,Integer pagerSize){
        PageHelper.startPage(pagerNo,pagerSize);
        List<Register> list = mapper.selectall();
        return new PageInfo<>(list);
    }

    /**
     * 多条件分页查询
     * @Description 方法是a
     * @Param 参数:a
     * @Return 返回类是:a
     * @Author chenxing
     * @Date 2020/12/23 11:32
     */
    public PageInfo<Register> selectBypagers(Integer pagerNo,Integer pagerSize,String name,String kdate,String jdate){
        PageHelper.startPage(pagerNo,pagerSize);
        List<Register> list = mapper.selectbykwy("%"+name+"%",kdate,jdate);
        return new PageInfo<>(list);
    }

}
