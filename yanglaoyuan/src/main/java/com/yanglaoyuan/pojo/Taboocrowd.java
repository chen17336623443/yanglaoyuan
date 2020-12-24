package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Taboocrowd {
    private Integer tcId;
    private String tcName;
    private String tcRemark;
    private List<Shiwuzhongjianbiao> shiwuzhongjianbiaosByTcId;
    private List<Food> foods;
    private List<Otman> otmans;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tc_id", nullable = false)
    public Integer getTcId() {
        return tcId;
    }

    public void setTcId(Integer tcId) {
        this.tcId = tcId;
    }

    @Basic
    @Column(name = "tc_name", nullable = true, length = 100)
    public String getTcName() {
        return tcName;
    }

    public void setTcName(String tcName) {
        this.tcName = tcName;
    }

    @Basic
    @Column(name = "tc_remark", nullable = true, length = 255)
    public String getTcRemark() {
        return tcRemark;
    }

    public void setTcRemark(String tcRemark) {
        this.tcRemark = tcRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taboocrowd that = (Taboocrowd) o;
        return Objects.equals(tcId, that.tcId) &&
                Objects.equals(tcName, that.tcName) &&
                Objects.equals(tcRemark, that.tcRemark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tcId, tcName, tcRemark);
    }

    @OneToMany(mappedBy = "taboocrowdByTcId")
    public List<Shiwuzhongjianbiao> getShiwuzhongjianbiaosByTcId() {
        return shiwuzhongjianbiaosByTcId;
    }

    public void setShiwuzhongjianbiaosByTcId(List<Shiwuzhongjianbiao> shiwuzhongjianbiaosByTcId) {
        this.shiwuzhongjianbiaosByTcId = shiwuzhongjianbiaosByTcId;
    }

    @ManyToMany(mappedBy = "taboocrowds")
    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    @OneToMany(mappedBy = "mytaboocrowd")
    public List<Otman> getOtmans() {
        return otmans;
    }

    public void setOtmans(List<Otman> otmans) {
        this.otmans = otmans;
    }
}
