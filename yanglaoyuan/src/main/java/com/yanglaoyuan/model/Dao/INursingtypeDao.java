package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Nursingtype;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description: 护理类型JPADao层
 * @author: JiaWei Liu
 * @create: 2020-12-22 21:30
 */
@Repository
public interface INursingtypeDao extends CrudRepository<Nursingtype,Integer> {
}
