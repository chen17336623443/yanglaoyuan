package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class Foodorder {
    private Integer foId;
    private String foMeals;
    private Timestamp foTime;
    private String foFoods;
    private BigDecimal foPrice;
    private Integer foState;
    private List<Fooddelivered> fooddeliveredsByFoId;
    private Oldman oldmanByOmId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fo_id", nullable = false)
    public Integer getFoId() {
        return foId;
    }

    public void setFoId(Integer foId) {
        this.foId = foId;
    }

    @Basic
    @Column(name = "fo_meals", nullable = true, length = 30)
    public String getFoMeals() {
        return foMeals;
    }

    public void setFoMeals(String foMeals) {
        this.foMeals = foMeals;
    }

    @Basic
    @Column(name = "fo_time", nullable = true)
    public Timestamp getFoTime() {
        return foTime;
    }

    public void setFoTime(Timestamp foTime) {
        this.foTime = foTime;
    }

    @Basic
    @Column(name = "fo_foods", nullable = true, length = 255)
    public String getFoFoods() {
        return foFoods;
    }

    public void setFoFoods(String foFoods) {
        this.foFoods = foFoods;
    }

    @Basic
    @Column(name = "fo_price", nullable = true, precision = 1)
    public BigDecimal getFoPrice() {
        return foPrice;
    }

    public void setFoPrice(BigDecimal foPrice) {
        this.foPrice = foPrice;
    }

    @Basic
    @Column(name = "fo_state", nullable = true)
    public Integer getFoState() {
        return foState;
    }

    public void setFoState(Integer foState) {
        this.foState = foState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foodorder foodorder = (Foodorder) o;
        return Objects.equals(foId, foodorder.foId) &&
                Objects.equals(foMeals, foodorder.foMeals) &&
                Objects.equals(foTime, foodorder.foTime) &&
                Objects.equals(foFoods, foodorder.foFoods) &&
                Objects.equals(foPrice, foodorder.foPrice) &&
                Objects.equals(foState, foodorder.foState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foId, foMeals, foTime, foFoods, foPrice, foState);
    }

    @OneToMany(mappedBy = "foodorderByFoId")
    public List<Fooddelivered> getFooddeliveredsByFoId() {
        return fooddeliveredsByFoId;
    }

    public void setFooddeliveredsByFoId(List<Fooddelivered> fooddeliveredsByFoId) {
        this.fooddeliveredsByFoId = fooddeliveredsByFoId;
    }

    @ManyToOne
    @JoinColumn(name = "om_id", referencedColumnName = "om_id")
    public Oldman getOldmanByOmId() {
        return oldmanByOmId;
    }

    public void setOldmanByOmId(Oldman oldmanByOmId) {
        this.oldmanByOmId = oldmanByOmId;
    }
}
