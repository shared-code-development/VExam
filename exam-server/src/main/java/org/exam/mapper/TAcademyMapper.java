package org.exam.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.exam.bean.entity.TAcademy;
import org.exam.bean.entity.TAcademyExample;

public interface TAcademyMapper {
    long countByExample(TAcademyExample example);

    int deleteByExample(TAcademyExample example);

    @Delete({
        "delete from t_academy",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_academy (id, academy_no, ",
        "academy_name, is_del)",
        "values (#{id,jdbcType=BIGINT}, #{academyNo,jdbcType=BIGINT}, ",
        "#{academyName,jdbcType=VARCHAR}, #{isDel,jdbcType=BIT})"
    })
    int insert(TAcademy record);

    int insertSelective(TAcademy record);

    List<TAcademy> selectByExample(TAcademyExample example);

    @Select({
        "select",
        "id, academy_no, academy_name, is_del",
        "from t_academy",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("org.exam.mapper.TAcademyMapper.BaseResultMap")
    TAcademy selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TAcademy record, @Param("example") TAcademyExample example);

    int updateByExample(@Param("record") TAcademy record, @Param("example") TAcademyExample example);

    int updateByPrimaryKeySelective(TAcademy record);

    @Update({
        "update t_academy",
        "set academy_no = #{academyNo,jdbcType=BIGINT},",
          "academy_name = #{academyName,jdbcType=VARCHAR},",
          "is_del = #{isDel,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TAcademy record);
}