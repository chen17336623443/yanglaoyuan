package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Oorder {
    private Integer oId;
    private String omname;
    private String card;
    private String omsex;
    private Integer omage;
    private String omselfCare;
    private String oname;
    private String relation;
    private Timestamp newtime;
    private Long cphone;
    private Date ordertime;
    private Integer privateroom;
    private Double omoney;
    private String payment;
    private User userByUid;

    @Id
    @Column(name = "o_id", nullable = false)
    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
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
    @Column(name = "cphone", nullable = true)
    public Long getCphone() {
        return cphone;
    }

    public void setCphone(Long cphone) {
        this.cphone = cphone;
    }

    @Basic
    @Column(name = "ordertime", nullable = true)
    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    @Basic
    @Column(name = "privateroom", nullable = true)
    public Integer getPrivateroom() {
        return privateroom;
    }

    public void setPrivateroom(Integer privateroom) {
        this.privateroom = privateroom;
    }

    @Basic
    @Column(name = "omoney", nullable = true, precision = 2)
    public Double getOmoney() {
        return omoney;
    }

    public void setOmoney(Double omoney) {
        this.omoney = omoney;
    }

    @Basic
    @Column(name = "payment", nullable = true, length = 255)
    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oorder oorder = (Oorder) o;
        return Objects.equals(oId, oorder.oId) &&
                Objects.equals(omname, oorder.omname) &&
                Objects.equals(card, oorder.card) &&
                Objects.equals(omsex, oorder.omsex) &&
                Objects.equals(omage, oorder.omage) &&
                Objects.equals(omselfCare, oorder.omselfCare) &&
                Objects.equals(oname, oorder.oname) &&
                Objects.equals(relation, oorder.relation) &&
                Objects.equals(newtime, oorder.newtime) &&
                Objects.equals(cphone, oorder.cphone) &&
                Objects.equals(ordertime, oorder.ordertime) &&
                Objects.equals(privateroom, oorder.privateroom) &&
                Objects.equals(omoney, oorder.omoney) &&
                Objects.equals(payment, oorder.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oId, omname, card, omsex, omage, omselfCare, oname, relation, newtime, cphone, ordertime, privateroom, omoney, payment);
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
