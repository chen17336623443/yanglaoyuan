package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Dao.ICostflowDao;
import com.yanglaoyuan.model.Mapper.ICostflowMapper;
import com.yanglaoyuan.pojo.Costflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 费用流水业务类
 * @author: chenxing
 * @create: 2020-12-22 14:01
 **/
@Service
public class CostflowService {

    @Autowired
    ICostflowMapper mapper;
    @Autowired
    ICostflowDao dao;

    /**
     * @Description 方法:a
     * @Param 参是:a
     * @Return 返回型是:a
     * @Author chenxing
     * @Date 2020/12/22 14:08
     */
    public Integer doinsert(Costflow c){
        try{
            dao.save(c);
            return c.getCosId();
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
     * @Date 2020/12/22 14:11
     */
    public PageInfo<Costflow> selectBypager(Integer pagerNo,Integer pagerSize){
        PageHelper.startPage(pagerNo,pagerSize);
        List<Costflow> list = mapper.selectall();
        return new PageInfo<>(list);
    }

    /**
     * @Description 方法:a
     * @Param 参是:a
     * @Return 返回型是:a
     * @Author chenxing
     * @Date 2020/12/22 16:01
     */
    public PageInfo<Costflow> selectBypagers(Integer pagerNo,Integer pagerSize,String name,String lx,String kdate,String jdate){
        PageHelper.startPage(pagerNo,pagerSize);
        List<Costflow> list = mapper.selectbykwy("%"+name+"%",lx,kdate,jdate);
        return new PageInfo<>(list);
    }

}
