package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Deposit;
import com.yanglaoyuan.service.DepositServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * @Description 方法是pager
     * @Param 参数:no,size
     * @Return 返回类是:PageInfo<Deposit>
     * @Author chenxing
     * @Date 2020/12/21 16:46
     */
    @RequestMapping("/pager")
    public PageInfo<Deposit> pager(Integer no, @RequestParam(required = false) Integer size){
        Integer pageSize = 5;
        if(size!=null){
            pageSize = size;
        }
        return ds.selectByPager(no,size);
    }

    @RequestMapping("/sele")
    public List<Deposit> sele(){
        System.out.println(111);
        return ds.select();
    }

}
