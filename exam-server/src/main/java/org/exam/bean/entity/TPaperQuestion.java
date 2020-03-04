package org.exam.bean.entity;

import java.util.Date;

/**
 * Database Table Remarks:
 *   试卷题库表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_paper_question
 *
 * @mbg.generated do_not_delete_during_merge Fri May 17 08:47:47 CST 2019
 */
public class TPaperQuestion {
    /**
     * Database Column Remarks:
     *   主键
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_paper_question.id
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   题库id，可能是选择题，判断题，简答题任意表中的主键
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_paper_question.question_id
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    private Long questionId;

    /**
     * Database Column Remarks:
     *   题型：选择题，判断题，简答题
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_paper_question.dic_type_id
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    private Long dicTypeId;

    /**
     * Database Column Remarks:
     *   序号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_paper_question.sort
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    private Integer sort;

    /**
     * Database Column Remarks:
     *   删除标识
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_paper_question.is_del
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    private Boolean isDel;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_paper_question.create_time
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    private Date createTime;

    /**
     * Database Column Remarks:
     *   更新时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_paper_question.update_time
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    private Date updateTime;

    /**
     * Database Column Remarks:
     *   创建者
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_paper_question.creator
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    private Long creator;

    /**
     * Database Column Remarks:
     *   更新者
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_paper_question.updater
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    private Long updater;

    /**
     * Database Column Remarks:
     *   版本号，乐观锁专用
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_paper_question.version
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    private Integer version;

    /**
     * Database Column Remarks:
     *   试卷id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_paper_question.paper_id
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    private Long paperId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_paper_question.id
     *
     * @return the value of t_paper_question.id
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_paper_question.id
     *
     * @param id the value for t_paper_question.id
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_paper_question.question_id
     *
     * @return the value of t_paper_question.question_id
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_paper_question.question_id
     *
     * @param questionId the value for t_paper_question.question_id
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_paper_question.dic_type_id
     *
     * @return the value of t_paper_question.dic_type_id
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public Long getDicTypeId() {
        return dicTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_paper_question.dic_type_id
     *
     * @param dicTypeId the value for t_paper_question.dic_type_id
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public void setDicTypeId(Long dicTypeId) {
        this.dicTypeId = dicTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_paper_question.sort
     *
     * @return the value of t_paper_question.sort
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_paper_question.sort
     *
     * @param sort the value for t_paper_question.sort
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_paper_question.is_del
     *
     * @return the value of t_paper_question.is_del
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_paper_question.is_del
     *
     * @param isDel the value for t_paper_question.is_del
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_paper_question.create_time
     *
     * @return the value of t_paper_question.create_time
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_paper_question.create_time
     *
     * @param createTime the value for t_paper_question.create_time
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_paper_question.update_time
     *
     * @return the value of t_paper_question.update_time
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_paper_question.update_time
     *
     * @param updateTime the value for t_paper_question.update_time
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_paper_question.creator
     *
     * @return the value of t_paper_question.creator
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_paper_question.creator
     *
     * @param creator the value for t_paper_question.creator
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_paper_question.updater
     *
     * @return the value of t_paper_question.updater
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public Long getUpdater() {
        return updater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_paper_question.updater
     *
     * @param updater the value for t_paper_question.updater
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_paper_question.version
     *
     * @return the value of t_paper_question.version
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_paper_question.version
     *
     * @param version the value for t_paper_question.version
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_paper_question.paper_id
     *
     * @return the value of t_paper_question.paper_id
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public Long getPaperId() {
        return paperId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_paper_question.paper_id
     *
     * @param paperId the value for t_paper_question.paper_id
     *
     * @mbg.generated Fri May 17 08:47:47 CST 2019
     */
    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }
}