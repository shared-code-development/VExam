package org.exam.bean.entity;

public class TMajor {
    private Long id;

    private Long majorNo;

    private String majorName;

    private byte[] isDel;

    private Long academyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMajorNo() {
        return majorNo;
    }

    public void setMajorNo(Long majorNo) {
        this.majorNo = majorNo;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName == null ? null : majorName.trim();
    }

    public byte[] getIsDel() {
        return isDel;
    }

    public void setIsDel(byte[] isDel) {
        this.isDel = isDel;
    }

    public Long getAcademyId() {
        return academyId;
    }

    public void setAcademyId(Long academyId) {
        this.academyId = academyId;
    }
}