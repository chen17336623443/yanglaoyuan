package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Arrears {
    private Integer arrId;
    private BigDecimal arrMoney;
    private BigDecimal arrMonth;
    private BigDecimal arrOther;
    private String arrSfpay;

    private Oldman oldmanByOmId;

    @Id
    @Column(name = "arr_id", nullable = false)
    public Integer getArrId() {
        return arrId;
    }

    public void setArrId(Integer arrId) {
        this.arrId = arrId;
    }

    @Basic
    @Column(name = "arr_money", nullable = false, precision = 2)
    public BigDecimal getArrMoney() {
        return arrMoney;
    }

    public void setArrMoney(BigDecimal arrMoney) {
        this.arrMoney = arrMoney;
    }

    @Basic
    @Column(name = "arr_month", nullable = false, precision = 2)
    public BigDecimal getArrMonth() {
        return arrMonth;
    }

    public void setArrMonth(BigDecimal arrMonth) {
        this.arrMonth = arrMonth;
    }

    @Basic
    @Column(name = "arr_other", nullable = false, precision = 2)
    public BigDecimal getArrOther() {
        return arrOther;
    }

    public void setArrOther(BigDecimal arrOther) {
        this.arrOther = arrOther;
    }

    @Basic
    @Column(name = "arr_sfpay", nullable = false, length = 20)
    public String getArrSfpay() {
        return arrSfpay;
    }

    public void setArrSfpay(String arrSfpay) {
        this.arrSfpay = arrSfpay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arrears arrears = (Arrears) o;
        return Objects.equals(arrId, arrears.arrId) &&
                Objects.equals(arrMoney, arrears.arrMoney) &&
                Objects.equals(arrMonth, arrears.arrMonth) &&
                Objects.equals(arrOther, arrears.arrOther) &&
                Objects.equals(arrSfpay, arrears.arrSfpay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrId, arrMoney, arrMonth, arrOther, arrSfpay);
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
