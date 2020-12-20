package com.yanglaoyuan.controller;

import com.alibaba.fastjson.JSONObject;
import com.yanglaoyuan.pojo.MyResult;
import com.yanglaoyuan.pojo.User;
import com.yanglaoyuan.pojo.Yanzhen;
import com.yanglaoyuan.service.UserServices;
import com.yanglaoyuan.service.yanzhenServices;
import com.yanglaoyuan.utils.ResultUtil;
import com.yanglaoyuan.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServices us;
    @Autowired
    yanzhenServices ys;

    //登录
    @PostMapping("/login")
    public MyResult<Object> selectByname(@RequestBody User user) {
        User user1 = us.login(user.getUphone(),user.getUpass());
        MyResult<Object> res;
        if (null != user1) {
            String token = Token.createToken(user1.getUname());
            ys.del(user1.getUphone());
            Yanzhen yanzhen = new Yanzhen();
            yanzhen.setToken(token);
            yanzhen.setEname(user1.getUphone());
            ys.add(yanzhen);
            JSONObject object = new JSONObject();
            object.put("token", token);
            object.put("user", user1);
            res = ResultUtil.OK(object, "登录成功");
            return res;
        }
        res = ResultUtil.ERR("登录失败");
        return res;
    }

    //删除token
    @RequestMapping("/deltoken")
    public Integer del(String ename){
        return ys.del(ename);
    }
}
