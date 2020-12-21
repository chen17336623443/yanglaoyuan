package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Outregistration {
    private Integer outId;
    private Timestamp outTime;
    private String outAccompanyname;
    private String outAccompanyphone;
    private Timestamp outReturntime;
    private Timestamp outReturnestimate;
    private Integer outOutsum;
    private String outReason;
    private Integer outTrue;
    private Oldman oldmanByOmId;
    private User userByUseUid;

    @Id
    @Column(name = "out_id", nullable = false)
    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    @Basic
    @Column(name = "out_time", nullable = true)
    public Timestamp getOutTime() {
        return outTime;
    }

    public void setOutTime(Timestamp outTime) {
        this.outTime = outTime;
    }

    @Basic
    @Column(name = "out_accompanyname", nullable = true, length = 30)
    public String getOutAccompanyname() {
        return outAccompanyname;
    }

    public void setOutAccompanyname(String outAccompanyname) {
        this.outAccompanyname = outAccompanyname;
    }

    @Basic
    @Column(name = "out_accompanyphone", nullable = true, length = 11)
    public String getOutAccompanyphone() {
        return outAccompanyphone;
    }

    public void setOutAccompanyphone(String outAccompanyphone) {
        this.outAccompanyphone = outAccompanyphone;
    }

    @Basic
    @Column(name = "out_returntime", nullable = true)
    public Timestamp getOutReturntime() {
        return outReturntime;
    }

    public void setOutReturntime(Timestamp outReturntime) {
        this.outReturntime = outReturntime;
    }

    @Basic
    @Column(name = "out_returnestimate", nullable = true)
    public Timestamp getOutReturnestimate() {
        return outReturnestimate;
    }

    public void setOutReturnestimate(Timestamp outReturnestimate) {
        this.outReturnestimate = outReturnestimate;
    }

    @Basic
    @Column(name = "out_outsum", nullable = true)
    public Integer getOutOutsum() {
        return outOutsum;
    }

    public void setOutOutsum(Integer outOutsum) {
        this.outOutsum = outOutsum;
    }

    @Basic
    @Column(name = "out_reason", nullable = true, length = -1)
    public String getOutReason() {
        return outReason;
    }

    public void setOutReason(String outReason) {
        this.outReason = outReason;
    }

    @Basic
    @Column(name = "out_true", nullable = true)
    public Integer getOutTrue() {
        return outTrue;
    }

    public void setOutTrue(Integer outTrue) {
        this.outTrue = outTrue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outregistration that = (Outregistration) o;
        return Objects.equals(outId, that.outId) &&
                Objects.equals(outTime, that.outTime) &&
                Objects.equals(outAccompanyname, that.outAccompanyname) &&
                Objects.equals(outAccompanyphone, that.outAccompanyphone) &&
                Objects.equals(outReturntime, that.outReturntime) &&
                Objects.equals(outReturnestimate, that.outReturnestimate) &&
                Objects.equals(outOutsum, that.outOutsum) &&
                Objects.equals(outReason, that.outReason) &&
                Objects.equals(outTrue, that.outTrue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(outId, outTime, outAccompanyname, outAccompanyphone, outReturntime, outReturnestimate, outOutsum, outReason, outTrue);
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
    @JoinColumn(name = "use_uid", referencedColumnName = "uid")
    public User getUserByUseUid() {
        return userByUseUid;
    }

    public void setUserByUseUid(User userByUseUid) {
        this.userByUseUid = userByUseUid;
    }
}
