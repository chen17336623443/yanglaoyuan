package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Dao.IArrearsDao;
import com.yanglaoyuan.model.Mapper.IArrearsMapper;
import com.yanglaoyuan.pojo.Arrears;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 欠费管理业务类
 * @author: chenxing
 * @create: 2020-12-23 16:11
 **/
@Service
public class ArrearsService {

    @Autowired
    IArrearsDao dao;
    @Autowired
    IArrearsMapper mapper;

    /**
     * 新增
     * @Description 方法是:a
     * @Param 参数是:a
     * @Return 返回类型是:a
     * @Author chenxing
     * @Date 2020/12/23 16:13
     */
    public Integer doinsert(Arrears a){
        try {
            dao.save(a);
            return a.getArrId();
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
     * @Date 2020/12/23 16:15
     */
    public PageInfo<Arrears> selectBypager(Integer pagerNo,Integer pagerSize){
        PageHelper.startPage(pagerNo,pagerSize);
        List<Arrears> list = mapper.selectall();
        return new PageInfo<>(list);
    }

    /**
     * 多条件分页查询
     * @Description 方法是a
     * @Param 参数:a
     * @Return 返回类是:a
     * @Author chenxing
     * @Date 2020/12/23 20:58
     */
    public PageInfo<Arrears> selectBypagers(Integer pagerNo, Integer pagerSize, String name, String sfz, String cwh,
                                            String qfje, String kdate, String jdate, String zt){
        PageHelper.startPage(pagerNo,pagerSize);
        List<Arrears> list = mapper.selectbykwy("%"+name+"%","%"+sfz+"%",cwh,qfje,kdate,jdate,zt);
        return new PageInfo<>(list);
    }

}
