package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Floor {
    private Integer fid;
    private Integer floFid;
    private String fname;
    private Integer fdad;
    private List<Bed> bedsByFid;
    private Floor floorByFloFid;
    private List<Floor> floorsByFid;
    private List<Floorinfo> floorinfosByFid;

    @Id
    @Column(name = "fid", nullable = false)
    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    @Basic
    @Column(name = "flo_fid", nullable = true)
    public Integer getFloFid() {
        return floFid;
    }

    public void setFloFid(Integer floFid) {
        this.floFid = floFid;
    }

    @Basic
    @Column(name = "fname", nullable = true, length = 20)
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    @Basic
    @Column(name = "fdad", nullable = true)
    public Integer getFdad() {
        return fdad;
    }

    public void setFdad(Integer fdad) {
        this.fdad = fdad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return Objects.equals(fid, floor.fid) &&
                Objects.equals(floFid, floor.floFid) &&
                Objects.equals(fname, floor.fname) &&
                Objects.equals(fdad, floor.fdad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fid, floFid, fname, fdad);
    }

    @OneToMany(mappedBy = "floorByFid")
    public List<Bed> getBedsByFid() {
        return bedsByFid;
    }

    public void setBedsByFid(List<Bed> bedsByFid) {
        this.bedsByFid = bedsByFid;
    }

    @ManyToOne
    @JoinColumn(name = "flo_fid", referencedColumnName = "fid")
    public Floor getFloorByFloFid() {
        return floorByFloFid;
    }

    public void setFloorByFloFid(Floor floorByFloFid) {
        this.floorByFloFid = floorByFloFid;
    }

    @OneToMany(mappedBy = "floorByFloFid")
    public List<Floor> getFloorsByFid() {
        return floorsByFid;
    }

    public void setFloorsByFid(List<Floor> floorsByFid) {
        this.floorsByFid = floorsByFid;
    }

    @OneToMany(mappedBy = "floorByFid")
    public List<Floorinfo> getFloorinfosByFid() {
        return floorinfosByFid;
    }

    public void setFloorinfosByFid(List<Floorinfo> floorinfosByFid) {
        this.floorinfosByFid = floorinfosByFid;
    }
}
