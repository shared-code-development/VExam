package org.exam.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.exam.bean.entity.TUserMajorCourse;
import org.exam.bean.entity.TUserMajorCourseExample;

public interface TUserMajorCourseMapper {
    long countByExample(TUserMajorCourseExample example);

    int deleteByExample(TUserMajorCourseExample example);

    @Delete({
        "delete from t_user_major_course",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_user_major_course (id, major_id, ",
        "course_id, user_id)",
        "values (#{id,jdbcType=BIGINT}, #{majorId,jdbcType=BIGINT}, ",
        "#{courseId,jdbcType=BIGINT}, #{userId,jdbcType=BIGINT})"
    })
    int insert(TUserMajorCourse record);

    int insertSelective(TUserMajorCourse record);

    List<TUserMajorCourse> selectByExample(TUserMajorCourseExample example);

    @Select({
        "select",
        "id, major_id, course_id, user_id",
        "from t_user_major_course",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("org.exam.mapper.TUserMajorCourseMapper.BaseResultMap")
    TUserMajorCourse selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TUserMajorCourse record, @Param("example") TUserMajorCourseExample example);

    int updateByExample(@Param("record") TUserMajorCourse record, @Param("example") TUserMajorCourseExample example);

    int updateByPrimaryKeySelective(TUserMajorCourse record);

    @Update({
        "update t_user_major_course",
        "set major_id = #{majorId,jdbcType=BIGINT},",
          "course_id = #{courseId,jdbcType=BIGINT},",
          "user_id = #{userId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TUserMajorCourse record);
}