package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class Oldman {
    private Integer omId;
    private Integer tslId;
    private Integer acId;
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
    private List<Accident> accidentsByOmId;
    private List<Arrears> arrearsByOmId;
    private List<Checkin> checkinsByOmId;
    private List<Costflow> costflowsByOmId;
    private List<Deposit> depositsByOmId;
    private List<Foodorder> foodordersByOmId;
    private List<Health> healthByOmId;
    private List<Nursingrecord> nursingrecordsByOmId;
    private Thenursinglevel thenursinglevelByTslId;
    private Acare acareByAcId;
    private List<Outregistration> outregistrationsByOmId;
    private List<Register> registersByOmId;
    private List<Relation> relationsByOmId;
    private List<Settlement> settlementsByOmId;
    private List<Visit> visitsByOmId;

    @Id
    @Column(name = "om_id", nullable = false)
    public Integer getOmId() {
        return omId;
    }

    public void setOmId(Integer omId) {
        this.omId = omId;
    }

    @Basic
    @Column(name = "tsl_id", nullable = true)
    public Integer getTslId() {
        return tslId;
    }

    public void setTslId(Integer tslId) {
        this.tslId = tslId;
    }

    @Basic
    @Column(name = "ac_id", nullable = true)
    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oldman oldman = (Oldman) o;
        return Objects.equals(omId, oldman.omId) &&
                Objects.equals(tslId, oldman.tslId) &&
                Objects.equals(acId, oldman.acId) &&
                Objects.equals(tomName, oldman.tomName) &&
                Objects.equals(tomCard, oldman.tomCard) &&
                Objects.equals(tomSex, oldman.tomSex) &&
                Objects.equals(tomNumber, oldman.tomNumber) &&
                Objects.equals(tomAddress, oldman.tomAddress) &&
                Objects.equals(tomCategory, oldman.tomCategory) &&
                Objects.equals(tomDiet, oldman.tomDiet) &&
                Objects.equals(tomLabour, oldman.tomLabour) &&
                Objects.equals(tomCheckIntime, oldman.tomCheckIntime) &&
                Objects.equals(tomFn, oldman.tomFn) &&
                Objects.equals(tomImg, oldman.tomImg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(omId, tslId, acId, tomName, tomCard, tomSex, tomNumber, tomAddress, tomCategory, tomDiet, tomLabour, tomCheckIntime, tomFn, tomImg);
    }

    @OneToMany(mappedBy = "oldmanByOmId")
    public List<Accident> getAccidentsByOmId() {
        return accidentsByOmId;
    }

    public void setAccidentsByOmId(List<Accident> accidentsByOmId) {
        this.accidentsByOmId = accidentsByOmId;
    }

    @OneToMany(mappedBy = "oldmanByOmId")
    public List<Arrears> getArrearsByOmId() {
        return arrearsByOmId;
    }

    public void setArrearsByOmId(List<Arrears> arrearsByOmId) {
        this.arrearsByOmId = arrearsByOmId;
    }

    @OneToMany(mappedBy = "oldmanByOmId")
    public List<Checkin> getCheckinsByOmId() {
        return checkinsByOmId;
    }

    public void setCheckinsByOmId(List<Checkin> checkinsByOmId) {
        this.checkinsByOmId = checkinsByOmId;
    }

    @OneToMany(mappedBy = "oldmanByOmId")
    public List<Costflow> getCostflowsByOmId() {
        return costflowsByOmId;
    }

    public void setCostflowsByOmId(List<Costflow> costflowsByOmId) {
        this.costflowsByOmId = costflowsByOmId;
    }

    @OneToMany(mappedBy = "oldmanByOmId")
    public List<Deposit> getDepositsByOmId() {
        return depositsByOmId;
    }

    public void setDepositsByOmId(List<Deposit> depositsByOmId) {
        this.depositsByOmId = depositsByOmId;
    }

    @OneToMany(mappedBy = "oldmanByOmId")
    public List<Foodorder> getFoodordersByOmId() {
        return foodordersByOmId;
    }

    public void setFoodordersByOmId(List<Foodorder> foodordersByOmId) {
        this.foodordersByOmId = foodordersByOmId;
    }

    @OneToMany(mappedBy = "oldmanByOmId")
    public List<Health> getHealthByOmId() {
        return healthByOmId;
    }

    public void setHealthByOmId(List<Health> healthByOmId) {
        this.healthByOmId = healthByOmId;
    }

    @OneToMany(mappedBy = "oldmanByOmId")
    public List<Nursingrecord> getNursingrecordsByOmId() {
        return nursingrecordsByOmId;
    }

    public void setNursingrecordsByOmId(List<Nursingrecord> nursingrecordsByOmId) {
        this.nursingrecordsByOmId = nursingrecordsByOmId;
    }

    @ManyToOne
    @JoinColumn(name = "tsl_id", referencedColumnName = "tsl_id")
    public Thenursinglevel getThenursinglevelByTslId() {
        return thenursinglevelByTslId;
    }

    public void setThenursinglevelByTslId(Thenursinglevel thenursinglevelByTslId) {
        this.thenursinglevelByTslId = thenursinglevelByTslId;
    }

    @ManyToOne
    @JoinColumn(name = "ac_id", referencedColumnName = "ac_id")
    public Acare getAcareByAcId() {
        return acareByAcId;
    }

    public void setAcareByAcId(Acare acareByAcId) {
        this.acareByAcId = acareByAcId;
    }

    @OneToMany(mappedBy = "oldmanByOmId")
    public List<Outregistration> getOutregistrationsByOmId() {
        return outregistrationsByOmId;
    }

    public void setOutregistrationsByOmId(List<Outregistration> outregistrationsByOmId) {
        this.outregistrationsByOmId = outregistrationsByOmId;
    }

    @OneToMany(mappedBy = "oldmanByOmId")
    public List<Register> getRegistersByOmId() {
        return registersByOmId;
    }

    public void setRegistersByOmId(List<Register> registersByOmId) {
        this.registersByOmId = registersByOmId;
    }

    @OneToMany(mappedBy = "oldmanByOmId")
    public List<Relation> getRelationsByOmId() {
        return relationsByOmId;
    }

    public void setRelationsByOmId(List<Relation> relationsByOmId) {
        this.relationsByOmId = relationsByOmId;
    }

    @OneToMany(mappedBy = "oldmanByOmId")
    public List<Settlement> getSettlementsByOmId() {
        return settlementsByOmId;
    }

    public void setSettlementsByOmId(List<Settlement> settlementsByOmId) {
        this.settlementsByOmId = settlementsByOmId;
    }

    @OneToMany(mappedBy = "oldmanByOmId")
    public List<Visit> getVisitsByOmId() {
        return visitsByOmId;
    }

    public void setVisitsByOmId(List<Visit> visitsByOmId) {
        this.visitsByOmId = visitsByOmId;
    }
}
