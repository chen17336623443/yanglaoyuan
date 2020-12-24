package com.yanglaoyuan.controller;

import com.yanglaoyuan.pojo.Nursingtype;
import com.yanglaoyuan.service.NursingtypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 护理类型Controller层
 * @author: JiaWei Liu
 * @create: 2020-12-22 21:38
 */
@RestController
@RequestMapping("/nurst")
public class NursingtypeController {
    @Autowired
    NursingtypeServices ns;

    /**
     * 护理类别查询所有
     * @return
     */
    @RequestMapping("/all")
    public List<Nursingtype> selectall() {
        return ns.selectall();
    }

    /**
     * 根据主键查询护理类别
     * @param ntid
     * @return
     */
    @RequestMapping("/byid")
    public Nursingtype selectbyid(Integer ntid) {
        return ns.selectbyid(ntid);
    }

    /**
     * 新增护理类别
     * @param nursingtype
     * @return
     */
    @PostMapping("/add")
    public String  add(@RequestBody Nursingtype nursingtype) {
        return ns.add(nursingtype)==1?"新增类别成功！":"新增类别失败！";
    }

    /**
     * 修改护理类别
     * @param name
     * @param other
     * @param ntid
     * @return
     */
    @GetMapping("/update")
    public String  updateNur(String name,String other,Integer ntid) {
        return ns.updateNur(name, other, ntid)==1?"修改类别成功！":"修改类别失败！";
    }
}
