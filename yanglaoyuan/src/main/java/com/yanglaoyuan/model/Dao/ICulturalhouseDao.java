package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Culturalhouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: LiuJingZhao
 * @create: 2020-12-24 15:13
 */
@Repository
public interface  ICulturalhouseDao extends CrudRepository<Culturalhouse,Integer> {
}
