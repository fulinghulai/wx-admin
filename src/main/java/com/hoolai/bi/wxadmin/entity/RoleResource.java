package com.hoolai.bi.wxadmin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

public class RoleResource extends Model<RoleResource> {

    @TableId
    private int id;
    private int roleId;
    private int resourceId;

    public RoleResource() {
    }

    public RoleResource(int roleId, int resourceId) {
        this.roleId = roleId;
        this.resourceId = resourceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
