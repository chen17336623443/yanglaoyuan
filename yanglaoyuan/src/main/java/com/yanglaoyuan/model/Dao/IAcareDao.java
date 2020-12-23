package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Acare;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: JiaWei Liu
 * @create: 2020-12-21 15:54
 */
@Repository
public interface IAcareDao extends CrudRepository<Acare,Integer> {
}
