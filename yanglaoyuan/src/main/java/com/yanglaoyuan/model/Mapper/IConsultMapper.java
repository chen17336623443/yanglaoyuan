package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Consult;
import com.yanglaoyuan.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IConsultMapper {
    List<Consult> consultall();

}
