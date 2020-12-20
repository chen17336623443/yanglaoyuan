package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Nursingtype {
    private Integer ntId;
    private String ntName;
    private List<Nursing> nursingsByNtId;

    @Id
    @Column(name = "nt_id", nullable = false)
    public Integer getNtId() {
        return ntId;
    }

    public void setNtId(Integer ntId) {
        this.ntId = ntId;
    }

    @Basic
    @Column(name = "nt_name", nullable = true, length = 60)
    public String getNtName() {
        return ntName;
    }

    public void setNtName(String ntName) {
        this.ntName = ntName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nursingtype that = (Nursingtype) o;
        return Objects.equals(ntId, that.ntId) &&
                Objects.equals(ntName, that.ntName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ntId, ntName);
    }

    @OneToMany(mappedBy = "nursingtypeByNtId")
    public List<Nursing> getNursingsByNtId() {
        return nursingsByNtId;
    }

    public void setNursingsByNtId(List<Nursing> nursingsByNtId) {
        this.nursingsByNtId = nursingsByNtId;
    }
}
