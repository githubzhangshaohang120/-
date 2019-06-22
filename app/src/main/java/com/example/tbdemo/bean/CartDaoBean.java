package com.example.tbdemo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CartDaoBean extends UserBean {
    @Id(autoincrement = true)
    private Long id;
    private  String json;
    @Generated(hash = 718041936)
    public CartDaoBean(Long id, String json) {
        this.id = id;
        this.json = json;
    }
    @Generated(hash = 1020066015)
    public CartDaoBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getJson() {
        return this.json;
    }
    public void setJson(String json) {
        this.json = json;
    }
}
