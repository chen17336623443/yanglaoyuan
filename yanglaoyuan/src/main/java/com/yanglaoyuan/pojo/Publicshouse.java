package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Publicshouse {
    private Integer phid;
    private Integer fiid;
    private String chname;
    private Floorinfo floorinfoByFiid;

    @Id
    @Column(name = "phid", nullable = false)
    public Integer getPhid() {
        return phid;
    }

    public void setPhid(Integer phid) {
        this.phid = phid;
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
    @Column(name = "chname", nullable = true, length = 50)
    public String getChname() {
        return chname;
    }

    public void setChname(String chname) {
        this.chname = chname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publicshouse that = (Publicshouse) o;
        return Objects.equals(phid, that.phid) &&
                Objects.equals(fiid, that.fiid) &&
                Objects.equals(chname, that.chname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phid, fiid, chname);
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
