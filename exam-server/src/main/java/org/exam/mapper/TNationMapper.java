package org.exam.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.exam.bean.entity.TNation;
import org.exam.bean.entity.TNationExample;

public interface TNationMapper {
    long countByExample(TNationExample example);

    int deleteByExample(TNationExample example);

    @Delete({
        "delete from t_nation",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into t_nation (name)",
        "values (#{name,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(TNation record);

    int insertSelective(TNation record);

    List<TNation> selectByExample(TNationExample example);

    @Select({
        "select",
        "id, name",
        "from t_nation",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("org.exam.mapper.TNationMapper.BaseResultMap")
    TNation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TNation record, @Param("example") TNationExample example);

    int updateByExample(@Param("record") TNation record, @Param("example") TNationExample example);

    int updateByPrimaryKeySelective(TNation record);

    @Update({
        "update t_nation",
        "set name = #{name,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TNation record);
}