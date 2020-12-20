package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
public class Thenursinglevel {
    private Integer tslId;
    private String tslName;
    private BigDecimal tslMoney;
    private String tslOther;
    private String tslObject;
    private List<Oldman> oldmenByTslId;
    private List<Thenursingleveldetails> thenursingleveldetailsByTslId;

    @Id
    @Column(name = "tsl_id", nullable = false)
    public Integer getTslId() {
        return tslId;
    }

    public void setTslId(Integer tslId) {
        this.tslId = tslId;
    }

    @Basic
    @Column(name = "tsl_name", nullable = true, length = 60)
    public String getTslName() {
        return tslName;
    }

    public void setTslName(String tslName) {
        this.tslName = tslName;
    }

    @Basic
    @Column(name = "tsl_money", nullable = true, precision = 2)
    public BigDecimal getTslMoney() {
        return tslMoney;
    }

    public void setTslMoney(BigDecimal tslMoney) {
        this.tslMoney = tslMoney;
    }

    @Basic
    @Column(name = "tsl_other", nullable = true, length = -1)
    public String getTslOther() {
        return tslOther;
    }

    public void setTslOther(String tslOther) {
        this.tslOther = tslOther;
    }

    @Basic
    @Column(name = "tsl_object", nullable = true, length = 60)
    public String getTslObject() {
        return tslObject;
    }

    public void setTslObject(String tslObject) {
        this.tslObject = tslObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thenursinglevel that = (Thenursinglevel) o;
        return Objects.equals(tslId, that.tslId) &&
                Objects.equals(tslName, that.tslName) &&
                Objects.equals(tslMoney, that.tslMoney) &&
                Objects.equals(tslOther, that.tslOther) &&
                Objects.equals(tslObject, that.tslObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tslId, tslName, tslMoney, tslOther, tslObject);
    }

    @OneToMany(mappedBy = "thenursinglevelByTslId")
    public List<Oldman> getOldmenByTslId() {
        return oldmenByTslId;
    }

    public void setOldmenByTslId(List<Oldman> oldmenByTslId) {
        this.oldmenByTslId = oldmenByTslId;
    }

    @OneToMany(mappedBy = "thenursinglevelByTslId")
    public List<Thenursingleveldetails> getThenursingleveldetailsByTslId() {
        return thenursingleveldetailsByTslId;
    }

    public void setThenursingleveldetailsByTslId(List<Thenursingleveldetails> thenursingleveldetailsByTslId) {
        this.thenursingleveldetailsByTslId = thenursingleveldetailsByTslId;
    }
}
