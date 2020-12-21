package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Acare;
import com.yanglaoyuan.service.AcareService;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: JiaWei Liu
 * @create: 2020-12-21 16:02
 */
@RestController
@RequestMapping("/aca")
public class AcareController {
    @Autowired
    AcareService as;


    /**
     *@Description 方法是:selectall
     *@Param 参数是:[num]
     *@Return 返回类型是:com.github.pagehelper.PageInfo<com.yanglaoyuan.pojo.Acare>
     *@Other 备注:护工查询所有分页查询地址
     *@Author liujiawei
     *@Date 2020-12-21 16:23
     */
    @GetMapping("/all")
    public PageInfo<Acare> selectall(Integer num){
        return as.selectall(num);
    }
}
