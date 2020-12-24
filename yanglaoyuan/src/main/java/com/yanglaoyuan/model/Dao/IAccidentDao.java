package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Accident;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description: 意外登记Dao
 * @author: ChenZhiPeng
 * @create: 2020-12-23 19:16
 */
@Repository
public interface IAccidentDao extends CrudRepository<Accident,Integer> {
}
