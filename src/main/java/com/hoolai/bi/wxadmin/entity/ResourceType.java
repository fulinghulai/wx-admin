package com.hoolai.bi.wxadmin.entity;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-18 14:29
 * Description:
 */
public enum ResourceType {
	ajax(0),
	menu(1);

	private int type;

	ResourceType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}}
