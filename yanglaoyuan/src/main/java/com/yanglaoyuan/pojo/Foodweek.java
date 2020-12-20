package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Foodweek {
    private Integer fwId;
    private Integer fpId;
    private String fwWeekday;
    private String fwMeals;
    private String fwFoods;
    private Foodpackge foodpackgeByFpId;

    @Id
    @Column(name = "fw_id", nullable = false)
    public Integer getFwId() {
        return fwId;
    }

    public void setFwId(Integer fwId) {
        this.fwId = fwId;
    }

    @Basic
    @Column(name = "fp_id", nullable = true)
    public Integer getFpId() {
        return fpId;
    }

    public void setFpId(Integer fpId) {
        this.fpId = fpId;
    }

    @Basic
    @Column(name = "fw_weekday", nullable = true, length = 30)
    public String getFwWeekday() {
        return fwWeekday;
    }

    public void setFwWeekday(String fwWeekday) {
        this.fwWeekday = fwWeekday;
    }

    @Basic
    @Column(name = "fw_meals", nullable = true, length = 30)
    public String getFwMeals() {
        return fwMeals;
    }

    public void setFwMeals(String fwMeals) {
        this.fwMeals = fwMeals;
    }

    @Basic
    @Column(name = "fw_foods", nullable = true, length = 255)
    public String getFwFoods() {
        return fwFoods;
    }

    public void setFwFoods(String fwFoods) {
        this.fwFoods = fwFoods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foodweek foodweek = (Foodweek) o;
        return Objects.equals(fwId, foodweek.fwId) &&
                Objects.equals(fpId, foodweek.fpId) &&
                Objects.equals(fwWeekday, foodweek.fwWeekday) &&
                Objects.equals(fwMeals, foodweek.fwMeals) &&
                Objects.equals(fwFoods, foodweek.fwFoods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fwId, fpId, fwWeekday, fwMeals, fwFoods);
    }

    @ManyToOne
    @JoinColumn(name = "fp_id", referencedColumnName = "fp_id")
    public Foodpackge getFoodpackgeByFpId() {
        return foodpackgeByFpId;
    }

    public void setFoodpackgeByFpId(Foodpackge foodpackgeByFpId) {
        this.foodpackgeByFpId = foodpackgeByFpId;
    }
}
