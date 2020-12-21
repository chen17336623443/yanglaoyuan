package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IConsultMapper {
    User login(String phone,String upass);
}
