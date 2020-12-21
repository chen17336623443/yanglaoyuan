package com.yanglaoyuan.controller;

import com.yanglaoyuan.pojo.Floor;
import com.yanglaoyuan.pojo.MyResult;
import com.yanglaoyuan.service.FloorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: yanglaoyuan
 * @description:楼层信息
 * @author: LiuJingZhao
 * @create: 2020-12-21 20:15
 */
@RestController
@RequestMapping("/floor")
public class FloorController {
    @Autowired
    FloorServices services;

    @RequestMapping("/tree")
    public Map<String,Object> floorTree() {
        Map<String,Object> map = new HashMap<>();
        List<Floor> floors = services.selectAll();
        map.put("floors",floors);
        System.out.println(floors);
        return map;
    }
}
