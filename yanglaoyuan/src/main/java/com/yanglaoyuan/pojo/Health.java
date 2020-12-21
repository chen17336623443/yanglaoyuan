package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Health {
    private Integer hid;
    private String capacity;
    private String accommodation;
    private String vision;
    private String hearing;
    private String medical;
    private User userByUid;
    private Oldman oldmanByOmId;

    @Id
    @Column(name = "hid", nullable = false)
    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    @Basic
    @Column(name = "capacity", nullable = true, length = 255)
    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "accommodation", nullable = true, length = 255)
    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    @Basic
    @Column(name = "vision", nullable = true, length = 255)
    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    @Basic
    @Column(name = "hearing", nullable = true, length = 255)
    public String getHearing() {
        return hearing;
    }

    public void setHearing(String hearing) {
        this.hearing = hearing;
    }

    @Basic
    @Column(name = "medical", nullable = true, length = 2000)
    public String getMedical() {
        return medical;
    }

    public void setMedical(String medical) {
        this.medical = medical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Health health = (Health) o;
        return Objects.equals(hid, health.hid) &&
                Objects.equals(capacity, health.capacity) &&
                Objects.equals(accommodation, health.accommodation) &&
                Objects.equals(vision, health.vision) &&
                Objects.equals(hearing, health.hearing) &&
                Objects.equals(medical, health.medical);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hid, capacity, accommodation, vision, hearing, medical);
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
