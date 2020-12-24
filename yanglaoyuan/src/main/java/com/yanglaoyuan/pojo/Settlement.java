package com.yanglaoyuan.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class Settlement {
    private Integer seId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = " GMT+8 " )
    private Timestamp seTime;
    private BigDecimal seDue;
    private BigDecimal seWithdrawn;
    private User userByUid;
    private Oldman oldmanByOmId;
    private List<Stayback> staybacksBySeId;

    @Id
    @Column(name = "se_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getSeId() {
        return seId;
    }

    public void setSeId(Integer seId) {
        this.seId = seId;
    }

    @Basic
    @Column(name = "se_time", nullable = true)
    public Timestamp getSeTime() {
        return seTime;
    }

    public void setSeTime(Timestamp seTime) {
        this.seTime = seTime;
    }

    @Basic
    @Column(name = "se_due", nullable = true, precision = 2)
    public BigDecimal getSeDue() {
        return seDue;
    }

    public void setSeDue(BigDecimal seDue) {
        this.seDue = seDue;
    }

    @Basic
    @Column(name = "se_withdrawn", nullable = true, precision = 2)
    public BigDecimal getSeWithdrawn() {
        return seWithdrawn;
    }

    public void setSeWithdrawn(BigDecimal seWithdrawn) {
        this.seWithdrawn = seWithdrawn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Settlement that = (Settlement) o;
        return Objects.equals(seId, that.seId) &&
                Objects.equals(seTime, that.seTime) &&
                Objects.equals(seDue, that.seDue) &&
                Objects.equals(seWithdrawn, that.seWithdrawn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seId, seTime, seDue, seWithdrawn);
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

    @OneToMany(mappedBy = "settlementBySeId")
    public List<Stayback> getStaybacksBySeId() {
        return staybacksBySeId;
    }

    public void setStaybacksBySeId(List<Stayback> staybacksBySeId) {
        this.staybacksBySeId = staybacksBySeId;
    }
}
