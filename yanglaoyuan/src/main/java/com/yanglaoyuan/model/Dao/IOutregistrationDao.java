package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Outregistration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description: 外出登记表Dao
 * @author: ChenZhiPeng
 * @create: 2020-12-21 20:57
 */
@Repository
public interface IOutregistrationDao extends CrudRepository<Outregistration,Integer> {
}
