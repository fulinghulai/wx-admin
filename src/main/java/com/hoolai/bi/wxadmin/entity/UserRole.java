package com.hoolai.bi.wxadmin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

public class UserRole extends Model<UserRole> {

    @TableId
    private int id;
    private String openId;
    private int roleId;

    public UserRole() {
    }

    public UserRole(String openId, int roleId) {
        this.openId = openId;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
