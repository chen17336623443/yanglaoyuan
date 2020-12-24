package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Floorinfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: LiuJingZhao
 * @create: 2020-12-24 14:32
 */
@Repository
public interface  IFloorinfoDao extends CrudRepository<Floorinfo,Integer> {
}
