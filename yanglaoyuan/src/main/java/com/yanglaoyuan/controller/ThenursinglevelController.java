package com.yanglaoyuan.controller;

import com.yanglaoyuan.pojo.Thenursinglevel;
import com.yanglaoyuan.service.ThenursinglevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 护理级别Controller层
 * @author: JiaWei Liu
 * @create: 2020-12-23 17:24
 */
@RestController
@RequestMapping("/tnsl")
public class ThenursinglevelController {
    @Autowired
    ThenursinglevelService ts;

    /**
     * //护理级别查询所有
     * @return
     */
    @RequestMapping("/all")
    public List<Thenursinglevel> selectall(){
        return ts.selectall();
    }

    /**
     * 根据主键查询护理级别
     * @param id
     * @return
     */
    @RequestMapping("/byid")
    public Thenursinglevel selectbyid(Integer id){
        return ts.selectbyid(id);
    }


    @PostMapping("/add")
    public String add(@RequestBody Thenursinglevel t){
        return ts.add(t)==1?"新增护理级别成功！":"新增护理级别失败！";
    }

}
