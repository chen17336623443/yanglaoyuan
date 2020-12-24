package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Stayback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description: 退住登记Dao
 * @author: ChenZhiPeng
 * @create: 2020-12-24 10:03
 */
@Repository
public interface IStaybackDao extends CrudRepository<Stayback,Integer>{
}
