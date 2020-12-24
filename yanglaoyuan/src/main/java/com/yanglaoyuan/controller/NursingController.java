package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Nursing;
import com.yanglaoyuan.service.NursingServices;
import com.yanglaoyuan.service.NursingtypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description: 护理实体Controller层
 * @author: JiaWei Liu
 * @create: 2020-12-22 21:48
 */
@RestController
@RequestMapping("/nurc")
public class NursingController {
    @Autowired
    NursingServices ns;

    @Autowired
    NursingtypeServices nt;

    /**
     * 根据护理类别查询护理
     * @param num
     * @param id
     * @return
     */
    @RequestMapping("/byid")
    public PageInfo<Nursing> selectbyid(Integer num,Integer size, Integer id){
        return ns.selectbyid(num,size, id);
    }

    /**
     * 查询所有护理
     * @param num
     * @param size
     * @return
     */
    @RequestMapping("/all")
    public PageInfo<Nursing> selectall(Integer num,Integer size){
        return ns.selectall(num,size);
    }

    @PostMapping("/add/{ntid}")
    public String add(@RequestBody Nursing nursing, @PathVariable("ntid")Integer ntid){
        nursing.setNursingtypeByNtId(nt.selectbyid(ntid));
        return ns.add(nursing)==1?"新增护理项目成功！":"新增护理项目失败！";
    }

    @RequestMapping("/del")
    public String del(Integer id){
        return ns.del(id)==1?"护理项目删除成功！":"护理项目删除失败！";
    }

    @PostMapping("/upd")
    public String upd(@RequestBody Nursing n){
        return ns.upd(n.getNuName(),String.valueOf(n.getNuMonry()),n.getNuTime(),n.getNuOther(),n.getNuId())==1?"护理项目修改成功！":"护理项目修改失败！";
    }

    @RequestMapping("/allall")
    public List<Nursing> selectallall(){
        return ns.selectAllall();
    }

    @RequestMapping("/idall")
    public Nursing selectbynuid(Integer nuid){
        return ns.selectbynuid(nuid);
    }
}
