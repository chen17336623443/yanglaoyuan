package com.yanglaoyuan.service;

import com.yanglaoyuan.model.Dao.IThenursinglevelDao;
import com.yanglaoyuan.model.Mapper.IThenursinglevelMapper;
import com.yanglaoyuan.pojo.Thenursinglevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 护理级别Service层
 * @author: JiaWei Liu
 * @create: 2020-12-23 17:22
 */
@Service
public class ThenursinglevelService {
    @Autowired
    IThenursinglevelMapper tm;
    @Autowired
    IThenursinglevelDao td;

    //护理级别查询所有
    public List<Thenursinglevel> selectall(){
        return tm.selectall();
    }

    //根据主键查询护理级别
    public Thenursinglevel selectbyid(Integer id){
       return tm.selectbyid(id);
    }

    //新增护理级别
    public Integer add(Thenursinglevel t){
        try {
            td.save(t);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
