package com.yanglaoyuan.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Visit {
    private Integer vId;
    private String vName;
    private String vPhone;
    private Integer vRelationship;
    private String vReason;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = " GMT+8 " )
    private Timestamp vVisittime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = " GMT+8 " )
    private Timestamp vLeavetime;
    private String vRemarks;
    private User userByUid;
    private Oldman oldmanByOmId;

    @Id
    @Column(name = "v_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getvId() {
        return vId;
    }

    public void setvId(Integer vId) {
        this.vId = vId;
    }

    @Basic
    @Column(name = "v_name", nullable = true, length = 30)
    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    @Basic
    @Column(name = "v_phone", nullable = true, length = 11)
    public String getvPhone() {
        return vPhone;
    }

    public void setvPhone(String vPhone) {
        this.vPhone = vPhone;
    }

    @Basic
    @Column(name = "v_relationship", nullable = true)
    public Integer getvRelationship() {
        return vRelationship;
    }

    public void setvRelationship(Integer vRelationship) {
        this.vRelationship = vRelationship;
    }

    @Basic
    @Column(name = "v_reason", nullable = true, length = -1)
    public String getvReason() {
        return vReason;
    }

    public void setvReason(String vReason) {
        this.vReason = vReason;
    }

    @Basic
    @Column(name = "v_visittime", nullable = true)
    public Timestamp getvVisittime() {
        return vVisittime;
    }

    public void setvVisittime(Timestamp vVisittime) {
        this.vVisittime = vVisittime;
    }

    @Basic
    @Column(name = "v_leavetime", nullable = true)
    public Timestamp getvLeavetime() {
        return vLeavetime;
    }

    public void setvLeavetime(Timestamp vLeavetime) {
        this.vLeavetime = vLeavetime;
    }

    @Basic
    @Column(name = "v_remarks", nullable = true, length = -1)
    public String getvRemarks() {
        return vRemarks;
    }

    public void setvRemarks(String vRemarks) {
        this.vRemarks = vRemarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(vId, visit.vId) &&
                Objects.equals(vName, visit.vName) &&
                Objects.equals(vPhone, visit.vPhone) &&
                Objects.equals(vRelationship, visit.vRelationship) &&
                Objects.equals(vReason, visit.vReason) &&
                Objects.equals(vVisittime, visit.vVisittime) &&
                Objects.equals(vLeavetime, visit.vLeavetime) &&
                Objects.equals(vRemarks, visit.vRemarks);
    }

    @Override
    public String toString() {
        return "Visit{" +
                "vId=" + vId +
                ", vName='" + vName + '\'' +
                ", vPhone='" + vPhone + '\'' +
                ", vRelationship=" + vRelationship +
                ", vReason='" + vReason + '\'' +
                ", vVisittime=" + vVisittime +
                ", vLeavetime=" + vLeavetime +
                ", vRemarks='" + vRemarks + '\'' +
                ", userByUid=" + userByUid +
                ", oldmanByOmId=" + oldmanByOmId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(vId, vName, vPhone, vRelationship, vReason, vVisittime, vLeavetime, vRemarks);
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
