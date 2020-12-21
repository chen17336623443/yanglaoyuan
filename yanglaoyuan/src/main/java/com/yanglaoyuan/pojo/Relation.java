package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Relation {
    private Integer rid;
    private String rname;
    private Long phone;
    private String card;
    private String relation;
    private String omsex;
    private String site;
    private String guardian;
    private User userByUid;
    private Oldman oldmanByOmId;

    @Id
    @Column(name = "rid", nullable = false)
    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    @Basic
    @Column(name = "rname", nullable = true, length = 255)
    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    @Basic
    @Column(name = "phone", nullable = true)
    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "card", nullable = true, length = 255)
    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @Basic
    @Column(name = "relation", nullable = true, length = 255)
    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    @Basic
    @Column(name = "omsex", nullable = true, length = 20)
    public String getOmsex() {
        return omsex;
    }

    public void setOmsex(String omsex) {
        this.omsex = omsex;
    }

    @Basic
    @Column(name = "site", nullable = true, length = 500)
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Basic
    @Column(name = "guardian", nullable = true, length = 50)
    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relation relation1 = (Relation) o;
        return Objects.equals(rid, relation1.rid) &&
                Objects.equals(rname, relation1.rname) &&
                Objects.equals(phone, relation1.phone) &&
                Objects.equals(card, relation1.card) &&
                Objects.equals(relation, relation1.relation) &&
                Objects.equals(omsex, relation1.omsex) &&
                Objects.equals(site, relation1.site) &&
                Objects.equals(guardian, relation1.guardian);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rid, rname, phone, card, relation, omsex, site, guardian);
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
