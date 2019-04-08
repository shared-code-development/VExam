package org.exam.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.exam.bean.entity.TUserClazzCourse;
import org.exam.bean.entity.TUserClazzCourseExample;

public interface TUserClazzCourseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_clazz_course
     *
     * @mbg.generated Thu Apr 04 09:15:24 CST 2019
     */
    long countByExample(TUserClazzCourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_clazz_course
     *
     * @mbg.generated Thu Apr 04 09:15:24 CST 2019
     */
    int deleteByExample(TUserClazzCourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_clazz_course
     *
     * @mbg.generated Thu Apr 04 09:15:24 CST 2019
     */
    @Delete({
        "delete from t_user_clazz_course",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_clazz_course
     *
     * @mbg.generated Thu Apr 04 09:15:24 CST 2019
     */
    @Insert({
        "insert into t_user_clazz_course (id, clazz_id, ",
        "course_id, user_id, ",
        "is_del, create_time, ",
        "update_time, creator, ",
        "updater, version)",
        "values (#{id,jdbcType=BIGINT}, #{clazzId,jdbcType=BIGINT}, ",
        "#{courseId,jdbcType=BIGINT}, #{userId,jdbcType=BIGINT}, ",
        "#{isDel,jdbcType=BIT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{creator,jdbcType=INTEGER}, ",
        "#{updater,jdbcType=INTEGER}, #{version,jdbcType=INTEGER})"
    })
    int insert(TUserClazzCourse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_clazz_course
     *
     * @mbg.generated Thu Apr 04 09:15:24 CST 2019
     */
    int insertSelective(TUserClazzCourse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_clazz_course
     *
     * @mbg.generated Thu Apr 04 09:15:24 CST 2019
     */
    List<TUserClazzCourse> selectByExample(TUserClazzCourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_clazz_course
     *
     * @mbg.generated Thu Apr 04 09:15:24 CST 2019
     */
    @Select({
        "select",
        "id, clazz_id, course_id, user_id, is_del, create_time, update_time, creator, ",
        "updater, version",
        "from t_user_clazz_course",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("org.exam.mapper.TUserClazzCourseMapper.BaseResultMap")
    TUserClazzCourse selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_clazz_course
     *
     * @mbg.generated Thu Apr 04 09:15:24 CST 2019
     */
    int updateByExampleSelective(@Param("record") TUserClazzCourse record, @Param("example") TUserClazzCourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_clazz_course
     *
     * @mbg.generated Thu Apr 04 09:15:24 CST 2019
     */
    int updateByExample(@Param("record") TUserClazzCourse record, @Param("example") TUserClazzCourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_clazz_course
     *
     * @mbg.generated Thu Apr 04 09:15:24 CST 2019
     */
    int updateByPrimaryKeySelective(TUserClazzCourse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_clazz_course
     *
     * @mbg.generated Thu Apr 04 09:15:24 CST 2019
     */
    @Update({
        "update t_user_clazz_course",
        "set clazz_id = #{clazzId,jdbcType=BIGINT},",
          "course_id = #{courseId,jdbcType=BIGINT},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "is_del = #{isDel,jdbcType=BIT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "creator = #{creator,jdbcType=INTEGER},",
          "updater = #{updater,jdbcType=INTEGER},",
          "version = #{version,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TUserClazzCourse record);
}