package com.yanglaoyuan.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Yanzhen {
    private String token;
    private String ename;
    private Integer yzid;

    @Basic
    @Column(name = "token", nullable = true, length = 400)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "ename", nullable = true, length = 32)
    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    @Id
    @Column(name = "yzid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getYzid() {
        return yzid;
    }

    public void setYzid(Integer yzid) {
        this.yzid = yzid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Yanzhen yanzhen = (Yanzhen) o;
        return Objects.equals(token, yanzhen.token) &&
                Objects.equals(ename, yanzhen.ename) &&
                Objects.equals(yzid, yanzhen.yzid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, ename, yzid);
    }
}
