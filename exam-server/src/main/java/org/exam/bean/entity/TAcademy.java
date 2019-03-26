package org.exam.bean.entity;

public class TAcademy {
    private Long id;

    private Long academyNo;

    private String academyName;

    private byte[] isDel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAcademyNo() {
        return academyNo;
    }

    public void setAcademyNo(Long academyNo) {
        this.academyNo = academyNo;
    }

    public String getAcademyName() {
        return academyName;
    }

    public void setAcademyName(String academyName) {
        this.academyName = academyName == null ? null : academyName.trim();
    }

    public byte[] getIsDel() {
        return isDel;
    }

    public void setIsDel(byte[] isDel) {
        this.isDel = isDel;
    }
}