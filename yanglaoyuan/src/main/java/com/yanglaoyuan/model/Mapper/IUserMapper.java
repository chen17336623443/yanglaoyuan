package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IUserMapper {
    User login(String phone,String upass);


    /*根据用户名查询用户信息*/

    User byuphone(String name);
}
