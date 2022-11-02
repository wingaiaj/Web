package com.zx.qqzone.pojo;

import java.sql.Date;

/**
 * @ClassName DetailUser
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/26 19:57
 * @Version 1.0
 */
public class DetailUser {
    private Integer id;
    private String realName;
    private String tel;
    private String email;
    private Date birth;
    private String star;

    public DetailUser() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
