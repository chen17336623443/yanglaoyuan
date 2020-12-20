package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class User {
    private Integer uid;
    private String uname;
    private String uphone;
    private String upass;
    private List<Accident> accidentsByUid;
    private List<Checkin> checkinsByUid;
    private List<Costflow> costflowsByUid;
    private List<Deposit> depositsByUid;
    private List<Health> healthByUid;
    private List<Oorder> oordersByUid;
    private List<Outregistration> outregistrationsByUid;
    private List<Register> registersByUid;
    private List<Relation> relationsByUid;
    private List<Settlement> settlementsByUid;
    private List<Stayback> staybacksByUid;
    private List<Tryout> tryoutsByUid;
    private List<Visit> visitsByUid;

    @Id
    @Column(name = "uid", nullable = false)
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "uname", nullable = true, length = 30)
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Basic
    @Column(name = "uphone", nullable = true, length = 11)
    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    @Basic
    @Column(name = "upass", nullable = true, length = 30)
    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid) &&
                Objects.equals(uname, user.uname) &&
                Objects.equals(uphone, user.uphone) &&
                Objects.equals(upass, user.upass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, uname, uphone, upass);
    }

    @OneToMany(mappedBy = "userByUid")
    public List<Accident> getAccidentsByUid() {
        return accidentsByUid;
    }

    public void setAccidentsByUid(List<Accident> accidentsByUid) {
        this.accidentsByUid = accidentsByUid;
    }

    @OneToMany(mappedBy = "userByUid")
    public List<Checkin> getCheckinsByUid() {
        return checkinsByUid;
    }

    public void setCheckinsByUid(List<Checkin> checkinsByUid) {
        this.checkinsByUid = checkinsByUid;
    }

    @OneToMany(mappedBy = "userByUid")
    public List<Costflow> getCostflowsByUid() {
        return costflowsByUid;
    }

    public void setCostflowsByUid(List<Costflow> costflowsByUid) {
        this.costflowsByUid = costflowsByUid;
    }

    @OneToMany(mappedBy = "userByUid")
    public List<Deposit> getDepositsByUid() {
        return depositsByUid;
    }

    public void setDepositsByUid(List<Deposit> depositsByUid) {
        this.depositsByUid = depositsByUid;
    }

    @OneToMany(mappedBy = "userByUid")
    public List<Health> getHealthByUid() {
        return healthByUid;
    }

    public void setHealthByUid(List<Health> healthByUid) {
        this.healthByUid = healthByUid;
    }

    @OneToMany(mappedBy = "userByUid")
    public List<Oorder> getOordersByUid() {
        return oordersByUid;
    }

    public void setOordersByUid(List<Oorder> oordersByUid) {
        this.oordersByUid = oordersByUid;
    }

    @OneToMany(mappedBy = "userByUseUid")
    public List<Outregistration> getOutregistrationsByUid() {
        return outregistrationsByUid;
    }

    public void setOutregistrationsByUid(List<Outregistration> outregistrationsByUid) {
        this.outregistrationsByUid = outregistrationsByUid;
    }

    @OneToMany(mappedBy = "userByUid")
    public List<Register> getRegistersByUid() {
        return registersByUid;
    }

    public void setRegistersByUid(List<Register> registersByUid) {
        this.registersByUid = registersByUid;
    }

    @OneToMany(mappedBy = "userByUid")
    public List<Relation> getRelationsByUid() {
        return relationsByUid;
    }

    public void setRelationsByUid(List<Relation> relationsByUid) {
        this.relationsByUid = relationsByUid;
    }

    @OneToMany(mappedBy = "userByUid")
    public List<Settlement> getSettlementsByUid() {
        return settlementsByUid;
    }

    public void setSettlementsByUid(List<Settlement> settlementsByUid) {
        this.settlementsByUid = settlementsByUid;
    }

    @OneToMany(mappedBy = "userByUid")
    public List<Stayback> getStaybacksByUid() {
        return staybacksByUid;
    }

    public void setStaybacksByUid(List<Stayback> staybacksByUid) {
        this.staybacksByUid = staybacksByUid;
    }

    @OneToMany(mappedBy = "userByUid")
    public List<Tryout> getTryoutsByUid() {
        return tryoutsByUid;
    }

    public void setTryoutsByUid(List<Tryout> tryoutsByUid) {
        this.tryoutsByUid = tryoutsByUid;
    }

    @OneToMany(mappedBy = "userByUid")
    public List<Visit> getVisitsByUid() {
        return visitsByUid;
    }

    public void setVisitsByUid(List<Visit> visitsByUid) {
        this.visitsByUid = visitsByUid;
    }
}
