package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Nursingrecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description: 老人护理记录Dao
 * @author: JiaWei Liu
 * @create: 2020-12-24 16:05
 */
@Repository
public interface INursingrecordDao extends CrudRepository<Nursingrecord,Integer> {
}
