package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Nursingrecord {
    private Integer nrId;
    private Timestamp nrTime;
    private Integer nrCompletion;
    private Timestamp nrMoney;
    private String nrOther;
    private Oldman oldmanByOmId;

    @Id
    @Column(name = "nr_id", nullable = false)
    public Integer getNrId() {
        return nrId;
    }

    public void setNrId(Integer nrId) {
        this.nrId = nrId;
    }

    @Basic
    @Column(name = "nr_time", nullable = true)
    public Timestamp getNrTime() {
        return nrTime;
    }

    public void setNrTime(Timestamp nrTime) {
        this.nrTime = nrTime;
    }

    @Basic
    @Column(name = "nr_completion", nullable = true)
    public Integer getNrCompletion() {
        return nrCompletion;
    }

    public void setNrCompletion(Integer nrCompletion) {
        this.nrCompletion = nrCompletion;
    }

    @Basic
    @Column(name = "nr_money", nullable = true)
    public Timestamp getNrMoney() {
        return nrMoney;
    }

    public void setNrMoney(Timestamp nrMoney) {
        this.nrMoney = nrMoney;
    }

    @Basic
    @Column(name = "nr_other", nullable = true, length = -1)
    public String getNrOther() {
        return nrOther;
    }

    public void setNrOther(String nrOther) {
        this.nrOther = nrOther;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nursingrecord that = (Nursingrecord) o;
        return Objects.equals(nrId, that.nrId) &&
                Objects.equals(nrTime, that.nrTime) &&
                Objects.equals(nrCompletion, that.nrCompletion) &&
                Objects.equals(nrMoney, that.nrMoney) &&
                Objects.equals(nrOther, that.nrOther);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrId, nrTime, nrCompletion, nrMoney, nrOther);
    }

    @ManyToOne
    @JoinColumn(name = "om_id", referencedColumnName = "om_id")
    public Oldman getOldmanByOmId() {
        return oldmanByOmId;
    }

    public void setOldmanByOmId(Oldman oldmanByOmId) {
        this.oldmanByOmId = oldmanByOmId;
    }
}
