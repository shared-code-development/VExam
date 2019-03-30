package org.exam.bean.entity;

/**
 * Database Table Remarks:
 *   课程表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_course
 *
 * @mbg.generated do_not_delete_during_merge Sat Mar 30 14:43:46 CST 2019
 */
public class TCourse {
    /**
     * Database Column Remarks:
     *   主键
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course.id
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   课程编号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course.course_no
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    private Long courseNo;

    /**
     * Database Column Remarks:
     *   课程名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course.course_name
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    private String courseName;

    /**
     * Database Column Remarks:
     *   删除标识：0-未删除；1-已删除
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course.is_del
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    private Boolean isDel;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course.create_time
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    private Long createTime;

    /**
     * Database Column Remarks:
     *   更新时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course.update_time
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    private Long updateTime;

    /**
     * Database Column Remarks:
     *   创建者
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course.creator
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    private Integer creator;

    /**
     * Database Column Remarks:
     *   更新者
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course.updater
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    private Integer updater;

    /**
     * Database Column Remarks:
     *   版本号，乐观锁专用
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course.version
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    private Integer version;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course.id
     *
     * @return the value of t_course.id
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course.id
     *
     * @param id the value for t_course.id
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course.course_no
     *
     * @return the value of t_course.course_no
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public Long getCourseNo() {
        return courseNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course.course_no
     *
     * @param courseNo the value for t_course.course_no
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public void setCourseNo(Long courseNo) {
        this.courseNo = courseNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course.course_name
     *
     * @return the value of t_course.course_name
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course.course_name
     *
     * @param courseName the value for t_course.course_name
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course.is_del
     *
     * @return the value of t_course.is_del
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course.is_del
     *
     * @param isDel the value for t_course.is_del
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course.create_time
     *
     * @return the value of t_course.create_time
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course.create_time
     *
     * @param createTime the value for t_course.create_time
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course.update_time
     *
     * @return the value of t_course.update_time
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course.update_time
     *
     * @param updateTime the value for t_course.update_time
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course.creator
     *
     * @return the value of t_course.creator
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course.creator
     *
     * @param creator the value for t_course.creator
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course.updater
     *
     * @return the value of t_course.updater
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public Integer getUpdater() {
        return updater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course.updater
     *
     * @param updater the value for t_course.updater
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public void setUpdater(Integer updater) {
        this.updater = updater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course.version
     *
     * @return the value of t_course.version
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course.version
     *
     * @param version the value for t_course.version
     *
     * @mbg.generated Sat Mar 30 14:43:46 CST 2019
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}