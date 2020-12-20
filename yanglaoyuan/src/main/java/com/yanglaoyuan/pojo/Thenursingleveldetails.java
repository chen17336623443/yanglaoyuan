package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Thenursingleveldetails {
    private Integer tsldId;
    private Integer nurId;
    private Integer tslId;
    private Integer tsldType;
    private Integer tsldNumber;
    private Timestamp tsldTime;
    private Nursing nursingByNurId;
    private Thenursinglevel thenursinglevelByTslId;

    @Id
    @Column(name = "tsld_id", nullable = false)
    public Integer getTsldId() {
        return tsldId;
    }

    public void setTsldId(Integer tsldId) {
        this.tsldId = tsldId;
    }

    @Basic
    @Column(name = "nur_id", nullable = true)
    public Integer getNurId() {
        return nurId;
    }

    public void setNurId(Integer nurId) {
        this.nurId = nurId;
    }

    @Basic
    @Column(name = "tsl_id", nullable = true)
    public Integer getTslId() {
        return tslId;
    }

    public void setTslId(Integer tslId) {
        this.tslId = tslId;
    }

    @Basic
    @Column(name = "tsld_type", nullable = true)
    public Integer getTsldType() {
        return tsldType;
    }

    public void setTsldType(Integer tsldType) {
        this.tsldType = tsldType;
    }

    @Basic
    @Column(name = "tsld_number", nullable = true)
    public Integer getTsldNumber() {
        return tsldNumber;
    }

    public void setTsldNumber(Integer tsldNumber) {
        this.tsldNumber = tsldNumber;
    }

    @Basic
    @Column(name = "tsld_time", nullable = true)
    public Timestamp getTsldTime() {
        return tsldTime;
    }

    public void setTsldTime(Timestamp tsldTime) {
        this.tsldTime = tsldTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thenursingleveldetails that = (Thenursingleveldetails) o;
        return Objects.equals(tsldId, that.tsldId) &&
                Objects.equals(nurId, that.nurId) &&
                Objects.equals(tslId, that.tslId) &&
                Objects.equals(tsldType, that.tsldType) &&
                Objects.equals(tsldNumber, that.tsldNumber) &&
                Objects.equals(tsldTime, that.tsldTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tsldId, nurId, tslId, tsldType, tsldNumber, tsldTime);
    }

    @ManyToOne
    @JoinColumn(name = "nur_id", referencedColumnName = "nur_id")
    public Nursing getNursingByNurId() {
        return nursingByNurId;
    }

    public void setNursingByNurId(Nursing nursingByNurId) {
        this.nursingByNurId = nursingByNurId;
    }

    @ManyToOne
    @JoinColumn(name = "tsl_id", referencedColumnName = "tsl_id")
    public Thenursinglevel getThenursinglevelByTslId() {
        return thenursinglevelByTslId;
    }

    public void setThenursinglevelByTslId(Thenursinglevel thenursinglevelByTslId) {
        this.thenursinglevelByTslId = thenursinglevelByTslId;
    }
}
