package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Register;
import org.springframework.data.repository.CrudRepository;

/**
 * @program: yanglaoyuan
 * @description: 缴费登记Dao接口
 * @author: chenxing
 * @create: 2020-12-23 09:33
 **/
public interface IRegisterDao extends CrudRepository<Register,Integer> {



}
