package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Equipmenthouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: LiuJingZhao
 * @create: 2020-12-24 15:14
 */
@Repository
public interface  IEquipmenthouseDao extends CrudRepository<Equipmenthouse,Integer> {
}
