package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Nursing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description: 护理实体JPADao接口
 * @author: JiaWei Liu
 * @create: 2020-12-22 21:44
 */
@Repository
public interface INursingDao extends CrudRepository<Nursing,Integer> {
}
