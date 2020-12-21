package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Equipmenthouse {
    private Integer ehid;
    private String shname;
    private Floorinfo floorinfoByFiid;

    @Id
    @Column(name = "ehid", nullable = false)
    public Integer getEhid() {
        return ehid;
    }

    public void setEhid(Integer ehid) {
        this.ehid = ehid;
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
        Equipmenthouse that = (Equipmenthouse) o;
        return Objects.equals(ehid, that.ehid) &&
                Objects.equals(shname, that.shname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ehid,  shname);
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
