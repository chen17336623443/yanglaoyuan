package com.yanglaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.model.Dao.INursingDao;
import com.yanglaoyuan.model.Mapper.INursingMapper;
import com.yanglaoyuan.pojo.Nursing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 护理实体Service层
 * @author: JiaWei Liu
 * @create: 2020-12-22 21:47
 */
@Service
public class NursingServices {
    @Autowired
    INursingMapper um;

    @Autowired
    INursingDao nd;

    /**
     * 根据护理类别查询护理
     *
     * @param num
     * @param id
     * @return
     */
    public PageInfo<Nursing> selectbyid(Integer num, Integer size, Integer id) {
        PageHelper.startPage(num, size);
        List<Nursing> selectbyid = um.selectbyid(id);
        PageInfo<Nursing> nursingPageInfo = new PageInfo<>(selectbyid);
        return nursingPageInfo;
    }

    /**
     * 查询所有护理分页
     *
     * @param num
     * @return
     */
    public PageInfo<Nursing> selectall(Integer num, Integer size) {
        PageHelper.startPage(num, size);
        List<Nursing> selectall = um.selectall();
        PageInfo<Nursing> nursingPageInfo = new PageInfo<>(selectall);
        return nursingPageInfo;
    }

    public List<Nursing> selectAllall(){
        return um.selectall();
    }

    /**
     * 新增护理项目
     * @param nursing
     * @return
     */
    public Integer add(Nursing nursing){
        try {
            nd.save(nursing);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 删除护理项目
     * @param id
     * @return
     */
    public Integer del(Integer id){
        return um.del(id);
    }

    /**
     * 修改护理项目
     * @param nuname
     * @param numonry
     * @param nutime
     * @param nuother
     * @param nuid
     * @return
     */
    public Integer upd(String nuname,String numonry,String nutime,String nuother,Integer nuid){
        return um.upd(nuname, numonry, nutime, nuother, nuid);
    }

    public Nursing selectid(Integer id){
        return um.selectid(id);
    }

}
