package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.*;
import com.yanglaoyuan.service.ArrearsService;
import com.yanglaoyuan.service.CostflowService;
import com.yanglaoyuan.service.DepositServices;
import com.yanglaoyuan.service.RegisterService;
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
 * @description: 缴费登记Controller
 * @author: chenxing
 * @create: 2020-12-23 09:53
 **/
@RestController
@RequestMapping("/register")
@CrossOrigin
public class RegisterController {

    @Autowired
    RegisterService rs;
    @Autowired
    DepositServices ds;
    @Autowired
    CostflowService cs;
    @Autowired
    ArrearsService as;

    @RequestMapping("/add")
    public PageInfo<Register> add(@RequestParam("no")Integer no, @RequestParam("size")Integer size,
                                  @RequestParam("jmoney") BigDecimal jmoney,@RequestParam("omid") Integer omid,
                                  @RequestParam("jfr") String jfr, @RequestParam("sfr") Integer sfr,
                                  @RequestParam("jffs") String jffs, @RequestParam("bz") String bz,
                                  @RequestParam("moneyc") BigDecimal moneyc){
        System.out.println(jmoney+"/"+omid+"/"+jfr+"/"+sfr+"/"+jffs+"/"+bz+"/"+moneyc);
        Oldman ol = new Oldman();
        ol.setOmId(omid);
        User u = new User();
        u.setUid(sfr);
        if(jffs.equals("余额")){
            ds.updatemoney(moneyc,omid);
            Arrears ar = new Arrears();
            ar.setOldmanByOmId(ol);
            ar.setArrMoney(moneyc.multiply(new BigDecimal(-1)));
            ar.setArrSfpay("否");
            as.doinsert(ar);
        }
        //交费新增
        Register reg = new Register();
        reg.setRegDate(new Timestamp(new Date().getTime()));
        reg.setOldmanByOmId(ol);
        reg.setUserByUid(u);
        reg.setRegMoney(jmoney);
        reg.setRegPaytype(jffs);
        reg.setRegPay(jfr);
        reg.setRegRemarks(bz);
        rs.doinsert(reg);
        //流水新增
        Costflow co = new Costflow();
        co.setOldmanByOmId(ol);
        co.setUserByUid(u);
        co.setCosMoney(jmoney);
        co.setCosDate(new Timestamp(new Date().getTime()));
        co.setCosCategory("月消费");
        co.setCosExplain("老人月消费");
        cs.doinsert(co);
        Integer pageSize = 5;
        if(size!=null){
            pageSize = size;
        }
        return rs.selectBypager(no, size);
    }

    /**
     * 分页查询
     * @Description 方法是a
     * @Param 参数:a
     * @Return 返回类是:a
     * @Author chenxing
     * @Date 2020/12/23 10:00
     */
    @RequestMapping("/pager")
    public PageInfo<Register> selectbypager(@RequestParam("no")Integer no,@RequestParam("size")Integer size){
        Integer pageSize = 5;
        if(size!=null){
            pageSize = size;
        }
        return rs.selectBypager(no, size);
    }

    /**
     * 多条件分页查询
     * @Description 方法是a
     * @Param 参数:a
     * @Return 返回类是:a
     * @Author chenxing
     * @Date 2020/12/23 11:35
     */
    @RequestMapping("/pagers")
    public PageInfo<Register> selectBypagers(@RequestParam("no")Integer no,@RequestParam("size")Integer size,
                                             @RequestParam("name")String name,@RequestParam("kdate")String kdate,
                                             @RequestParam("jdate")String jdate){
        Integer pageSize = 5;
        if(size!=null){
            pageSize = size;
        }
        return rs.selectBypagers(no,size,name,kdate,jdate);
    }

}
