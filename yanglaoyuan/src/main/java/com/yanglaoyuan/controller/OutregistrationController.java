package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Outregistration;
import com.yanglaoyuan.service.OutregistrationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenZhiPeng
 * @date 2020/12/21 20:56
 */
@RestController
@RequestMapping("/outregistration")
public class OutregistrationController {
    @Autowired
    OutregistrationServices outregistrationservices;

    @PostMapping("/page")
    /*分页模糊查询*/
    public PageInfo<Outregistration> selectBypage(@RequestParam("no")Integer no,@RequestParam("size")Integer size,@RequestParam("tomName")String tomName,@RequestParam("outtrue")String outtrue,@RequestParam(value="time[0]",required = false)String time1,@RequestParam(value="time[1]",required = false)String time2){
        Integer pageSize=4;
        if (size!=null){
            pageSize=size;
        }
        PageInfo<Outregistration> info=outregistrationservices.selectBypage(no,pageSize,tomName,outtrue,time1,time2);
        return info;
    }

}
