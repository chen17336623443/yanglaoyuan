package com.yanglaoyuan.service;

import com.yanglaoyuan.model.Dao.INursingtypeDao;
import com.yanglaoyuan.model.Mapper.INursingtypeMapper;
import com.yanglaoyuan.pojo.Nursingtype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 护理类型Service层
 * @author: JiaWei Liu
 * @create: 2020-12-22 21:29
 */
@Service
public class NursingtypeServices {
    @Autowired
    INursingtypeMapper nm;

    @Autowired
    INursingtypeDao nd;

    //护理类别查询所有
    public List<Nursingtype> selectall() {
        return nm.selectall();
    }


    //根据主键查询护理类别
    public Nursingtype selectbyid(Integer ntid) {
        return nm.selectbyid(ntid);
    }

    //新增护理类别
    public Integer add(Nursingtype nt){
        try {
            nd.save(nt);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    //修改护理类别
    public Integer updateNur(String name,String other,Integer ntid){
        return nm.updateNur(name, other, ntid);
    }

}
