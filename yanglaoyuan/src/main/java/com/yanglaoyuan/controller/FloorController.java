package com.yanglaoyuan.controller;

import com.yanglaoyuan.pojo.Bed;
import com.yanglaoyuan.pojo.Floor;
import com.yanglaoyuan.pojo.MyResult;
import com.yanglaoyuan.service.BedServices;
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
    @Autowired
    BedServices bedServices;

    @RequestMapping("selectByFtype")
    /**
     *@Description 方法是:selectByFtype
     *@Param 参数是:[]
     *@Return 返回类型是:java.util.List<com.yanglaoyuan.pojo.Floor>
     *@Author LiuJingZhao
     *@Date 2020/12/24 10:28
     * 根据Ftype=1查询所有的楼栋
     */
    public List<Floor> selectByFtype(){
        List<Floor> floors = services.selectByFtype();
        return floors;
    }

    @RequestMapping("updateNameByFid")
    /**
     *@Description 方法是:updateNameByFid
     *@Param 参数是:[fid, fname]
     *@Return 返回类型是:int
     *@Author LiuJingZhao
     *@Date 2020/12/23 19:43
     */
    public int updateNameByFid(@RequestParam("fid")Integer fid,@RequestParam("fname")String fname){
        int i = services.updateNameByFid(fid,fname);
        return i;
    }

    @RequestMapping("deldetByFidcw")
    /**
     *@Description 方法是:deldetByFidfj
     *@Param 参数是:[fid]
     *@Return 返回类型是:int
     *@Author LiuJingZhao
     *@Date 2020/12/23 23:55
     */
    public int deldetByFidcw(@RequestParam("bid")Integer bid){
         int i = bedServices.delbybid(bid);
         return i;
    }


    @RequestMapping("deldetByFidfj")
    /**
     *@Description 方法是:deldetByFidfj
     *@Param 参数是:[fid]
     *@Return 返回类型是:int
     *@Author LiuJingZhao
     *@Date 2020/12/23 20:34
     */
    public int deldetByFidfj(@RequestParam("fid")Integer fid){
        System.out.println(fid);
        List<Floor> floors = services.selectByFidfj(fid);
        System.out.println(floors.size());
        if(floors.size()!=0){
            return 1;
        }else{
            bedServices.del(fid);
            services.deldetByFid(fid);
            return 2;
        }
    }

    @RequestMapping("deldetByFidlc")
    /**
     *@Description 方法是:deldetByFidlc
     *@Param 参数是:[fid]
     *@Return 返回类型是:int
     *@Author LiuJingZhao
     *@Date 2020/12/23 20:34
     */
    public int deldetByFidlc(@RequestParam("fid")Integer fid){
        System.out.println(fid);
        List<Floor> floors = services.selectByFidlc(fid);
        System.out.println(floors.size());
        if(floors.size()!=0){
            return 1;
        }else{
            services.deldetByFid(fid);
            return 2;
        }
    }

    @RequestMapping("deldetByFid")
    /**
     *@Description 方法是:deldetByFid
     *@Param 参数是:[fid]
     *@Return 返回类型是:int
     *@Author LiuJingZhao
     *@Date 2020/12/23 20:34
     */
    public int deldetByFid(@RequestParam("fid")Integer fid){
        System.out.println(fid);
        List<Floor> floors = services.selectByFid(fid);
        System.out.println(floors.size());
        if(floors.size()!=0){
            return 1;
        }else{
            services.deldetByFid(fid);
            return 2;
        }

    }


    @RequestMapping("selectByFid")
    /**
     *@Description 方法是:selectByFid
     *@Param 参数是:[fid]
     *@Return 返回类型是:java.util.List<com.yanglaoyuan.pojo.Bed>
     *@Author LiuJingZhao
     *@Date 2020/12/23 15:47
     * 根据fid房间id 查找床位
     */
    public List<Bed> selectByFid(Integer fid){
        List<Bed> beds = bedServices.selectByFid(fid);
        return beds;
    }



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

    @RequestMapping("insertChuangwei")
    /**
     *@Description 方法是:insertChuangwei
     *@Param 参数是:[fname, fjnumber, lcnumber, lcstyle, mchz, mcqz, fid, cwnumber, cwtype]
     *@Return 返回类型是:int
     *@Author LiuJingZhao
     *@Date 2020/12/23 19:08
     */
    public int insertChuangwei(@RequestParam("fname")String fname,@RequestParam("fjnumber")Integer fjnumber,
                              @RequestParam("lcnumber")Integer lcnumber,@RequestParam("lcstyle")Integer lcstyle,@RequestParam("mchz")String mchz,
                              @RequestParam("mcqz")String mcqz, @RequestParam("fid")Integer fid,@RequestParam("cwnumber")Integer cwnumber,@RequestParam("cwtype")Integer cwtype){

        int bed =  bedServices.insert(cwtype,fid,fname);

        return bed;
    }

    @RequestMapping("insertFangjian")
    /**
     *@Description 方法是:insertLouchen
     *@Param 参数是:[fname, fjnumber, lcnumber, lcstyle, mchz, mcqz]
     *@Return 返回类型是:int
     *@Author LiuJingZhao
     *@Date 2020/12/22 21:31
     */
    public int insertFangjian(@RequestParam("fname")String fname,@RequestParam("fjnumber")Integer fjnumber,
             @RequestParam("lcnumber")Integer lcnumber,@RequestParam("lcstyle")Integer lcstyle,@RequestParam("mchz")String mchz,
             @RequestParam("mcqz")String mcqz, @RequestParam("fid")Integer fid,@RequestParam("cwnumber")Integer cwnumber,@RequestParam("cwtype")Integer cwtype){
        /*新增楼房 并且获取刚刚新增的楼房的主键id*/
        Floor f = new Floor();
        f.setFid(null);
        f.setFname(fname);
        f.setFdad(fid);
        f.setFtype(3);
        Floor floor = services.insertLou2(f);
        for (int k = 1; k <= cwnumber; k++) {
            int bed =  bedServices.insert(cwtype,floor.getFid(),""+k);
        }
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
                             @RequestParam("mcqz")String mcqz, @RequestParam("fid")Integer fid,@RequestParam("cwnumber")Integer cwnumber,@RequestParam("cwtype")Integer cwtype){
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
                for (int k = 1; k <= cwnumber; k++) {
                    int bed =  bedServices.insert(cwtype,floorfj.getFid(),""+k);
                }
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
                             @RequestParam("mcqz")String mcqz,@RequestParam("cwnumber")Integer cwnumber,@RequestParam("cwtype")Integer cwtype){
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
                for (int k = 1; k <= cwnumber; k++) {
                        int bed =  bedServices.insert(cwtype,floorfj.getFid(),""+k);
                }
            }
        }
        return 1;
    }
}
