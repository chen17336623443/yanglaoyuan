package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Nursingrecord;
import com.yanglaoyuan.service.NursingServices;
import com.yanglaoyuan.service.NursingrecordServices;
import com.yanglaoyuan.service.OldmanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: JiaWei Liu
 * @create: 2020-12-24 16:27
 */
@RestController
@RequestMapping("/nrc")
public class NursingrecordController {
    @Autowired
    NursingrecordServices nrs;

    @Autowired
    NursingServices ns;

    @Autowired
    OldmanServices ol;

    /**
     * 查询所有
     * @param num
     * @param size
     * @return
     */
    @RequestMapping("/all")
    public PageInfo<Nursingrecord> selectall(Integer num,Integer size){
        return nrs.selectall(num, size);
    }

    @RequestMapping("/byid")
    public List<Nursingrecord> selectbyid(Integer omid){
        return nrs.selectbyomid(omid);
    }

    @PostMapping("/add/{omid}/{nuid}")
    public String add(@RequestBody Nursingrecord n, @PathVariable("omid")Integer omid,@PathVariable("nuid") Integer nuid){
        n.setOldmanByOmId(ol.selectByomid(omid));
        n.setNursingByNuId(ns.selectid(nuid));
        return nrs.add(n)==1?"新增记录成功！":"新增记录失败！";
    }
}
