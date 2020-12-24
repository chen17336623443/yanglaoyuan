package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Consult;
import com.yanglaoyuan.pojo.Otman;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IotmanMapper {

/*根据健康信息id查询疾病中间表信息*/
List<Otman> bythId(Integer hid);
}
