package com.hoolai.bi.wxadmin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.google.common.collect.Lists;

import java.util.List;

public class Role extends Model<Role> {

    @TableId
    private int id;
    private String name;
    @TableField(exist = false)
    private List<Resource> permissions;

    public Role(){
        this.permissions = Lists.newArrayList();
    }

    public void updatePermission(List<Resource> permissions){
        this.permissions = permissions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public List<Resource> getResources() {
        return permissions;
    }

    public void setResources(List<Resource> permissions) {
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
