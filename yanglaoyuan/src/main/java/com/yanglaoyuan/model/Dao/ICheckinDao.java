package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Checkin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description: 入住缴费DAO接口
 * @author: chenxing
 * @create: 2020-12-21 21:19
 **/
@Repository
public interface ICheckinDao extends CrudRepository<Checkin,Integer> {



}
