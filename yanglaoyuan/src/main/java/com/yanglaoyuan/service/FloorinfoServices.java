package com.yanglaoyuan.service;

import com.yanglaoyuan.model.Dao.*;
import com.yanglaoyuan.model.Mapper.IFloorinfoMapper;
import com.yanglaoyuan.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: LiuJingZhao
 * @create: 2020-12-24 09:45
 */
@Service
public class FloorinfoServices {
    @Autowired
    IFloorinfoMapper mapper;

    @Autowired
    IFloorinfoDao dao;

    @Autowired
    IPublicshouseDao publicshouseDao;
    @Autowired
    ICulturalhouseDao culturalhouseDao;
    @Autowired
    IEquipmenthouseDao equipmenthouseDao;
    @Autowired
    ISafetyhouseDao safetyhouseDao;

    public int closepublicshouses(Integer id){
        publicshouseDao.deleteById(id);
        return 1;
    }
    public int closeculturalhouses(Integer id){
        culturalhouseDao.deleteById(id);
        return 1;
    }
    public int closeequipmenthouse(Integer id){
        equipmenthouseDao.deleteById(id);
        return 1;
    }
    public int closesafetyhouse(Integer id){
        safetyhouseDao.deleteById(id);
        return 1;
    }


    public Floorinfo insert(Floorinfo floorinfo){
        return dao.save(floorinfo);
    }

    public Publicshouse insertpublicshouseDao(Publicshouse publicshouse){
        return publicshouseDao.save(publicshouse);
    }
    public Culturalhouse insertculturalhouseDao(Culturalhouse culturalhouse){
        return culturalhouseDao.save(culturalhouse);
    }
    public Equipmenthouse insertequipmenthouseDao(Equipmenthouse equipmenthouse){
        return equipmenthouseDao.save(equipmenthouse);
    }
    public Safetyhouse insertsafetyhouseDao(Safetyhouse safetyhouse){
        return safetyhouseDao.save(safetyhouse);
    }
}
