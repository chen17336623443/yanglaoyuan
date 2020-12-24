package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Costflow;
import com.yanglaoyuan.service.CostflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 费用流水Controller
 * @author: chenxing
 * @create: 2020-12-22 14:12
 **/
@RestController
@RequestMapping("/costflow")
@CrossOrigin
public class CostflowController {

    @Autowired
    CostflowService cs;

    @RequestMapping("/byomid")
    public List<Costflow> selectbyomid(@RequestParam("omid")Integer omid,@RequestParam("type")String type){
        return cs.selectbyomid(omid,type);
    }

    /**
     * 分页
     * @Description 方法是a
     * @Param 参数:a
     * @Return 返回类是:a
     * @Author chenxing
     * @Date 2020/12/23 8:38
     */
    @RequestMapping("/pager")
    public PageInfo<Costflow> pager(@RequestParam("no")Integer no,@RequestParam(value="size",required = false)Integer size){
        Integer pageSize = 5;
        if(size!=null){
            pageSize = size;
        }
        return cs.selectBypager(no, size);
    }

    /**
     * 多条件分页
     * @Description 方法是a
     * @Param 参数:a
     * @Return 返回类是:a
     * @Author chenxing
     * @Date 2020/12/23 8:38
     */
    @RequestMapping("/pagers")
    public PageInfo<Costflow> pagers(@RequestParam("no")Integer no,@RequestParam(value="size",required = false)Integer size,
                                     @RequestParam("name")String name,@RequestParam("fylb")String fylb,
                                     @RequestParam("kdate")String kdate,@RequestParam("jdate")String jdate){
        Integer pageSize = 5;
        if(size!=null){
            pageSize = size;
        }
        return cs.selectBypagers(no, size, name, fylb, kdate, jdate);
    }

}
