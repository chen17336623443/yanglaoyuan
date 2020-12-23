package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Costflow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description: 费用流水Dao接口
 * @author: chenxing
 * @create: 2020-12-22 11:24
 **/
@Repository
public interface ICostflowDao extends CrudRepository<Costflow,Integer> {



}
