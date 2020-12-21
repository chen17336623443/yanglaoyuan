package com.yanglaoyuan.service;

import com.yanglaoyuan.model.Dao.yanzhenDao;
import com.yanglaoyuan.model.Mapper.IyanzhenMapper;
import com.yanglaoyuan.pojo.Yanzhen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class yanzhenServices {
    @Autowired
    yanzhenDao ydao;
    @Autowired
    IyanzhenMapper ym;

    public Integer add(Yanzhen yz){
        try {
            ydao.save(yz);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Integer del(String ename){
        return ym.del(ename);
    }


    public Yanzhen select(String token){
        return ym.select(token);
    }
}
