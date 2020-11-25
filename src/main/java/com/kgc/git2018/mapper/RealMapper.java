package com.kgc.git2018.mapper;

import com.kgc.git2018.pojo.Real;
import com.kgc.git2018.pojo.RealExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RealMapper {
    int countByExample(RealExample example);

    int deleteByExample(RealExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Real record);

    int insertSelective(Real record);

    List<Real> selectByExample(RealExample example);

    Real selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Real record, @Param("example") RealExample example);

    int updateByExample(@Param("record") Real record, @Param("example") RealExample example);

    int updateByPrimaryKeySelective(Real record);

    int updateByPrimaryKey(Real record);
}