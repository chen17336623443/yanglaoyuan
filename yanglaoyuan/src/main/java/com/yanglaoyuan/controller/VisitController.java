package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Oldman;
import com.yanglaoyuan.pojo.User;
import com.yanglaoyuan.pojo.Visit;
import com.yanglaoyuan.service.OldmanServices;
import com.yanglaoyuan.service.UserServices;
import com.yanglaoyuan.service.VisitServices;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

/**
 * @author ChenZhiPeng
 * @date 2020/12/23 9:52
 */
@RestController
@RequestMapping("/visit")
public class VisitController {
    @Autowired
    VisitServices visitServices;
    @Autowired
    OldmanServices oldmanServices;
    @Autowired
    UserServices userServices;

    @PostMapping("/selectByPage")
    public PageInfo<Visit> selectByPage(@RequestParam("no")Integer no,
                                        @RequestParam(value = "size",required = false)Integer size,
                                        @RequestParam("tomname")String tomname,
                                        @RequestParam("vname")String vname,
                                        @RequestParam(value="visittime[0]",required = false)String time1,
                                        @RequestParam(value="visittime[1]",required = false)String time2
                                        ){
        Integer pageSize=4;
        if (size!=null){
            pageSize=size;
        }
        PageInfo<Visit> info=visitServices.selectByPage(no,pageSize,tomname,vname,time1,time2);
        return info;
    }

    @PostMapping("/insertVisit")
    public Integer insertVisit(@RequestParam("vname")String vname,
                               @RequestParam("vphone")String vphone,
                               @RequestParam("vrelationship")Integer vrelationship,
                               @RequestParam("vreason")String vreason,
                               @RequestParam("vremarks")String vremarks,
                               @RequestParam("omid")Integer omid,
                               @RequestParam("uid")Integer uid
                               ){
        Visit visit=new Visit();
        visit.setvName(vname);
        visit.setvPhone(vphone);
        visit.setvReason(vreason);
        visit.setvRelationship(vrelationship);
        visit.setvRemarks(vremarks);
        visit.setvVisittime(new Timestamp(System.currentTimeMillis()));
        Oldman oldman=oldmanServices.selectByomid(omid);
        visit.setOldmanByOmId(oldman);
        User user=userServices.selectByuid(uid);
        visit.setUserByUid(user);
        Integer i=visitServices.insertVisit(visit);
        return i;
    }

    @PostMapping("/leaveVisit")
    public Integer leaveVisit(@RequestParam("vid")Integer vid){
        Integer i=visitServices.leaveVisit(vid);
        return i;
    }

    @PostMapping("/selectByvid")
    /*根据vid查询来访记录*/
    public Visit selectByvid(@RequestParam("vid")Integer vid){
        Visit visit=visitServices.selectByvid(vid);
        return visit;
    }

    @PostMapping("/updateVisit")
    /*修改来访记录*/
    public Integer updateVisit(@RequestParam("vId")Integer vId,
                               @RequestParam("vName")String vName,
                               @RequestParam("vPhone")String vPhone,
                               @RequestParam("vReason")String vReason,
                               @RequestParam("vRelationship")Integer vRelationship,
                               @RequestParam("vRemarks")String vRemarks){
        Integer i =visitServices.updateVisit(vId,vName,vPhone,vReason,vRelationship,vRemarks);
        return 1;
    }

}
