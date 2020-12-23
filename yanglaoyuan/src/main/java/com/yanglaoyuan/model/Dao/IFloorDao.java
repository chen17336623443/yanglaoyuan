package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Floor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description: Floor
 * @author: LiuJingZhao
 * @create: 2020-12-22 16:47
 */
@Repository
public interface IFloorDao extends CrudRepository<Floor,Integer> {

}
