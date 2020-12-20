package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Bed {
    private Integer bid;
    private String bname;
    private Bedtype bedtypeByByid;
    private Floor floorByFid;

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
}
