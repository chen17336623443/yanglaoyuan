package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
public class Nursing {
    private Integer nurId;
    private String nuName;
    private BigDecimal nuMonry;
    private Nursingtype nursingtypeByNtId;
    private List<Thenursingleveldetails> thenursingleveldetailsByNurId;

    @Id
    @Column(name = "nur_id", nullable = false)
    public Integer getNurId() {
        return nurId;
    }

    public void setNurId(Integer nurId) {
        this.nurId = nurId;
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
        return Objects.equals(nurId, nursing.nurId) &&
                Objects.equals(nuName, nursing.nuName) &&
                Objects.equals(nuMonry, nursing.nuMonry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nurId, nuName, nuMonry);
    }

    @ManyToOne
    @JoinColumn(name = "nt_id", referencedColumnName = "nt_id")
    public Nursingtype getNursingtypeByNtId() {
        return nursingtypeByNtId;
    }

    public void setNursingtypeByNtId(Nursingtype nursingtypeByNtId) {
        this.nursingtypeByNtId = nursingtypeByNtId;
    }

    @OneToMany(mappedBy = "nursingByNurId")
    public List<Thenursingleveldetails> getThenursingleveldetailsByNurId() {
        return thenursingleveldetailsByNurId;
    }

    public void setThenursingleveldetailsByNurId(List<Thenursingleveldetails> thenursingleveldetailsByNurId) {
        this.thenursingleveldetailsByNurId = thenursingleveldetailsByNurId;
    }
}
