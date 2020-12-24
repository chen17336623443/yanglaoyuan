package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Oldman;
import com.yanglaoyuan.pojo.Outregistration;
import com.yanglaoyuan.pojo.User;
import com.yanglaoyuan.service.OldmanServices;
import com.yanglaoyuan.service.OutregistrationServices;
import com.yanglaoyuan.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

/**
 * @author ChenZhiPeng
 * @date 2020/12/21 20:56
 */
@RestController
@RequestMapping("/outregistration")
public class OutregistrationController {
    @Autowired
    OutregistrationServices outregistrationservices;
    @Autowired
    OldmanServices oldmanServices;
    @Autowired
    UserServices userServices;

    @PostMapping("/page")
    /*分页模糊查询*/
    public PageInfo<Outregistration> selectBypage(@RequestParam("no")Integer no,
                                                  @RequestParam("size")Integer size,
                                                  @RequestParam("tomName")String tomName,
                                                  @RequestParam("outtrue")String outtrue,
                                                  @RequestParam(value="time[0]",required = false)String time1,
                                                  @RequestParam(value="time[1]",required = false)String time2){
        Integer pageSize=4;
        if (size!=null){
            pageSize=size;
        }
        Integer out;
        if (("未返回").equals(outtrue)){
            out=1;
        }else if(("已返回").equals(outtrue)) {
            out=2;
        }else {
            out=null;
        }
        System.out.println(out+"：是否返回");
        PageInfo<Outregistration> info=outregistrationservices.selectBypage(no,pageSize,tomName,out,time1,time2);
        for (Outregistration o:info.getList()) {
            System.out.println(o.toString());
        }
        System.out.println(info.getTotal()+"：总条数");
        return info;
    }

    @PostMapping("/insertOutregistration")
    /*新增外出记录*/
    public Integer insertOutregistration(@RequestParam("outAccompanyname")String outAccompanyname,
                                         @RequestParam("outAccompanyphone")String outAccompanyphone,
                                         @RequestParam("outReturnestimate") Timestamp outReturnestimate,
                                         @RequestParam("outReason")String outReason,
                                         @RequestParam("omId")Integer omId,
                                         @RequestParam("uid")Integer uid){
        Outregistration outregistration=new Outregistration();
        outregistration.setOutAccompanyname(outAccompanyname);
        outregistration.setOutAccompanyphone(outAccompanyphone);
        outregistration.setOutReturnestimate(outReturnestimate);
        outregistration.setOutReason(outReason);
        outregistration.setOutTime(new Timestamp(System.currentTimeMillis()));
        outregistration.setOutOutsum(0);
        outregistration.setOutTrue(1);
        /*查询老人*/
        Oldman oldman=oldmanServices.selectByomid(omId);
        outregistration.setOldmanByOmId(oldman);
        /*查询当前登录用户*/
        User user=userServices.selectByuid(uid);
        outregistration.setUserByUseUid(user);
        Integer i=outregistrationservices.insertOutregistration(outregistration);
        return i;
    }

    @PostMapping("/updateOutregistration")
    /*修改外出登记状态*/
    public Integer updateOutregistration(@RequestParam("outId")Integer outId){
        Integer i=outregistrationservices.updateOutregistration(outId);
        return i;
    }

    @PostMapping("/updateOutReturnestimate")
    /*修改预计返回时间*/
    public Integer updateOutReturnestimate(@RequestParam("outId")Integer outId,@RequestParam("outReturnestimate")Timestamp outReturnestimate){
        Integer i=outregistrationservices.updateOutReturnestimate(outId,outReturnestimate);
        return i;
    }

}
