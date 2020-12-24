package com.yanglaoyuan.model.Mapper;

import com.yanglaoyuan.pojo.Consult;
import com.yanglaoyuan.pojo.Relation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IRelationMapper {

/*新增家属信息*/
  Integer addrelation(@Param("d")Relation d);

  /*根据老人id查询老人家属列表*/

    List<Relation> selectbyomid(Integer omid);



/*删除老人家属信息*/
  Integer delete(Integer id);





}
