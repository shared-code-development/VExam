package org.exam.bean.entity;

public class TCourse {
    private Long id;

    private Long courseNo;

    private String courseName;

    private byte[] isDel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(Long courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public byte[] getIsDel() {
        return isDel;
    }

    public void setIsDel(byte[] isDel) {
        this.isDel = isDel;
    }
}