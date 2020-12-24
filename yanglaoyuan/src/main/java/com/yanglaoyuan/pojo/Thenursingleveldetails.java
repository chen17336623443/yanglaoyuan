package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Thenursingleveldetails {
    private Integer tsldId;
    private String tsldType;
    private String tsldNumber;
    private String tsldTime;
    private Nursing nursingByNuId;
    private Thenursinglevel thenursinglevelByTslId;

    @Id
    @Column(name = "tsld_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getTsldId() {
        return tsldId;
    }

    public void setTsldId(Integer tsldId) {
        this.tsldId = tsldId;
    }

    @Basic
    @Column(name = "tsld_type", nullable = true, length = 255)
    public String getTsldType() {
        return tsldType;
    }

    public void setTsldType(String tsldType) {
        this.tsldType = tsldType;
    }


    @Basic
    @Column(name = "tsld_number", nullable = true, length = 255)
    public String getTsldNumber() {
        return tsldNumber;
    }

    public void setTsldNumber(String tsldNumber) {
        this.tsldNumber = tsldNumber;
    }


    @Basic
    @Column(name = "tsld_time", nullable = true, length = 255)
    public String getTsldTime() {
        return tsldTime;
    }

    public void setTsldTime(String tsldTime) {
        this.tsldTime = tsldTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thenursingleveldetails that = (Thenursingleveldetails) o;
        return Objects.equals(tsldId, that.tsldId) &&
                Objects.equals(tsldType, that.tsldType) &&
                Objects.equals(tsldNumber, that.tsldNumber) &&
                Objects.equals(tsldTime, that.tsldTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tsldId, tsldType, tsldNumber, tsldTime);
    }

    @ManyToOne
    @JoinColumn(name = "nu_id", referencedColumnName = "nu_id")
    public Nursing getNursingByNuId() {
        return nursingByNuId;
    }

    public void setNursingByNuId(Nursing nursingByNuId) {
        this.nursingByNuId = nursingByNuId;
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
