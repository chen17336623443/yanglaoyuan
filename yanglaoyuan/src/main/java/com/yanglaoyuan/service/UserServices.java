package com.yanglaoyuan.service;

import com.yanglaoyuan.model.Mapper.UserMapper;
import com.yanglaoyuan.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    UserMapper um;
    public User login(String phone,String upass){
        return um.login(phone, upass);
    }
}
