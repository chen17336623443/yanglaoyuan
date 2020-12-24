package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Costflow;
import com.yanglaoyuan.pojo.Deposit;
import com.yanglaoyuan.pojo.Oldman;
import com.yanglaoyuan.pojo.User;
import com.yanglaoyuan.service.CostflowService;
import com.yanglaoyuan.service.DepositServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @program: yanglaoyuan
 * @description: 预存管理contrller
 * @author: chenxing
 * @create: 2020-12-21 16:43
 **/
@RestController
@RequestMapping("/deposit")
@CrossOrigin
public class DepositContrller {

    @Autowired
    DepositServices ds;
    @Autowired
    CostflowService cs;

    /**
     * 预存修改
     * @Description 方法:a
     * @Param 参是:a
     * @Return 返回型是:a
     * @Author chenxing
     * @Date 2020/12/23 8:31
     */
    @RequestMapping("/upd")
    public PageInfo<Deposit> update(@RequestParam("no") Integer no, @RequestParam(value="size",required = false) Integer size,
                                    @RequestParam("zmoney") BigDecimal zmoney, @RequestParam("jmoney") BigDecimal jmoney,
                                    @RequestParam("jfr") String jfr, @RequestParam("sfr") Integer sfr,
                                    @RequestParam("jffs") String jffs, @RequestParam("bz") String bz,@RequestParam("omid") Integer omid){
        ds.update(zmoney,new Timestamp(new Date().getTime()),jffs,jfr,bz,omid);
        Costflow c = new Costflow();
        User u = new User();
        Oldman ol = new Oldman();
        ol.setOmId(omid);
        u.setUid(sfr);
        c.setCosCategory("交费");
        c.setCosDate(new Timestamp(new Date().getTime()));
        c.setCosMoney(jmoney);
        c.setUserByUid(u);
        c.setCosExplain("费用预存");
        c.setOldmanByOmId(ol);
        cs.doinsert(c);
        Integer pageSize = 5;
        if(size!=null){
            pageSize = size;
        }
        return ds.selectByPager(no,size);
    }

    /**
     * 根据老人id查询预存
     * @Description 方法:a
     * @Param 参是:a
     * @Return 返回型是:a
     * @Author chenxing
     * @Date 2020/12/22 20:16
     */
    @RequestMapping("/byid")
    public Deposit selectbyid(@RequestParam("omid")Integer omid){
        return ds.selectbyid(omid);
    }

    /**
     * 预存查询分页
     * @Description 方法是pager
     * @Param 参数:no,size
     * @Return 返回类是:PageInfo<Deposit>
     * @Author chenxing
     * @Date 2020/12/21 16:46
     */
    @RequestMapping("/pager")
    public PageInfo<Deposit> pager(@RequestParam("no") Integer no, @RequestParam(value="size",required = false) Integer size){
        Integer pageSize = 5;
        if(size!=null){
            pageSize = size;
        }
        return ds.selectByPager(no,size);
    }

    /**
     * 预存多条件查询分页
     * @Description 方法是a
     * @Param 参数:a
     * @Return 返回类是:a
     * @Author chenxing
     * @Date 2020/12/22 10:14
     */
    @RequestMapping("/pagers")
    public PageInfo<Deposit> pagers(@RequestParam("no") Integer no, @RequestParam(value="size",required = false) Integer size,
                                    @RequestParam("name")String name,@RequestParam("phone")String phone,@RequestParam("card")String card){
        Integer pageSize = 5;
        if(size!=null){
            pageSize = size;
        }
        return ds.selectByPagers(no, size, name, phone, card);
    }

}
