package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Dao.ISettlementDao;
import com.yanglaoyuan.model.Mapper.ISettlementMapper;
import com.yanglaoyuan.pojo.Settlement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenZhiPeng
 * @date 2020/12/24 10:25
 */
@Repository
@Service
public class SettlementServices {
    @Autowired
    ISettlementMapper mapper;
    @Autowired
    ISettlementDao dao;

    public PageInfo<Settlement> selectByPage(Integer no,Integer size,String tomname){
        PageHelper.startPage(no,size);
        List<Settlement> list=mapper.selectByPage(tomname);
        PageInfo<Settlement> info=new PageInfo<>(list);
        return info;
    }

    public Integer insert(Settlement settlement){
        return dao.save(settlement).getSeId();
    }

    public Settlement selectByseid(Integer seid){
        return mapper.selectByseid(seid);
    }
}
