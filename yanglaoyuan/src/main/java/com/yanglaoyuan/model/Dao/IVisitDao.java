package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description: 来访记录Dao
 * @author: ChenZhiPeng
 * @create: 2020-12-23 09:53
 */
@Repository
public interface IVisitDao extends CrudRepository<Visit,Integer> {
}
