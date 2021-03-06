package com.yanglaoyuan.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Deposit {
    private Integer depId;
    private BigDecimal depMoney;
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" , timezone = " GMT+8 " )
    private Timestamp depDate;
    private String depPayment;
    private String depPaytype;
    private String depRemarks;

    private User userByUid;
    private Oldman oldmanByOmId;

    @Override
    public String toString() {
        return "Deposit{" +
                "depId=" + depId +
                ", depMoney=" + depMoney +
                ", depDate=" + depDate +
                ", depPayment='" + depPayment + '\'' +
                ", depPaytype='" + depPaytype + '\'' +
                ", depRemarks='" + depRemarks + '\'' +
                ", userByUid=" + userByUid +
                ", oldmanByOmId=" + oldmanByOmId +
                '}';
    }

    @Id
    @Column(name = "dep_id", nullable = false)
    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    @Basic
    @Column(name = "dep_money", nullable = false, precision = 2)
    public BigDecimal getDepMoney() {
        return depMoney;
    }

    public void setDepMoney(BigDecimal depMoney) {
        this.depMoney = depMoney;
    }

    @Basic
    @Column(name = "dep_date", nullable = false)
    public Timestamp getDepDate() {
        return depDate;
    }

    public void setDepDate(Timestamp depDate) {
        this.depDate = depDate;
    }

    @Basic
    @Column(name = "dep_payment", nullable = true, length = 30)
    public String getDepPayment() {
        return depPayment;
    }

    public void setDepPayment(String depPayment) {
        this.depPayment = depPayment;
    }

    @Basic
    @Column(name = "dep_paytype", nullable = true, length = 30)
    public String getDepPaytype() {
        return depPaytype;
    }

    public void setDepPaytype(String depPaytype) {
        this.depPaytype = depPaytype;
    }

    @Basic
    @Column(name = "dep_remarks", nullable = true, length = 200)
    public String getDepRemarks() {
        return depRemarks;
    }

    public void setDepRemarks(String depRemarks) {
        this.depRemarks = depRemarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deposit deposit = (Deposit) o;
        return Objects.equals(depId, deposit.depId) &&
                Objects.equals(depMoney, deposit.depMoney) &&
                Objects.equals(depDate, deposit.depDate) &&
                Objects.equals(depPayment, deposit.depPayment) &&
                Objects.equals(depPaytype, deposit.depPaytype) &&
                Objects.equals(depRemarks, deposit.depRemarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depId, depMoney, depDate, depPayment, depPaytype, depRemarks);
    }

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    public User getUserByUid() {
        return userByUid;
    }

    public void setUserByUid(User userByUid) {
        this.userByUid = userByUid;
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
