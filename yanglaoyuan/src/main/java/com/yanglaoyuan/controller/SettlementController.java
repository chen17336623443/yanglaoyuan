package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Oldman;
import com.yanglaoyuan.pojo.Settlement;
import com.yanglaoyuan.pojo.Stayback;
import com.yanglaoyuan.pojo.User;
import com.yanglaoyuan.service.OldmanServices;
import com.yanglaoyuan.service.SettlementServices;
import com.yanglaoyuan.service.StaybackServices;
import com.yanglaoyuan.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author ChenZhiPeng
 * @date 2020/12/24 8:43
 */
@RestController
@RequestMapping("/settlement")
public class SettlementController {
    @Autowired
    SettlementServices settlementServices;
    @Autowired
    OldmanServices oldmanServices;
    @Autowired
    UserServices userServices;
    @Autowired
    StaybackServices staybackServices;

    @PostMapping("/selectByPage")
    public PageInfo<Settlement> selectByPage(@RequestParam("no")Integer no,
                                             @RequestParam(value = "size",required = false)Integer size,
                                             @RequestParam("tomname")String tomname){
        Integer pageSize=2;
        if (size!=null){
            pageSize=size;
        }
        PageInfo<Settlement> info=settlementServices.selectByPage(no,pageSize,tomname);
        return info;
    }

    /*退住结算*/
    @PostMapping("/insertSettlement")
    public Integer insertSettlement(@RequestParam("sedue")Integer sedue,
                                    @RequestParam("sewithdrawn")Integer sewithdrawn,
                                    @RequestParam("streason")String streason,
                                    @RequestParam("stform")String stform,
                                    @RequestParam("omId")Integer omId,
                                    @RequestParam("uid")Integer uid){
        User u=userServices.selectByuid(uid);
        Settlement settlement=new Settlement();
        settlement.setSeDue(new BigDecimal(sedue));
        settlement.setSeTime(new Timestamp(System.currentTimeMillis()));
        settlement.setSeWithdrawn(new BigDecimal(sewithdrawn));
        settlement.setUserByUid(u);
        Oldman oldman=oldmanServices.selectByomid(omId);
        settlement.setOldmanByOmId(oldman);
        Integer i=settlementServices.insert(settlement);
        Stayback stayback=new Stayback();
        stayback.setStForm(stform);
        stayback.setStReason(streason);
        stayback.setStTime(new Timestamp(System.currentTimeMillis()));
        Settlement s=settlementServices.selectByseid(i);
        stayback.setSettlementBySeId(s);
        stayback.setUserByUid(u);
        Integer m=staybackServices.insert(stayback);
        return m;
    }
}
