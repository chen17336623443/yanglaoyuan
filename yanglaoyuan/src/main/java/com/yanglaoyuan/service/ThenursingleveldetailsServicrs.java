package com.yanglaoyuan.service;

import com.yanglaoyuan.model.Dao.IThenursingleveldetailsDao;
import com.yanglaoyuan.model.Mapper.IThenursingleveldetailsMapper;
import com.yanglaoyuan.pojo.Thenursingleveldetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: JiaWei Liu
 * @create: 2020-12-24 01:33
 */
@Service
public class ThenursingleveldetailsServicrs {
    @Autowired
    IThenursingleveldetailsMapper itmapper;
    @Autowired
    IThenursingleveldetailsDao itdao;

    public Integer add(Thenursingleveldetails t){
        try {
            itdao.save(t);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
