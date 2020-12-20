package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Yanzhen;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface yanzhenMapper {
    Integer del(String ename);

    Yanzhen select(String token);
}
