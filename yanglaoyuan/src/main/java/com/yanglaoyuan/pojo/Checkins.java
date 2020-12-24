package com.yanglaoyuan.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: 阳宇
 * @create: 2020-12-23 09:30
 */
@Entity
public class Checkins {
    private Integer checkinid;
    private String accomplish;
    private String checkintype;
    @JsonFormat( pattern = "yyyy-MM-dd" , timezone = " GMT+8 " )
    private Date checkinbegin;
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" , timezone = " GMT+8 " )
    private Timestamp checkinnew;
    @JsonFormat( pattern = "yyyy-MM-dd" , timezone = " GMT+8 " )
    private Date checkinend;
    private Oldman myoldman;

    @Id
    @Column(name = "checkinid", nullable = false)
    public Integer getCheckinid() {
        return checkinid;
    }

    public void setCheckinid(Integer checkinid) {
        this.checkinid = checkinid;
    }

    @Basic
    @Column(name = "accomplish", nullable = true, length = 255)
    public String getAccomplish() {
        return accomplish;
    }

    public void setAccomplish(String accomplish) {
        this.accomplish = accomplish;
    }

    @Basic
    @Column(name = "checkintype", nullable = true, length = 255)
    public String getCheckintype() {
        return checkintype;
    }

    public void setCheckintype(String checkintype) {
        this.checkintype = checkintype;
    }

    @Basic
    @Column(name = "checkinbegin", nullable = true)
    public Date getCheckinbegin() {
        return checkinbegin;
    }

    public void setCheckinbegin(Date checkinbegin) {
        this.checkinbegin = checkinbegin;
    }

    @Basic
    @Column(name = "checkinnew", nullable = true)
    public Timestamp getCheckinnew() {
        return checkinnew;
    }

    public void setCheckinnew(Timestamp checkinnew) {
        this.checkinnew = checkinnew;
    }

    @Basic
    @Column(name = "checkinend", nullable = true)
    public Date getCheckinend() {
        return checkinend;
    }

    public void setCheckinend(Date checkinend) {
        this.checkinend = checkinend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checkins checkins = (Checkins) o;
        return Objects.equals(checkinid, checkins.checkinid) &&
                Objects.equals(accomplish, checkins.accomplish) &&
                Objects.equals(checkintype, checkins.checkintype) &&
                Objects.equals(checkinbegin, checkins.checkinbegin) &&
                Objects.equals(checkinnew, checkins.checkinnew) &&
                Objects.equals(checkinend, checkins.checkinend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkinid, accomplish, checkintype, checkinbegin, checkinnew, checkinend);
    }

    @ManyToOne
    @JoinColumn(name = "om_id", referencedColumnName = "om_id")
    public Oldman getMyoldman() {
        return myoldman;
    }

    public void setMyoldman(Oldman myoldman) {
        this.myoldman = myoldman;
    }
}
