package com.hoolai.bi.wxadmin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

public class Resource extends Model<Resource> {

    @TableId
    private int id;
    private String name;
    private String url;
    private int type;
    @TableField(value = "false")
    private boolean isContain;

    public Resource() {
        this.isContain = false;
    }

    @Override
    public int hashCode() {
        return url.hashCode()+id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Resource){
            Resource resource = (Resource)obj;
            if (resource.id == this.id&&resource.url.equals(this.url))
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isContain() {
        return isContain;
    }

    public void setContain(boolean contain) {
        isContain = contain;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
