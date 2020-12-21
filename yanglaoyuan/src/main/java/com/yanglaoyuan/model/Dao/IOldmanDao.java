package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Oldman;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description: 老人Dao接口
 * @author: ChenZhiPeng
 * @create: 2020-12-21 16:15
 */
@Repository
public interface IOldmanDao extends CrudRepository<Oldman,Integer> {
}
