package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Bed;
import com.yanglaoyuan.pojo.Bedtype;
import com.yanglaoyuan.service.BedTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 床位类型管理
 * @author: LiuJingZhao
 * @create: 2020-12-23 09:31
 */
@RestController
@RequestMapping("/bedType")
public class BedTypeController {
    @Autowired
    BedTypeServices services;

    @RequestMapping("/selectAll")
    /**
     *@Description 方法是:selectAll
     *@Param 参数是:[no, size]
     *@Return 返回类型是:java.util.List<com.yanglaoyuan.pojo.Bedtype>
     *@Author LiuJingZhao
     *@Date 2020/12/23 14:36
     */
    public List<Bedtype> selectAll() {
        List<Bedtype> bedtypes = services.selectAll();
        return bedtypes;
    }
    
    @RequestMapping("/all")
    /**
     *@Description 方法是:selectBypage
     *@Param 参数是:[]
     *@Return 返回类型是:java.util.List<com.yanglaoyuan.pojo.Bedtype>
     *@Author LiuJingZhao
     *@Date 2020/12/23 9:56
     */
    public PageInfo<Bedtype>  selectBypage(@RequestParam("no")Integer no,@RequestParam("size")Integer size) {
        Integer pageSize=4;
        if (size!=null){
            pageSize=size;
        }
        PageInfo<Bedtype> info=services.selectBypage(no,pageSize);
        for (Bedtype o:info.getList()) {
            System.out.println(o.toString());
        }
        return info;
    }

    @RequestMapping("delete")
    /**
     *@Description 方法是:delete
     *@Param 参数是:[bedtypes]
     *@Return 返回类型是:int
     *@Author LiuJingZhao
     *@Date 2020/12/23 11:24
     */
    public int delete(@RequestParam("byid")Integer byid){
        services.delete(byid);
        return 1;
    }

    @RequestMapping("update")
    /**
     *@Description 方法是:update
     *@Param 参数是:[by]
     *@Return 返回类型是:com.yanglaoyuan.pojo.Bedtype
     *@Author LiuJingZhao
     *@Date 2020/12/23 11:04
     */
    public Bedtype update(@ModelAttribute Bedtype by){
        System.out.println(by);
        Bedtype bedtype = new Bedtype();
        bedtype.setByid(by.getByid());
        bedtype.setByname(by.getByname());
        bedtype.setByinfo(by.getByinfo());
        bedtype.setByimg(null);
        bedtype.setBymoney(by.getBymoney());
        bedtype.setByallmoney(by.getByallmoney());
        Timestamp d = new Timestamp(System.currentTimeMillis());
        bedtype.setBytime(d);
        return services.insert(bedtype);
    }


    @RequestMapping("insert")
    /**
     *@Description 方法是:insert
     *@Param 参数是:[by]
     *@Return 返回类型是:com.yanglaoyuan.pojo.Bedtype
     *@Author LiuJingZhao
     *@Date 2020/12/23 11:04
     */
    public Bedtype insert(@ModelAttribute Bedtype by){
        System.out.println(by);
        Bedtype bedtype = new Bedtype();
        bedtype.setByid(null);
        bedtype.setByname(by.getByname());
        bedtype.setByinfo(by.getByinfo());
        bedtype.setByimg(null);
        bedtype.setBymoney(by.getBymoney());
        bedtype.setByallmoney(by.getByallmoney());
        Timestamp d = new Timestamp(System.currentTimeMillis());
        bedtype.setBytime(d);
        return services.insert(bedtype);
    }

}
