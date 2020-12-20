package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Shiwuzhongjianbiao {
    private Integer fId;
    private Integer tcId;
    private Integer fctId;
    private Food foodByFId;
    private Taboocrowd taboocrowdByTcId;

    @Basic
    @Column(name = "f_id", nullable = false)
    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    @Basic
    @Column(name = "tc_id", nullable = false)
    public Integer getTcId() {
        return tcId;
    }

    public void setTcId(Integer tcId) {
        this.tcId = tcId;
    }

    @Id
    @Column(name = "fct_id", nullable = false)
    public Integer getFctId() {
        return fctId;
    }

    public void setFctId(Integer fctId) {
        this.fctId = fctId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shiwuzhongjianbiao that = (Shiwuzhongjianbiao) o;
        return Objects.equals(fId, that.fId) &&
                Objects.equals(tcId, that.tcId) &&
                Objects.equals(fctId, that.fctId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fId, tcId, fctId);
    }

    @ManyToOne
    @JoinColumn(name = "f_id", referencedColumnName = "f_id", nullable = false)
    public Food getFoodByFId() {
        return foodByFId;
    }

    public void setFoodByFId(Food foodByFId) {
        this.foodByFId = foodByFId;
    }

    @ManyToOne
    @JoinColumn(name = "tc_id", referencedColumnName = "tc_id", nullable = false)
    public Taboocrowd getTaboocrowdByTcId() {
        return taboocrowdByTcId;
    }

    public void setTaboocrowdByTcId(Taboocrowd taboocrowdByTcId) {
        this.taboocrowdByTcId = taboocrowdByTcId;
    }
}
