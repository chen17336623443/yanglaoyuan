package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Checkin {
    private Integer cheId;
    private Integer omId;
    private Integer uid;
    private BigDecimal cheBed;
    private BigDecimal cheNursing;
    private BigDecimal cheCanying;
    private BigDecimal cheDue;
    private BigDecimal cheActual;
    private String cheMode;
    private String cheRemarks;
    private Oldman oldmanByOmId;
    private User userByUid;

    @Id
    @Column(name = "che_id", nullable = false)
    public Integer getCheId() {
        return cheId;
    }

    public void setCheId(Integer cheId) {
        this.cheId = cheId;
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
    @Column(name = "che_bed", nullable = false, precision = 2)
    public BigDecimal getCheBed() {
        return cheBed;
    }

    public void setCheBed(BigDecimal cheBed) {
        this.cheBed = cheBed;
    }

    @Basic
    @Column(name = "che_nursing", nullable = false, precision = 2)
    public BigDecimal getCheNursing() {
        return cheNursing;
    }

    public void setCheNursing(BigDecimal cheNursing) {
        this.cheNursing = cheNursing;
    }

    @Basic
    @Column(name = "che_canying", nullable = false, precision = 2)
    public BigDecimal getCheCanying() {
        return cheCanying;
    }

    public void setCheCanying(BigDecimal cheCanying) {
        this.cheCanying = cheCanying;
    }

    @Basic
    @Column(name = "che_due", nullable = false, precision = 2)
    public BigDecimal getCheDue() {
        return cheDue;
    }

    public void setCheDue(BigDecimal cheDue) {
        this.cheDue = cheDue;
    }

    @Basic
    @Column(name = "che_actual", nullable = false, precision = 2)
    public BigDecimal getCheActual() {
        return cheActual;
    }

    public void setCheActual(BigDecimal cheActual) {
        this.cheActual = cheActual;
    }

    @Basic
    @Column(name = "che_mode", nullable = false, length = 50)
    public String getCheMode() {
        return cheMode;
    }

    public void setCheMode(String cheMode) {
        this.cheMode = cheMode;
    }

    @Basic
    @Column(name = "che_remarks", nullable = false, length = 200)
    public String getCheRemarks() {
        return cheRemarks;
    }

    public void setCheRemarks(String cheRemarks) {
        this.cheRemarks = cheRemarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checkin checkin = (Checkin) o;
        return Objects.equals(cheId, checkin.cheId) &&
                Objects.equals(omId, checkin.omId) &&
                Objects.equals(uid, checkin.uid) &&
                Objects.equals(cheBed, checkin.cheBed) &&
                Objects.equals(cheNursing, checkin.cheNursing) &&
                Objects.equals(cheCanying, checkin.cheCanying) &&
                Objects.equals(cheDue, checkin.cheDue) &&
                Objects.equals(cheActual, checkin.cheActual) &&
                Objects.equals(cheMode, checkin.cheMode) &&
                Objects.equals(cheRemarks, checkin.cheRemarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cheId, omId, uid, cheBed, cheNursing, cheCanying, cheDue, cheActual, cheMode, cheRemarks);
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
