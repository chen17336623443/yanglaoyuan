package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Consult;
import com.yanglaoyuan.pojo.Health;
import com.yanglaoyuan.pojo.Oldman;
import com.yanglaoyuan.pojo.User;
import com.yanglaoyuan.service.ConsultServices;
import com.yanglaoyuan.service.UserServices;
import com.yanglaoyuan.service.healthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/health")
public class HealthController {
    @Autowired
    healthServices services;
    @Autowired
    UserServices userServices;
@RequestMapping("byomid")
    public List<Health> byomid(@RequestParam("omid") Integer omid){
    return  services.byomid(omid);
}


/*新增或者修改健康信息*/
    @RequestMapping("save")
    public  Health save(@RequestBody Health d){
      User u=  new User();
      u.setUid(1);
        d.setUserByUid(u);
        Oldman o=new Oldman();
        o.setOmId(1);

        d.setOldmanByOmId(o);



        return  services.save(d);

    }



}
