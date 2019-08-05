package com.hoolai.bi.wxadmin.entity;

public enum SysRole {
    NORMAI(1),
    SYSADMIN(2);

    public final int code;

    private SysRole(int code){
        this.code = code;
    }

}
