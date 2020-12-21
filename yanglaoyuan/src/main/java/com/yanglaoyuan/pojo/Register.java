package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Register {
    private Integer regId;
    private Timestamp regDate;
    private BigDecimal regMoney;
    private String regPaytype;
    private String regPay;
    private String regRemarks;
    private User userByUid;
    private Oldman oldmanByOmId;

    @Id
    @Column(name = "reg_id", nullable = false)
    public Integer getRegId() {
        return regId;
    }

    public void setRegId(Integer regId) {
        this.regId = regId;
    }

    @Basic
    @Column(name = "reg_date", nullable = false)
    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    @Basic
    @Column(name = "reg_money", nullable = false, precision = 2)
    public BigDecimal getRegMoney() {
        return regMoney;
    }

    public void setRegMoney(BigDecimal regMoney) {
        this.regMoney = regMoney;
    }

    @Basic
    @Column(name = "reg_paytype", nullable = true, length = 50)
    public String getRegPaytype() {
        return regPaytype;
    }

    public void setRegPaytype(String regPaytype) {
        this.regPaytype = regPaytype;
    }

    @Basic
    @Column(name = "reg_pay", nullable = true, length = 30)
    public String getRegPay() {
        return regPay;
    }

    public void setRegPay(String regPay) {
        this.regPay = regPay;
    }

    @Basic
    @Column(name = "reg_remarks", nullable = true, length = 200)
    public String getRegRemarks() {
        return regRemarks;
    }

    public void setRegRemarks(String regRemarks) {
        this.regRemarks = regRemarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Register register = (Register) o;
        return Objects.equals(regId, register.regId) &&
                Objects.equals(regDate, register.regDate) &&
                Objects.equals(regMoney, register.regMoney) &&
                Objects.equals(regPaytype, register.regPaytype) &&
                Objects.equals(regPay, register.regPay) &&
                Objects.equals(regRemarks, register.regRemarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regId, regDate, regMoney, regPaytype, regPay, regRemarks);
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
