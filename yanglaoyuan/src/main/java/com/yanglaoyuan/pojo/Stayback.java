package com.yanglaoyuan.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Stayback {
    private Integer stId;
    private String stType;
    private String stForm;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = " GMT+8 " )
    private Timestamp stTime;
    private String stReason;
    private User userByUid;
    private Settlement settlementBySeId;

    @Id
    @Column(name = "st_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getStId() {
        return stId;
    }

    public void setStId(Integer stId) {
        this.stId = stId;
    }

    @Basic
    @Column(name = "st_type", nullable = true, length = 30)
    public String getStType() {
        return stType;
    }

    public void setStType(String stType) {
        this.stType = stType;
    }

    @Basic
    @Column(name = "st_form", nullable = true, length = 30)
    public String getStForm() {
        return stForm;
    }

    public void setStForm(String stForm) {
        this.stForm = stForm;
    }

    @Basic
    @Column(name = "st_time", nullable = true)
    public Timestamp getStTime() {
        return stTime;
    }

    public void setStTime(Timestamp stTime) {
        this.stTime = stTime;
    }

    @Basic
    @Column(name = "st_reason", nullable = true, length = -1)
    public String getStReason() {
        return stReason;
    }

    public void setStReason(String stReason) {
        this.stReason = stReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stayback stayback = (Stayback) o;
        return Objects.equals(stId, stayback.stId) &&
                Objects.equals(stType, stayback.stType) &&
                Objects.equals(stForm, stayback.stForm) &&
                Objects.equals(stTime, stayback.stTime) &&
                Objects.equals(stReason, stayback.stReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stId, stType, stForm, stTime, stReason);
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
    @JoinColumn(name = "se_id", referencedColumnName = "se_id")
    public Settlement getSettlementBySeId() {
        return settlementBySeId;
    }

    public void setSettlementBySeId(Settlement settlementBySeId) {
        this.settlementBySeId = settlementBySeId;
    }
}
