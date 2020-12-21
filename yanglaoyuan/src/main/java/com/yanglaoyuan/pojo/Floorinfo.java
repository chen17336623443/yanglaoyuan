package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Floorinfo {
    private Integer fiid;
    private String fiinfo;
    private List<Culturalhouse> culturalhousesByFiid;
    private List<Equipmenthouse> equipmenthousesByFiid;
    private Floor floorByFid;
    private List<Publicshouse> publicshousesByFiid;
    private List<Safetyhouse> safetyhousesByFiid;

    @Id
    @Column(name = "fiid", nullable = false)
    public Integer getFiid() {
        return fiid;
    }

    public void setFiid(Integer fiid) {
        this.fiid = fiid;
    }


    @Basic
    @Column(name = "fiinfo", nullable = true, length = 500)
    public String getFiinfo() {
        return fiinfo;
    }

    public void setFiinfo(String fiinfo) {
        this.fiinfo = fiinfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Floorinfo floorinfo = (Floorinfo) o;
        return Objects.equals(fiid, floorinfo.fiid) &&
                Objects.equals(fiinfo, floorinfo.fiinfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fiid, fiinfo);
    }

    @OneToMany(mappedBy = "floorinfoByFiid")
    public List<Culturalhouse> getCulturalhousesByFiid() {
        return culturalhousesByFiid;
    }

    public void setCulturalhousesByFiid(List<Culturalhouse> culturalhousesByFiid) {
        this.culturalhousesByFiid = culturalhousesByFiid;
    }

    @OneToMany(mappedBy = "floorinfoByFiid")
    public List<Equipmenthouse> getEquipmenthousesByFiid() {
        return equipmenthousesByFiid;
    }

    public void setEquipmenthousesByFiid(List<Equipmenthouse> equipmenthousesByFiid) {
        this.equipmenthousesByFiid = equipmenthousesByFiid;
    }

    @ManyToOne
    @JoinColumn(name = "fid", referencedColumnName = "fid")
    public Floor getFloorByFid() {
        return floorByFid;
    }

    public void setFloorByFid(Floor floorByFid) {
        this.floorByFid = floorByFid;
    }

    @OneToMany(mappedBy = "floorinfoByFiid")
    public List<Publicshouse> getPublicshousesByFiid() {
        return publicshousesByFiid;
    }

    public void setPublicshousesByFiid(List<Publicshouse> publicshousesByFiid) {
        this.publicshousesByFiid = publicshousesByFiid;
    }

    @OneToMany(mappedBy = "floorinfoByFiid")
    public List<Safetyhouse> getSafetyhousesByFiid() {
        return safetyhousesByFiid;
    }

    public void setSafetyhousesByFiid(List<Safetyhouse> safetyhousesByFiid) {
        this.safetyhousesByFiid = safetyhousesByFiid;
    }
}
