package org.exam.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TMajorMapper {
    long countByExample(TMajorExample example);

    int deleteByExample(TMajorExample example);

    @Delete({
        "delete from t_major",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_major (id, major_no, ",
        "major_name, is_del, academy_id)",
        "values (#{id,jdbcType=BIGINT}, #{majorNo,jdbcType=BIGINT}, ",
        "#{majorName,jdbcType=VARCHAR}, #{isDel,jdbcType=BIT}, #{academyId,jdbcType=BIGINT})"
    })
    int insert(TMajor record);

    int insertSelective(TMajor record);

    List<TMajor> selectByExample(TMajorExample example);

    @Select({
        "select",
        "id, major_no, major_name, is_del, academy_id",
        "from t_major",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("org.exam.mapper.TMajorMapper.BaseResultMap")
    TMajor selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TMajor record, @Param("example") TMajorExample example);

    int updateByExample(@Param("record") TMajor record, @Param("example") TMajorExample example);

    int updateByPrimaryKeySelective(TMajor record);

    @Update({
        "update t_major",
        "set major_no = #{majorNo,jdbcType=BIGINT},",
          "major_name = #{majorName,jdbcType=VARCHAR},",
          "is_del = #{isDel,jdbcType=BIT},",
          "academy_id = #{academyId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TMajor record);
}