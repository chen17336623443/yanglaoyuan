package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Fooddelivered {
    private Integer fdId;
    private Integer fdType;
    private Timestamp fdTime;
    private String fdAddress;
    private Foodorder foodorderByFoId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fd_id", nullable = false)
    public Integer getFdId() {
        return fdId;
    }

    public void setFdId(Integer fdId) {
        this.fdId = fdId;
    }

    @Basic
    @Column(name = "fd_type", nullable = true)
    public Integer getFdType() {
        return fdType;
    }

    public void setFdType(Integer fdType) {
        this.fdType = fdType;
    }

    @Basic
    @Column(name = "fd_time", nullable = true)
    public Timestamp getFdTime() {
        return fdTime;
    }

    public void setFdTime(Timestamp fdTime) {
        this.fdTime = fdTime;
    }

    @Basic
    @Column(name = "fd_address", nullable = true, length = 255)
    public String getFdAddress() {
        return fdAddress;
    }

    public void setFdAddress(String fdAddress) {
        this.fdAddress = fdAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fooddelivered that = (Fooddelivered) o;
        return Objects.equals(fdId, that.fdId) &&
                Objects.equals(fdType, that.fdType) &&
                Objects.equals(fdTime, that.fdTime) &&
                Objects.equals(fdAddress, that.fdAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fdId, fdType, fdTime, fdAddress);
    }

    @ManyToOne
    @JoinColumn(name = "fo_id", referencedColumnName = "fo_id")
    public Foodorder getFoodorderByFoId() {
        return foodorderByFoId;
    }

    public void setFoodorderByFoId(Foodorder foodorderByFoId) {
        this.foodorderByFoId = foodorderByFoId;
    }
}
