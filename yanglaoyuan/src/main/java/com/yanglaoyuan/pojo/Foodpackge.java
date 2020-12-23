package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
public class Foodpackge {
    private Integer fpId;
    private String fpName;
    private BigDecimal fpPrice;
    private String fpRemark;
    private List<Foodweek> foodweeksByFpId;
    private String fpImg;
    private List<Oldman> oldmans;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fp_id", nullable = false)
    public Integer getFpId() {
        return fpId;
    }

    public void setFpId(Integer fpId) {
        this.fpId = fpId;
    }

    @Basic
    @Column(name = "fp_name", nullable = true, length = 100)
    public String getFpName() {
        return fpName;
    }

    public void setFpName(String fpName) {
        this.fpName = fpName;
    }

    @Basic
    @Column(name = "fp_price", nullable = true, precision = 1)
    public BigDecimal getFpPrice() {
        return fpPrice;
    }

    public void setFpPrice(BigDecimal fpPrice) {
        this.fpPrice = fpPrice;
    }

    @Basic
    @Column(name = "fp_remark", nullable = true, length = 255)
    public String getFpRemark() {
        return fpRemark;
    }

    public void setFpRemark(String fpRemark) {
        this.fpRemark = fpRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foodpackge that = (Foodpackge) o;
        return Objects.equals(fpId, that.fpId) &&
                Objects.equals(fpName, that.fpName) &&
                Objects.equals(fpPrice, that.fpPrice) &&
                Objects.equals(fpRemark, that.fpRemark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fpId, fpName, fpPrice, fpRemark);
    }

    @OneToMany(mappedBy = "foodpackgeByFpId")
    public List<Foodweek> getFoodweeksByFpId() {
        return foodweeksByFpId;
    }

    public void setFoodweeksByFpId(List<Foodweek> foodweeksByFpId) {
        this.foodweeksByFpId = foodweeksByFpId;
    }

    @Basic
    @Column(name = "fp_img")
    public String getFpImg() {
        return fpImg;
    }

    public void setFpImg(String fpImg) {
        this.fpImg = fpImg;
    }

    @OneToMany(mappedBy = "foodpackge")
    public List<Oldman> getOldmans() {
        return oldmans;
    }

    public void setOldmans(List<Oldman> oldmans) {
        this.oldmans = oldmans;
    }
}
