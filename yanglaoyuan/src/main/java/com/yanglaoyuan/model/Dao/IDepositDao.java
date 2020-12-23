package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Deposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description: 预存管理DAO接口
 * @author: chenxing
 * @create: 2020-12-21 16:23
 **/
@Repository
public interface IDepositDao extends CrudRepository<Deposit,Integer> {



}
