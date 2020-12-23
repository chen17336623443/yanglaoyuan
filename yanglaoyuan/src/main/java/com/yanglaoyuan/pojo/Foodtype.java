package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Foodtype {
    private Integer ftId;
    private String ftName;
    private String ftRemark;
    private List<Food> foodsByFtId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ft_id", nullable = false)
    public Integer getFtId() {
        return ftId;
    }

    public void setFtId(Integer ftId) {
        this.ftId = ftId;
    }

    @Basic
    @Column(name = "ft_name", nullable = true, length = 30)
    public String getFtName() {
        return ftName;
    }

    public void setFtName(String ftName) {
        this.ftName = ftName;
    }

    @Basic
    @Column(name = "ft_remark", nullable = true, length = 255)
    public String getFtRemark() {
        return ftRemark;
    }

    public void setFtRemark(String ftRemark) {
        this.ftRemark = ftRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foodtype foodtype = (Foodtype) o;
        return Objects.equals(ftId, foodtype.ftId) &&
                Objects.equals(ftName, foodtype.ftName) &&
                Objects.equals(ftRemark, foodtype.ftRemark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ftId, ftName, ftRemark);
    }

    @OneToMany(mappedBy = "foodtypeByFtId")
    public List<Food> getFoodsByFtId() {
        return foodsByFtId;
    }

    public void setFoodsByFtId(List<Food> foodsByFtId) {
        this.foodsByFtId = foodsByFtId;
    }
}
