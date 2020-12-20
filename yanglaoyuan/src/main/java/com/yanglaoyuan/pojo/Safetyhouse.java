package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Safetyhouse {
    private Integer shid;
    private Integer fiid;
    private String shname;
    private Floorinfo floorinfoByFiid;

    @Id
    @Column(name = "shid", nullable = false)
    public Integer getShid() {
        return shid;
    }

    public void setShid(Integer shid) {
        this.shid = shid;
    }

    @Basic
    @Column(name = "fiid", nullable = true)
    public Integer getFiid() {
        return fiid;
    }

    public void setFiid(Integer fiid) {
        this.fiid = fiid;
    }

    @Basic
    @Column(name = "shname", nullable = true, length = 50)
    public String getShname() {
        return shname;
    }

    public void setShname(String shname) {
        this.shname = shname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Safetyhouse that = (Safetyhouse) o;
        return Objects.equals(shid, that.shid) &&
                Objects.equals(fiid, that.fiid) &&
                Objects.equals(shname, that.shname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shid, fiid, shname);
    }

    @ManyToOne
    @JoinColumn(name = "fiid", referencedColumnName = "fiid")
    public Floorinfo getFloorinfoByFiid() {
        return floorinfoByFiid;
    }

    public void setFloorinfoByFiid(Floorinfo floorinfoByFiid) {
        this.floorinfoByFiid = floorinfoByFiid;
    }
}
