package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: YeJin Tan
 * @create: 2020-12-23 15:18
 */
@Entity
public class Foodorderdetails {
    private Integer fodId;
    private Integer fodNumber;
    private BigDecimal fodTotal;
    private Food food;
    private Foodorder foodorder;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fod_id")
    public Integer getFodId() {
        return fodId;
    }

    public void setFodId(Integer fodId) {
        this.fodId = fodId;
    }

    @Basic
    @Column(name = "fod_number")
    public Integer getFodNumber() {
        return fodNumber;
    }

    public void setFodNumber(Integer fodNumber) {
        this.fodNumber = fodNumber;
    }

    @Basic
    @Column(name = "fod_total")
    public BigDecimal getFodTotal() {
        return fodTotal;
    }

    public void setFodTotal(BigDecimal fodTotal) {
        this.fodTotal = fodTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foodorderdetails that = (Foodorderdetails) o;
        return Objects.equals(fodId, that.fodId) &&
                Objects.equals(fodNumber, that.fodNumber) &&
                Objects.equals(fodTotal, that.fodTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fodId, fodNumber, fodTotal);
    }

    @ManyToOne
    @JoinColumn(name = "f_id", referencedColumnName = "f_id")
    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @ManyToOne
    @JoinColumn(name = "fo_id", referencedColumnName = "fo_id")
    public Foodorder getFoodorder() {
        return foodorder;
    }

    public void setFoodorder(Foodorder foodorder) {
        this.foodorder = foodorder;
    }
}
