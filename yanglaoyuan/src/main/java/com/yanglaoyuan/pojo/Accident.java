package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Accident {
    private Integer accId;
    private Integer omId;
    private Integer uid;
    private Timestamp accTime;
    private Integer accType;
    private String accReason;
    private String accDescribe;
    private Oldman oldmanByOmId;
    private User userByUid;

    @Id
    @Column(name = "acc_id", nullable = false)
    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    @Basic
    @Column(name = "om_id", nullable = true)
    public Integer getOmId() {
        return omId;
    }

    public void setOmId(Integer omId) {
        this.omId = omId;
    }

    @Basic
    @Column(name = "uid", nullable = true)
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "acc_time", nullable = true)
    public Timestamp getAccTime() {
        return accTime;
    }

    public void setAccTime(Timestamp accTime) {
        this.accTime = accTime;
    }

    @Basic
    @Column(name = "acc_type", nullable = true)
    public Integer getAccType() {
        return accType;
    }

    public void setAccType(Integer accType) {
        this.accType = accType;
    }

    @Basic
    @Column(name = "acc_reason", nullable = true, length = -1)
    public String getAccReason() {
        return accReason;
    }

    public void setAccReason(String accReason) {
        this.accReason = accReason;
    }

    @Basic
    @Column(name = "acc_describe", nullable = true, length = -1)
    public String getAccDescribe() {
        return accDescribe;
    }

    public void setAccDescribe(String accDescribe) {
        this.accDescribe = accDescribe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accident accident = (Accident) o;
        return Objects.equals(accId, accident.accId) &&
                Objects.equals(omId, accident.omId) &&
                Objects.equals(uid, accident.uid) &&
                Objects.equals(accTime, accident.accTime) &&
                Objects.equals(accType, accident.accType) &&
                Objects.equals(accReason, accident.accReason) &&
                Objects.equals(accDescribe, accident.accDescribe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accId, omId, uid, accTime, accType, accReason, accDescribe);
    }

    @ManyToOne
    @JoinColumn(name = "om_id", referencedColumnName = "om_id")
    public Oldman getOldmanByOmId() {
        return oldmanByOmId;
    }

    public void setOldmanByOmId(Oldman oldmanByOmId) {
        this.oldmanByOmId = oldmanByOmId;
    }

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    public User getUserByUid() {
        return userByUid;
    }

    public void setUserByUid(User userByUid) {
        this.userByUid = userByUid;
    }
}
