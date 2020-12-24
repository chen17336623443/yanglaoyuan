package com.yanglaoyuan.model.Dao;

import com.yanglaoyuan.pojo.Settlement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: yanglaoyuan
 * @description: 退住结算Dao
 * @author: ChenZhiPeng
 * @create: 2020-12-24 09:53
 */
@Repository
public interface ISettlementDao extends CrudRepository<Settlement,Integer> {
}
