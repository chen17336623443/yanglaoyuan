package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Bed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: LiuJingZhao
 * @create: 2020-12-23 14:27
 */
@Repository
public interface  IBedDao  extends CrudRepository<Bed,Integer> {

}
