package org.exam.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TCourseMapper {
    long countByExample(TCourseExample example);

    int deleteByExample(TCourseExample example);

    @Delete({
        "delete from t_course",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_course (id, course_no, ",
        "course_name, is_del)",
        "values (#{id,jdbcType=BIGINT}, #{courseNo,jdbcType=BIGINT}, ",
        "#{courseName,jdbcType=VARCHAR}, #{isDel,jdbcType=BIT})"
    })
    int insert(TCourse record);

    int insertSelective(TCourse record);

    List<TCourse> selectByExample(TCourseExample example);

    @Select({
        "select",
        "id, course_no, course_name, is_del",
        "from t_course",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("org.exam.mapper.TCourseMapper.BaseResultMap")
    TCourse selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TCourse record, @Param("example") TCourseExample example);

    int updateByExample(@Param("record") TCourse record, @Param("example") TCourseExample example);

    int updateByPrimaryKeySelective(TCourse record);

    @Update({
        "update t_course",
        "set course_no = #{courseNo,jdbcType=BIGINT},",
          "course_name = #{courseName,jdbcType=VARCHAR},",
          "is_del = #{isDel,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TCourse record);
}