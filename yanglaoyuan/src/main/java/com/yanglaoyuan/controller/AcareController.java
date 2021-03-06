package com.yanglaoyuan.controller;

import com.github.pagehelper.PageInfo;
import com.yanglaoyuan.pojo.Acare;
import com.yanglaoyuan.pojo.Oldman;
import com.yanglaoyuan.service.AcareService;
import com.yanglaoyuan.service.OldmanServices;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: JiaWei Liu
 * @create: 2020-12-21 16:02
 */
@RestController
@RequestMapping("/aca")
public class AcareController {
    @Autowired
    AcareService as;

    @Autowired
    OldmanServices os;


    /**
     * 护工查询所有分页查询地址
     *
     * @param num
     * @return
     */
    @GetMapping("/all")
    public PageInfo<Acare> selectall(Integer num) {
        return as.selectall(num);
    }

    /**
     * 根据护工id查询照顾的老人
     *
     * @param acid
     * @return
     */
    @GetMapping("/byid")
    public List<Oldman> selectbyid(Integer acid) {
        return os.selectoldmanByid(acid);
    }

    /**
     * 护工模糊条件查询
     *
     * @param num
     * @param acname
     * @param acphone
     * @return
     */
    @GetMapping("/like")
    public PageInfo<Acare> selectlike(Integer num, String acname, String acphone) {
        if (acname == "undefined" || acname.equals("undefined")) {
            return as.selectlike(num, "", acphone.trim());
        } else if (acphone == "undefined" || acphone.equals("undefined")) {
            return as.selectlike(num, acname.trim(), "");
        } else {
            return as.selectlike(num, acname.trim(), acphone.trim());
        }

    }

    /**
     * 添加新护工
     *
     * @param acare
     * @return
     */
    @PostMapping("/add")
    public String add(@RequestBody Acare acare) {
        try {
            as.add(acare);
            return "添加护工成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "添加护工失败！" + e;
        }
    }

    @GetMapping("/del")
    public String del(Integer acid) {
        return as.del(acid) == 1 ? "护工离职成功！" : "护工离职失败！";
    }
}
