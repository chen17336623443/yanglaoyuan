package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
public class Food {
    private Integer fId;
    private String fName;
    private BigDecimal fPrice;
    private String fPiquancy;
    private String fTaste;
    private String fImg;
    private Integer fSales;
    private String fRemark;
    private Foodtype foodtypeByFtId;
    private List<Shiwuzhongjianbiao> shiwuzhongjianbiaosByFId;
    private List<Taboocrowd> taboocrowds;
    private List<Foodorderdetails> foodorderdetails;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_id", nullable = false)
    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    @Basic
    @Column(name = "f_name", nullable = true, length = 100)
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    @Basic
    @Column(name = "f_price", nullable = true, precision = 1)
    public BigDecimal getfPrice() {
        return fPrice;
    }

    public void setfPrice(BigDecimal fPrice) {
        this.fPrice = fPrice;
    }

    @Basic
    @Column(name = "f_piquancy", nullable = true, length = 30)
    public String getfPiquancy() {
        return fPiquancy;
    }

    public void setfPiquancy(String fPiquancy) {
        this.fPiquancy = fPiquancy;
    }

    @Basic
    @Column(name = "f_taste", nullable = true, length = 100)
    public String getfTaste() {
        return fTaste;
    }

    public void setfTaste(String fTaste) {
        this.fTaste = fTaste;
    }

    @Basic
    @Column(name = "f_img", nullable = true, length = 255)
    public String getfImg() {
        return fImg;
    }

    public void setfImg(String fImg) {
        this.fImg = fImg;
    }

    @Basic
    @Column(name = "f_sales")
    public Integer getfSales() {
        return fSales;
    }

    public void setfSales(Integer fSales) {
        this.fSales = fSales;
    }

    @Basic
    @Column(name = "f_remark", nullable = true, length = 255)
    public String getfRemark() {
        return fRemark;
    }

    public void setfRemark(String fRemark) {
        this.fRemark = fRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(fId, food.fId) &&
                Objects.equals(fName, food.fName) &&
                Objects.equals(fPrice, food.fPrice) &&
                Objects.equals(fPiquancy, food.fPiquancy) &&
                Objects.equals(fTaste, food.fTaste) &&
                Objects.equals(fImg, food.fImg) &&
                Objects.equals(fSales, food.fSales) &&
                Objects.equals(fRemark, food.fRemark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fId, fName, fPrice, fPiquancy, fTaste, fImg, fSales, fRemark);
    }

    @ManyToOne
    @JoinColumn(name = "ft_id", referencedColumnName = "ft_id")
    public Foodtype getFoodtypeByFtId() {
        return foodtypeByFtId;
    }

    public void setFoodtypeByFtId(Foodtype foodtypeByFtId) {
        this.foodtypeByFtId = foodtypeByFtId;
    }

    @OneToMany(mappedBy = "foodByFId")
    public List<Shiwuzhongjianbiao> getShiwuzhongjianbiaosByFId() {
        return shiwuzhongjianbiaosByFId;
    }

    public void setShiwuzhongjianbiaosByFId(List<Shiwuzhongjianbiao> shiwuzhongjianbiaosByFId) {
        this.shiwuzhongjianbiaosByFId = shiwuzhongjianbiaosByFId;
    }

    @ManyToMany
    @JoinTable(name = "foodtaboocrowd", catalog = "", schema = "yanglaoyuan", joinColumns = @JoinColumn(name = "f_id", referencedColumnName = "f_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "tc_id", referencedColumnName = "tc_id", nullable = false))
    public List<Taboocrowd> getTaboocrowds() {
        return taboocrowds;
    }

    public void setTaboocrowds(List<Taboocrowd> taboocrowds) {
        this.taboocrowds = taboocrowds;
    }

    @OneToMany(mappedBy = "food")
    public List<Foodorderdetails> getFoodorderdetails() {
        return foodorderdetails;
    }

    public void setFoodorderdetails(List<Foodorderdetails> foodorderdetails) {
        this.foodorderdetails = foodorderdetails;
    }
}
