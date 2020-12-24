package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Accident;
import com.yanglaoyuan.pojo.Oldman;
import com.yanglaoyuan.pojo.User;
import com.yanglaoyuan.service.AccidentServices;
import com.yanglaoyuan.service.OldmanServices;
import com.yanglaoyuan.service.UserServices;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

/**
 * @author ChenZhiPeng
 * @date 2020/12/23 19:15
 */
@RestController
@RequestMapping("/accident")
public class AccidentController {
    @Autowired
    AccidentServices accidentServices;
    @Autowired
    UserServices userServices;
    @Autowired
    OldmanServices oldmanServices;

    @PostMapping("/selectByPage")
    public PageInfo<Accident> selectByPage(@RequestParam("no")Integer no,
                                           @RequestParam(value = "size",required = false)Integer size,
                                           @RequestParam("tomname")String tomname){
        Integer pageSize=2;
        if (size!=null){
            pageSize=size;
        }
        System.out.println(tomname);
        PageInfo<Accident> info=accidentServices.selectByPage(no,pageSize,tomname);
        return info;
    }

    @PostMapping("/insertAccident")
    public Integer insertAccident(@RequestParam("omid")Integer omid,
                                  @RequestParam("uid")Integer uid,
                                  @RequestParam("accType")Integer accType,
                                  @RequestParam("accReason")String accReason,
                                  @RequestParam("accDescribe")String accDescribe,
                                  @RequestParam("accTime") Timestamp accTime){
        Accident accident=new Accident();
        accident.setAccDescribe(accDescribe);
        accident.setAccReason(accReason);
        accident.setAccTime(accTime);
        accident.setAccType(accType);
        User user=userServices.selectByuid(uid);
        System.out.println(user.toString());
        accident.setUserByUid(user);
        Oldman oldman=oldmanServices.selectByomid(omid);
        accident.setOldmanByOmId(oldman);
        Integer i=accidentServices.insertAccident(accident);
        return i;
    }

    @PostMapping("/selectByaccid")
    public Accident selectByaccid(@RequestParam("accid")Integer accid){
        Accident accident=accidentServices.selectByaccid(accid);
        return accident;
    }

    @PostMapping("/updateAccident")
    public Integer updateAccident(@RequestParam("accid")Integer accid,
                                  @RequestParam("accdescribe")String accdescribe,
                                  @RequestParam("accreason")String accreason,
                                  @RequestParam("acctime") Timestamp acctime,
                                  @RequestParam("acctype")Integer acctype){
        Integer i=accidentServices.updateAccident(accid,accdescribe,accreason,acctime,acctype);
        return i;
    }

    @PostMapping("/deleteAccident")
    public Integer deleteAccident(@RequestParam("accid")Integer accid){
        Integer i=accidentServices.deleteAccident(accid);
        return i;
    }
}
