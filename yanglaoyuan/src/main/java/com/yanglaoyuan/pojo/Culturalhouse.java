package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Culturalhouse {
    private Integer chid;
    private Integer fiid;
    private String chname;
    private Floorinfo floorinfoByFiid;

    @Id
    @Column(name = "chid", nullable = false)
    public Integer getChid() {
        return chid;
    }

    public void setChid(Integer chid) {
        this.chid = chid;
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
        Culturalhouse that = (Culturalhouse) o;
        return Objects.equals(chid, that.chid) &&
                Objects.equals(fiid, that.fiid) &&
                Objects.equals(chname, that.chname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chid, fiid, chname);
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
