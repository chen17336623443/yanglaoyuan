package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: yanglaoyuan
 * @description:
 * @author: 阳宇
 * @create: 2020-12-24 10:27
 */
@Entity
public class Otman {
    private Integer zid;
    private Health myhealth;
    private Taboocrowd mytaboocrowd;

    @Id
    @Column(name = "zid", nullable = false)
    public Integer getZid() {
        return zid;
    }

    public void setZid(Integer zid) {
        this.zid = zid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Otman otman = (Otman) o;
        return Objects.equals(zid, otman.zid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zid);
    }

    @ManyToOne
    @JoinColumn(name = "hid", referencedColumnName = "hid")
    public Health getMyhealth() {
        return myhealth;
    }

    public void setMyhealth(Health myhealth) {
        this.myhealth = myhealth;
    }

    @ManyToOne
    @JoinColumn(name = "tc_id", referencedColumnName = "tc_id")
    public Taboocrowd getMytaboocrowd() {
        return mytaboocrowd;
    }

    public void setMytaboocrowd(Taboocrowd mytaboocrowd) {
        this.mytaboocrowd = mytaboocrowd;
    }
}
