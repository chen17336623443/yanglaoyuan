package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Consult;
import com.yanglaoyuan.pojo.Relation;
import com.yanglaoyuan.pojo.User;
import com.yanglaoyuan.service.ConsultServices;
import com.yanglaoyuan.service.RelationServices;
import com.yanglaoyuan.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relation")
public class RelationController {
    @Autowired
    RelationServices services;
    @Autowired
    UserServices userServices;
    /*新增家属记录*/
    @RequestMapping("addrelation")
    public  Integer addrelation(@RequestBody Relation d){
        User u=userServices.byuphone(d.getUserByUid().getUname());
        d.setUserByUid(u);
        return  services.addrelation(d);
    }

    @RequestMapping("selectbyomid")
    public PageInfo<Relation> selectbyomid(@RequestParam("no") Integer pageNum, @RequestParam("size") Integer pageSize,@RequestParam("omid") Integer omid){
        Integer pageNo = (pageNum!=null && pageNum>=1)?pageNum:1;
        Integer size = (pageSize!=null)?pageSize:10;
        return  services.selectbyomid(pageNo,size,omid);
    }


    /*删除老人家属信息*/
    @RequestMapping("delete")
    public   Integer delete(Integer id){
        return  services.delete(id);
    }

    /*修改老人家属信息*/
    @RequestMapping("update")
    public  Relation update(@RequestBody Relation d){
        System.out.println("---------"+d.getOldmanByOmId().getOmId());
        return  services.update(d);

    }
}

