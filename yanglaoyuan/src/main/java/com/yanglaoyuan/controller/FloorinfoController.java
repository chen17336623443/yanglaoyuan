package com.yanglaoyuan.controller;

import com.yanglaoyuan.pojo.*;
import com.yanglaoyuan.service.FloorServices;
import com.yanglaoyuan.service.FloorinfoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: LiuJingZhao
 * @create: 2020-12-24 09:45
 */
@RestController
@RequestMapping("/floorinfo")
public class FloorinfoController {
    @Autowired
    FloorinfoServices services;

    @Autowired
    FloorServices floorservices;


    @RequestMapping("closepublicshouses")
    public  int closepublicshouses(@RequestParam("id")Integer id){
        return services.closepublicshouses(id);
    }

    @RequestMapping("closeculturalhouses")
    public  int closeculturalhouses(@RequestParam("id")Integer id){
        return services.closeculturalhouses(id);
    }
    @RequestMapping("closeequipmenthouse")
    public  int closeequipmenthouse(@RequestParam("id")Integer id){
        return services.closeequipmenthouse(id);
    }
    @RequestMapping("closesafetyhouse")
    public  int closesafetyhouse(@RequestParam("id")Integer id){
        return services.closesafetyhouse(id);
    }


    @RequestMapping("insert")
    /**
     *@Description 方法是:insertChuangwei
     *@Param 参数是:[fid]
     *@Return 返回类型是:int
     *@Author LiuJingZhao
     *@Date 2020/12/24 14:40
     */
    public Floorinfo insert(@RequestParam("fid")Integer fid,@RequestParam("chname")String chname){

        Floor floor = floorservices.selectfid(fid);
        Floorinfo floorinfo = new Floorinfo();
        floorinfo.setFiid(null);
        floorinfo.setFloorByFid(floor);
        floorinfo.setFiinfo(null);
        Floorinfo floorinfo1 = services.insert(floorinfo);

        Publicshouse publicshouse = new Publicshouse();
        publicshouse.setChname(chname);
        publicshouse.setPhid(null);
        publicshouse.setFloorinfoByFiid(floorinfo1);
        services.insertpublicshouseDao(publicshouse);
        System.out.println(floorinfo1.getFiid());
        return floorinfo1;

    }

    @RequestMapping("insertculturalhouses")
    public Floorinfo insertculturalhouses(@RequestParam("fid")Integer fid,@RequestParam("chname")String chname){

        Floor floor = floorservices.selectfid(fid);
        Floorinfo floorinfo = new Floorinfo();
        floorinfo.setFiid(null);
        floorinfo.setFloorByFid(floor);
        floorinfo.setFiinfo(null);
        Floorinfo floorinfo1 = services.insert(floorinfo);

        Culturalhouse culturalhouse = new Culturalhouse();
        culturalhouse.setChid(null);
        culturalhouse.setChname(chname);
        culturalhouse.setFloorinfoByFiid(floorinfo1);
        services.insertculturalhouseDao(culturalhouse);
        return floorinfo1;

    }

    @RequestMapping("insertequipmenthouse")
    public Floorinfo insertequipmenthouse(@RequestParam("fid")Integer fid,@RequestParam("chname")String chname){

        Floor floor = floorservices.selectfid(fid);
        Floorinfo floorinfo = new Floorinfo();
        floorinfo.setFiid(null);
        floorinfo.setFloorByFid(floor);
        floorinfo.setFiinfo(null);
        Floorinfo floorinfo1 = services.insert(floorinfo);
        Equipmenthouse equipmenthouse = new Equipmenthouse();
        equipmenthouse.setEhid(null );
        equipmenthouse.setShname(chname);
        equipmenthouse.setFloorinfoByFiid(floorinfo1);
        services.insertequipmenthouseDao(equipmenthouse);
        return floorinfo1;

    }
    @RequestMapping("insertsafetyhouse")
    public Floorinfo insertsafetyhouse(@RequestParam("fid")Integer fid,@RequestParam("chname")String chname){

        Floor floor = floorservices.selectfid(fid);
        Floorinfo floorinfo = new Floorinfo();
        floorinfo.setFiid(null);
        floorinfo.setFloorByFid(floor);
        floorinfo.setFiinfo(null);
        Floorinfo floorinfo1 = services.insert(floorinfo);
        Safetyhouse safetyhouse = new Safetyhouse();
        safetyhouse.setShid(null);
        safetyhouse.setShname(chname);
        safetyhouse.setFloorinfoByFiid(floorinfo1);
        services.insertsafetyhouseDao(safetyhouse);
        return floorinfo1;

    }

}
