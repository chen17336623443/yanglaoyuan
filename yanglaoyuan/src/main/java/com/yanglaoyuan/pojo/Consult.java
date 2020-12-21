package com.yanglaoyuan.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Consult {
    private Integer cId;
    private String omname;
    private String card;
    private String omsex;
    private Integer omage;
    private String omselfCare;
    private String oname;
    private String relation;
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" , timezone = " GMT+8 " )
    private Timestamp newtime;
    private String way;
    private Long cphone;
    private String content;

    @Id
    @Column(name = "c_id", nullable = false)
    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    @Basic
    @Column(name = "omname", nullable = true, length = 255)
    public String getOmname() {
        return omname;
    }

    public void setOmname(String omname) {
        this.omname = omname;
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
    @Column(name = "omsex", nullable = true, length = 20)
    public String getOmsex() {
        return omsex;
    }

    public void setOmsex(String omsex) {
        this.omsex = omsex;
    }

    @Basic
    @Column(name = "omage", nullable = true)
    public Integer getOmage() {
        return omage;
    }

    public void setOmage(Integer omage) {
        this.omage = omage;
    }

    @Basic
    @Column(name = "omself_care", nullable = true, length = 255)
    public String getOmselfCare() {
        return omselfCare;
    }

    public void setOmselfCare(String omselfCare) {
        this.omselfCare = omselfCare;
    }

    @Basic
    @Column(name = "oname", nullable = true, length = 255)
    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
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
    @Column(name = "newtime", nullable = true)
    public Timestamp getNewtime() {
        return newtime;
    }

    public void setNewtime(Timestamp newtime) {
        this.newtime = newtime;
    }

    @Basic
    @Column(name = "way", nullable = true, length = 255)
    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    @Basic
    @Column(name = "cphone", nullable = true)
    public Long getCphone() {
        return cphone;
    }

    public void setCphone(Long cphone) {
        this.cphone = cphone;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 2000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consult consult = (Consult) o;
        return Objects.equals(cId, consult.cId) &&
                Objects.equals(omname, consult.omname) &&
                Objects.equals(card, consult.card) &&
                Objects.equals(omsex, consult.omsex) &&
                Objects.equals(omage, consult.omage) &&
                Objects.equals(omselfCare, consult.omselfCare) &&
                Objects.equals(oname, consult.oname) &&
                Objects.equals(relation, consult.relation) &&
                Objects.equals(newtime, consult.newtime) &&
                Objects.equals(way, consult.way) &&
                Objects.equals(cphone, consult.cphone) &&
                Objects.equals(content, consult.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cId, omname, card, omsex, omage, omselfCare, oname, relation, newtime, way, cphone, content);
    }
}
