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
    /**
     *@Description 方法是:floorTree floor的树形数据
     *@Param 参数是:[]
     *@Return 返回类型是:java.util.Map<java.lang.String,java.lang.Object>
     *@Author LiuJingZhao
     *@Date 2020/12/22 9:21
     */
    public Map<String,Object> floorTree() {
        Map<String,Object> map = new HashMap<>();
        List<Floor> floors = services.selectAll();
        map.put("floors",floors);
        System.out.println(floors);
        return map;
    }


    @RequestMapping("insertFangjian")
    public int insertFangjian(@RequestParam("fname")String fname,@RequestParam("fjnumber")Integer fjnumber,
             @RequestParam("lcnumber")Integer lcnumber,@RequestParam("lcstyle")Integer lcstyle,@RequestParam("mchz")String mchz,
             @RequestParam("mcqz")String mcqz, @RequestParam("fid")Integer fid){
        /*新增楼房 并且获取刚刚新增的楼房的主键id*/
        Floor f = new Floor();
        f.setFid(null);
        f.setFname(fname);
        f.setFdad(fid);
        f.setFtype(3);
        Floor floor = services.insertLou2(f);
        return 1;
    }
    @RequestMapping("insertLouchen")
    /**
     *@Description 方法是:insertLouchen
     *@Param 参数是:[fname, fjnumber, lcnumber, lcstyle, mchz, mcqz]
     *@Return 返回类型是:int
     *@Author LiuJingZhao
     *@Date 2020/12/22 20:41
     */
    public int insertLouchen(@RequestParam("fname")String fname,@RequestParam("fjnumber")Integer fjnumber,
                             @RequestParam("lcnumber")Integer lcnumber,@RequestParam("lcstyle")Integer lcstyle,@RequestParam("mchz")String mchz,
                             @RequestParam("mcqz")String mcqz, @RequestParam("fid")Integer fid){
        /*新增楼房 并且获取刚刚新增的楼房的主键id*/
        Floor f = new Floor();
            f.setFid(null);
            f.setFname(fname);
            f.setFdad(fid);
            f.setFtype(2);
            Floor floor = services.insertLou2(f);
            /*根据for循环 新增房间*/
            for (int i = 1; i <= fjnumber; i++) {
                Floor ffj = new Floor();
                if(i<10){
                    ffj.setFname(mcqz+0+i+mchz);
                }else{
                    ffj.setFname(mcqz+i+mchz);
                }
                ffj.setFid(null);
                ffj.setFdad(floor.getFid());
                ffj.setFtype(3);
                Floor floorfj =services.insertLou2(ffj);
            }
            return 1;
    }

    @RequestMapping("insertLoudong")
    /**
     *@Description 方法是:insertLoudong
     *@Param 参数是:[fname, fjnumber, fjstyle, lcnumber, lcstyle, mchz, mcqz]
     *@Return 返回类型是:int
     *@Author LiuJingZhao
     *@Date 2020/12/22 19:33
     */
    public int insertLoudong(@RequestParam("fname")String fname,@RequestParam("fjnumber")Integer fjnumber,
                             @RequestParam("lcnumber")Integer lcnumber,@RequestParam("lcstyle")Integer lcstyle,@RequestParam("mchz")String mchz,
                             @RequestParam("mcqz")String mcqz){
        /*新增楼房 并且获取刚刚新增的楼房的主键id*/
        Floor f = new Floor();
        f.setFid(null);
        f.setFname(fname);
        f.setFdad(36);
        f.setFtype(1);
        Floor floor = services.insertLou2(f);
        /*根据for循环 新增楼层*/
        for (int i = 1; i <= lcnumber; i++) {
            Floor flc = new Floor();

            /*<el-option label="第N层" value="1"></el-option>
               <el-option label="楼名N层" value="2"></el-option>*/
            if(lcstyle == 2){
                flc.setFname(fname+i+"层");
            }else{
                flc.setFname("第"+i+"层");
            }
            flc.setFid(null);
            flc.setFdad(floor.getFid());
            flc.setFtype(2);
            Floor floorLc = services.insertLou2(flc);
            /*根据for循环 新增每层楼的房间number*/
            for (int j = 1; j <= fjnumber; j++) {
                Floor ffj = new Floor();

                if(j<10){
                    ffj.setFname(mcqz+i+0+j+mchz);
                }else{
                    ffj.setFname(mcqz+i+j+mchz);
                }
                ffj.setFid(null);
                ffj.setFdad(floorLc.getFid());
                ffj.setFtype(3);
                Floor floorfj =services.insertLou2(ffj);
            }
        }
        return 1;
    }
}
