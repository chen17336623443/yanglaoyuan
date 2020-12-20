package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Costflow {
    private Integer cosId;
    private Integer omId;
    private Integer uid;
    private String cosCategory;
    private BigDecimal cosMoney;
    private Timestamp cosDate;
    private String cosExplain;
    private String cosRemarks;
    private Oldman oldmanByOmId;
    private User userByUid;

    @Id
    @Column(name = "cos_id", nullable = false)
    public Integer getCosId() {
        return cosId;
    }

    public void setCosId(Integer cosId) {
        this.cosId = cosId;
    }

    @Basic
    @Column(name = "om_id", nullable = true)
    public Integer getOmId() {
        return omId;
    }

    public void setOmId(Integer omId) {
        this.omId = omId;
    }

    @Basic
    @Column(name = "uid", nullable = true)
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "cos_category", nullable = false, length = 50)
    public String getCosCategory() {
        return cosCategory;
    }

    public void setCosCategory(String cosCategory) {
        this.cosCategory = cosCategory;
    }

    @Basic
    @Column(name = "cos_money", nullable = false, precision = 2)
    public BigDecimal getCosMoney() {
        return cosMoney;
    }

    public void setCosMoney(BigDecimal cosMoney) {
        this.cosMoney = cosMoney;
    }

    @Basic
    @Column(name = "cos_date", nullable = false)
    public Timestamp getCosDate() {
        return cosDate;
    }

    public void setCosDate(Timestamp cosDate) {
        this.cosDate = cosDate;
    }

    @Basic
    @Column(name = "cos_explain", nullable = false, length = 100)
    public String getCosExplain() {
        return cosExplain;
    }

    public void setCosExplain(String cosExplain) {
        this.cosExplain = cosExplain;
    }

    @Basic
    @Column(name = "cos_remarks", nullable = true, length = 200)
    public String getCosRemarks() {
        return cosRemarks;
    }

    public void setCosRemarks(String cosRemarks) {
        this.cosRemarks = cosRemarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Costflow costflow = (Costflow) o;
        return Objects.equals(cosId, costflow.cosId) &&
                Objects.equals(omId, costflow.omId) &&
                Objects.equals(uid, costflow.uid) &&
                Objects.equals(cosCategory, costflow.cosCategory) &&
                Objects.equals(cosMoney, costflow.cosMoney) &&
                Objects.equals(cosDate, costflow.cosDate) &&
                Objects.equals(cosExplain, costflow.cosExplain) &&
                Objects.equals(cosRemarks, costflow.cosRemarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cosId, omId, uid, cosCategory, cosMoney, cosDate, cosExplain, cosRemarks);
    }

    @ManyToOne
    @JoinColumn(name = "om_id", referencedColumnName = "om_id")
    public Oldman getOldmanByOmId() {
        return oldmanByOmId;
    }

    public void setOldmanByOmId(Oldman oldmanByOmId) {
        this.oldmanByOmId = oldmanByOmId;
    }

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    public User getUserByUid() {
        return userByUid;
    }

    public void setUserByUid(User userByUid) {
        this.userByUid = userByUid;
    }
}
