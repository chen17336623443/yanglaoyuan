package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Publicshouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: LiuJingZhao
 * @create: 2020-12-24 14:52
 */
@Repository
public interface IPublicshouseDao extends CrudRepository<Publicshouse,Integer> {
}
