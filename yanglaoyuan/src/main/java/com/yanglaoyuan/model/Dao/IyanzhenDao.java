package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Yanzhen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IyanzhenDao extends CrudRepository<Yanzhen,Integer> {
}
