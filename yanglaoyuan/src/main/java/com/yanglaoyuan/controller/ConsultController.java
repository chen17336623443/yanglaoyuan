package com.yanglaoyuan.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Consult;
import com.yanglaoyuan.pojo.MyResult;
import com.yanglaoyuan.pojo.User;
import com.yanglaoyuan.pojo.Yanzhen;
import com.yanglaoyuan.service.ConsultServices;
import com.yanglaoyuan.service.UserServices;
import com.yanglaoyuan.service.yanzhenServices;
import com.yanglaoyuan.utils.ResultUtil;
import com.yanglaoyuan.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consult")
public class ConsultController {
    @Autowired
    ConsultServices services;
    @Autowired
    UserServices userServices;
@RequestMapping("all")
    public PageInfo<Consult> consultall(@RequestParam("no") Integer pageNum ,@RequestParam("size") Integer pageSize,@RequestParam("name") String name,@RequestParam("begin") String begin, @RequestParam("end") String end){
        Integer pageNo = (pageNum!=null && pageNum>=1)?pageNum:1;
        Integer size = (pageSize!=null)?pageSize:10;
        return services.consultall(pageNo,size,name,begin,end);
    }

/*新增咨询信息*/
    @RequestMapping("inserconsult")
    public  Integer insertcount(@RequestBody Consult d){
        User u=userServices.byuphone(d.getMyuser().getUname());
        d.setMyuser(u);
    return  services.inserconsult(d);
    }


    @RequestMapping("updateconsult")
    public  Integer updateconsult(@RequestBody Consult d){
        return  services.updateconsult(d);
    }


    /*根据主键出删除咨询信息*/
    @RequestMapping("deleteconsult")
    public  Integer  delectconsult(Integer cid){

        return  services.delectconsult(cid);
    }

}
