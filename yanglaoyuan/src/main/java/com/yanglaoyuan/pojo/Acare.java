package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Acare {
    private Integer acId;
    private String acName;
    private String acIdcare;
    private String acSex;
    private Date acBirth;
    private String acDizi;
    private Long acPhone;
    private String acImg;
    private List<Oldman> oldmenByAcId;

    @Id
    @Column(name = "ac_id", nullable = false)
    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    @Basic
    @Column(name = "ac_name", nullable = true, length = 255)
    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    @Basic
    @Column(name = "ac_idcare", nullable = true, length = 255)
    public String getAcIdcare() {
        return acIdcare;
    }

    public void setAcIdcare(String acIdcare) {
        this.acIdcare = acIdcare;
    }

    @Basic
    @Column(name = "ac_sex", nullable = true, length = 25)
    public String getAcSex() {
        return acSex;
    }

    public void setAcSex(String acSex) {
        this.acSex = acSex;
    }

    @Basic
    @Column(name = "ac_birth", nullable = true)
    public Date getAcBirth() {
        return acBirth;
    }

    public void setAcBirth(Date acBirth) {
        this.acBirth = acBirth;
    }

    @Basic
    @Column(name = "ac_dizi", nullable = true, length = 255)
    public String getAcDizi() {
        return acDizi;
    }

    public void setAcDizi(String acDizi) {
        this.acDizi = acDizi;
    }

    @Basic
    @Column(name = "ac_phone", nullable = true)
    public Long getAcPhone() {
        return acPhone;
    }

    public void setAcPhone(Long acPhone) {
        this.acPhone = acPhone;
    }

    @Basic
    @Column(name = "ac_img", nullable = true, length = 255)
    public String getAcImg() {
        return acImg;
    }

    public void setAcImg(String acImg) {
        this.acImg = acImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acare acare = (Acare) o;
        return Objects.equals(acId, acare.acId) &&
                Objects.equals(acName, acare.acName) &&
                Objects.equals(acIdcare, acare.acIdcare) &&
                Objects.equals(acSex, acare.acSex) &&
                Objects.equals(acBirth, acare.acBirth) &&
                Objects.equals(acDizi, acare.acDizi) &&
                Objects.equals(acPhone, acare.acPhone) &&
                Objects.equals(acImg, acare.acImg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acId, acName, acIdcare, acSex, acBirth, acDizi, acPhone, acImg);
    }

    @OneToMany(mappedBy = "acareByAcId")
    public List<Oldman> getOldmenByAcId() {
        return oldmenByAcId;
    }

    public void setOldmenByAcId(List<Oldman> oldmenByAcId) {
        this.oldmenByAcId = oldmenByAcId;
    }
}
