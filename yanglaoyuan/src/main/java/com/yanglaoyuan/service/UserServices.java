package com.yanglaoyuan.service;

import com.yanglaoyuan.model.Mapper.IUserMapper;
import com.yanglaoyuan.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    IUserMapper um;
    public User login(String phone,String upass){
        return um.login(phone, upass);
    }

    public  User byuphone(String name){
        return  um.byuphone(name);
    }
}
