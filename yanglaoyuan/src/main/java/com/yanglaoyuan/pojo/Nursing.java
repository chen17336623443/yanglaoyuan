package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
public class Nursing {
    private Integer nuId;
    private String nuName;
    private BigDecimal nuMonry;
    private Nursingtype nursingtypeByNtId;
    private List<Thenursingleveldetails> thenursingleveldetailsByNuId;
    private String nuWay;
    private String nuTime;
    private String nuOther;
    private List<Nursingrecord> nursingrecordsByNuId;

    @Id
    @Column(name = "nu_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getNuId() {
        return nuId;
    }

    public void setNuId(Integer nuId) {
        this.nuId = nuId;
    }

    @Basic
    @Column(name = "nu_name", nullable = true, length = 200)
    public String getNuName() {
        return nuName;
    }

    public void setNuName(String nuName) {
        this.nuName = nuName;
    }

    @Basic
    @Column(name = "nu_monry", nullable = false, precision = 2)
    public BigDecimal getNuMonry() {
        return nuMonry;
    }

    public void setNuMonry(BigDecimal nuMonry) {
        this.nuMonry = nuMonry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nursing nursing = (Nursing) o;
        return Objects.equals(nuId, nursing.nuId) &&
                Objects.equals(nuName, nursing.nuName) &&
                Objects.equals(nuMonry, nursing.nuMonry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nuId, nuName, nuMonry);
    }

    @ManyToOne
    @JoinColumn(name = "nt_id", referencedColumnName = "nt_id")
    public Nursingtype getNursingtypeByNtId() {
        return nursingtypeByNtId;
    }

    public void setNursingtypeByNtId(Nursingtype nursingtypeByNtId) {
        this.nursingtypeByNtId = nursingtypeByNtId;
    }

    @OneToMany(mappedBy = "nursingByNuId")
    public List<Thenursingleveldetails> getThenursingleveldetailsByNuId() {
        return thenursingleveldetailsByNuId;
    }

    public void setThenursingleveldetailsByNuId(List<Thenursingleveldetails> thenursingleveldetailsByNuId) {
        this.thenursingleveldetailsByNuId = thenursingleveldetailsByNuId;
    }

    @Basic
    @Column(name = "nu_way", nullable = true, length = 255)
    public String getNuWay() {
        return nuWay;
    }

    public void setNuWay(String nuWay) {
        this.nuWay = nuWay;
    }

    @Basic
    @Column(name = "nu_time", nullable = true, length = 255)
    public String getNuTime() {
        return nuTime;
    }

    public void setNuTime(String nuTime) {
        this.nuTime = nuTime;
    }

    @Basic
    @Column(name = "nu_other", nullable = true, length = 255)
    public String getNuOther() {
        return nuOther;
    }

    public void setNuOther(String nuOther) {
        this.nuOther = nuOther;
    }

    @OneToMany(mappedBy = "nursingByNuId")
    public List<Nursingrecord> getNursingrecordsByNuId() {
        return nursingrecordsByNuId;
    }

    public void setNursingrecordsByNuId(List<Nursingrecord> nursingrecordsByNuId) {
        this.nursingrecordsByNuId = nursingrecordsByNuId;
    }
}
