package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Bed {
    private Integer bid;
    private String bname;
    private Bedtype bedtypeByByid;
    private Floor floorByFid;
    private Oldman myoldman;

    private String fjh;
    private String cshu;
    private String ldh;

    @Override
    public String toString() {
        return "Bed{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", bedtypeByByid=" + bedtypeByByid +
                ", floorByFid=" + floorByFid +
                ", myoldman=" + myoldman +
                ", fjh='" + fjh + '\'' +
                ", cshu='" + cshu + '\'' +
                ", ldh='" + ldh + '\'' +
                '}';
    }

    public String getFjh() {
        return fjh;
    }

    public void setFjh(String fjh) {
        this.fjh = fjh;
    }

    public String getCshu() {
        return cshu;
    }

    public void setCshu(String cshu) {
        this.cshu = cshu;
    }

    public String getLdh() {
        return ldh;
    }

    public void setLdh(String ldh) {
        this.ldh = ldh;
    }

    @Id
    @Column(name = "bid", nullable = false)
    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    @Basic
    @Column(name = "bname", nullable = true, length = 50)
    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bed bed = (Bed) o;
        return Objects.equals(bid, bed.bid) &&
                Objects.equals(bname, bed.bname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bid, bname);
    }

    @ManyToOne
    @JoinColumn(name = "byid", referencedColumnName = "byid")
    public Bedtype getBedtypeByByid() {
        return bedtypeByByid;
    }

    public void setBedtypeByByid(Bedtype bedtypeByByid) {
        this.bedtypeByByid = bedtypeByByid;
    }

    @ManyToOne
    @JoinColumn(name = "fid", referencedColumnName = "fid")
    public Floor getFloorByFid() {
        return floorByFid;
    }

    public void setFloorByFid(Floor floorByFid) {
        this.floorByFid = floorByFid;
    }

    @ManyToOne
    @JoinColumn(name = "om_id", referencedColumnName = "om_id")
    public Oldman getMyoldman() {
        return myoldman;
    }

    public void setMyoldman(Oldman myoldman) {
        this.myoldman = myoldman;
    }
}
