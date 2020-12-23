package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Bedtype;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: LiuJingZhao
 * @create: 2020-12-23 09:40
 */
@Repository
public interface IBedTypeDao extends CrudRepository<Bedtype,Integer> {

}
