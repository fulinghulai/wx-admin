package com.hoolai.bi.wxadmin.entity;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class User extends Model<User> {

    @TableId(value = "open_id")
    private String openId;
    private String name;
    @TableField(exist = false)
    private Role role;
    private String avatarUrl;

    public User() {
        this.role = new Role();
    }

    public User initUserInfo(JSONObject info){
        this.openId = info.getString("openId");
        this.name = info.getString("nickName");
        this.avatarUrl = info.getString("avatarUrl");
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public int hashCode() {
        return openId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User&&((User)obj).openId == this.openId){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "openId='" + openId + '\'' +
                ", name='" + name + '\'' +
                ", roles=" + role +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}

