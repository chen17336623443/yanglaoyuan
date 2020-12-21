package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Tryout {
    private Integer tomId;
    private String tomName;
    private String tomCard;
    private String tomSex;
    private Long tomNumber;
    private String tomAddress;
    private String tomCategory;
    private String tomDiet;
    private String tomLabour;
    private Timestamp tomCheckIntime;
    private String tomFn;
    private String tomImg;
    private Timestamp tomBigintime;
    private Timestamp tomEndtime;
    private User userByUid;

    @Id
    @Column(name = "tom_id", nullable = false)
    public Integer getTomId() {
        return tomId;
    }

    public void setTomId(Integer tomId) {
        this.tomId = tomId;
    }

    @Basic
    @Column(name = "tom_name", nullable = true, length = 255)
    public String getTomName() {
        return tomName;
    }

    public void setTomName(String tomName) {
        this.tomName = tomName;
    }

    @Basic
    @Column(name = "tom_card", nullable = true, length = 255)
    public String getTomCard() {
        return tomCard;
    }

    public void setTomCard(String tomCard) {
        this.tomCard = tomCard;
    }

    @Basic
    @Column(name = "tom_sex", nullable = true, length = 22)
    public String getTomSex() {
        return tomSex;
    }

    public void setTomSex(String tomSex) {
        this.tomSex = tomSex;
    }

    @Basic
    @Column(name = "tom_number", nullable = true)
    public Long getTomNumber() {
        return tomNumber;
    }

    public void setTomNumber(Long tomNumber) {
        this.tomNumber = tomNumber;
    }

    @Basic
    @Column(name = "tom_address", nullable = true, length = 255)
    public String getTomAddress() {
        return tomAddress;
    }

    public void setTomAddress(String tomAddress) {
        this.tomAddress = tomAddress;
    }

    @Basic
    @Column(name = "tom_category", nullable = true, length = 255)
    public String getTomCategory() {
        return tomCategory;
    }

    public void setTomCategory(String tomCategory) {
        this.tomCategory = tomCategory;
    }

    @Basic
    @Column(name = "tom_diet", nullable = true, length = 255)
    public String getTomDiet() {
        return tomDiet;
    }

    public void setTomDiet(String tomDiet) {
        this.tomDiet = tomDiet;
    }

    @Basic
    @Column(name = "tom_labour", nullable = true, length = 255)
    public String getTomLabour() {
        return tomLabour;
    }

    public void setTomLabour(String tomLabour) {
        this.tomLabour = tomLabour;
    }

    @Basic
    @Column(name = "tom_check_intime", nullable = true)
    public Timestamp getTomCheckIntime() {
        return tomCheckIntime;
    }

    public void setTomCheckIntime(Timestamp tomCheckIntime) {
        this.tomCheckIntime = tomCheckIntime;
    }

    @Basic
    @Column(name = "tom_fn", nullable = true, length = 255)
    public String getTomFn() {
        return tomFn;
    }

    public void setTomFn(String tomFn) {
        this.tomFn = tomFn;
    }

    @Basic
    @Column(name = "tom_img", nullable = true, length = 255)
    public String getTomImg() {
        return tomImg;
    }

    public void setTomImg(String tomImg) {
        this.tomImg = tomImg;
    }

    @Basic
    @Column(name = "tom_bigintime", nullable = true)
    public Timestamp getTomBigintime() {
        return tomBigintime;
    }

    public void setTomBigintime(Timestamp tomBigintime) {
        this.tomBigintime = tomBigintime;
    }

    @Basic
    @Column(name = "tom_endtime", nullable = true)
    public Timestamp getTomEndtime() {
        return tomEndtime;
    }

    public void setTomEndtime(Timestamp tomEndtime) {
        this.tomEndtime = tomEndtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tryout tryout = (Tryout) o;
        return Objects.equals(tomId, tryout.tomId) &&
                Objects.equals(tomName, tryout.tomName) &&
                Objects.equals(tomCard, tryout.tomCard) &&
                Objects.equals(tomSex, tryout.tomSex) &&
                Objects.equals(tomNumber, tryout.tomNumber) &&
                Objects.equals(tomAddress, tryout.tomAddress) &&
                Objects.equals(tomCategory, tryout.tomCategory) &&
                Objects.equals(tomDiet, tryout.tomDiet) &&
                Objects.equals(tomLabour, tryout.tomLabour) &&
                Objects.equals(tomCheckIntime, tryout.tomCheckIntime) &&
                Objects.equals(tomFn, tryout.tomFn) &&
                Objects.equals(tomImg, tryout.tomImg) &&
                Objects.equals(tomBigintime, tryout.tomBigintime) &&
                Objects.equals(tomEndtime, tryout.tomEndtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tomId, tomName, tomCard, tomSex, tomNumber, tomAddress, tomCategory, tomDiet, tomLabour, tomCheckIntime, tomFn, tomImg, tomBigintime, tomEndtime);
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
