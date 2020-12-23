package com.yanglaoyuan.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class Bedtype {
    private Integer byid;
    private String byname;
    private BigDecimal bymoney;
    private BigDecimal byallmoney;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp bytime;
    private String byinfo;
    private String byimg;
    private List<Bed> bedsByByid;


    @Override
    public String toString() {
        return "Bedtype{" +
                "byid=" + byid +
                ", byname='" + byname + '\'' +
                ", bymoney=" + bymoney +
                ", byallmoney=" + byallmoney +
                ", bytime=" + bytime +
                ", byinfo='" + byinfo + '\'' +
                ", byimg='" + byimg + '\'' +
                '}';
    }

    @Id
    @Column(name = "byid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getByid() {
        return byid;
    }

    public void setByid(Integer byid) {
        this.byid = byid;
    }

    @Basic
    @Column(name = "byname", nullable = false, length = 20)
    public String getByname() {
        return byname;
    }

    public void setByname(String byname) {
        this.byname = byname;
    }

    @Basic
    @Column(name = "bymoney", nullable = false, precision = 2)
    public BigDecimal getBymoney() {
        return bymoney;
    }

    public void setBymoney(BigDecimal bymoney) {
        this.bymoney = bymoney;
    }

    @Basic
    @Column(name = "byallmoney", nullable = true, precision = 2)
    public BigDecimal getByallmoney() {
        return byallmoney;
    }

    public void setByallmoney(BigDecimal byallmoney) {
        this.byallmoney = byallmoney;
    }

    @Basic
    @Column(name = "bytime", nullable = false)
    public Timestamp getBytime() {
        return bytime;
    }

    public void setBytime(Timestamp bytime) {
        this.bytime = bytime;
    }

    @Basic
    @Column(name = "byinfo", nullable = true, length = 200)
    public String getByinfo() {
        return byinfo;
    }

    public void setByinfo(String byinfo) {
        this.byinfo = byinfo;
    }

    @Basic
    @Column(name = "byimg", nullable = true, length = 200)
    public String getByimg() {
        return byimg;
    }

    public void setByimg(String byimg) {
        this.byimg = byimg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bedtype bedtype = (Bedtype) o;
        return Objects.equals(byid, bedtype.byid) &&
                Objects.equals(byname, bedtype.byname) &&
                Objects.equals(bymoney, bedtype.bymoney) &&
                Objects.equals(byallmoney, bedtype.byallmoney) &&
                Objects.equals(bytime, bedtype.bytime) &&
                Objects.equals(byinfo, bedtype.byinfo) &&
                Objects.equals(byimg, bedtype.byimg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(byid, byname, bymoney, byallmoney, bytime, byinfo, byimg);
    }

    @OneToMany(mappedBy = "bedtypeByByid")
    public List<Bed> getBedsByByid() {
        return bedsByByid;
    }

    public void setBedsByByid(List<Bed> bedsByByid) {
        this.bedsByByid = bedsByByid;
    }
}
