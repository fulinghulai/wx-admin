package com.hoolai.bi.wxadmin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-29 10:17
 * Description: 游戏权限地址
 */
public class Games extends Model<Games> {

	@TableId
	private int id;
	private String name;
	private String type;
	private String state;
	private String url;
	@TableField(exist = false)
	private boolean contain;

	public Games() {
	}

	public Games(int id, String name, String type, String state, String url, boolean contain) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.state = state;
		this.url = url;
		this.contain = true;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean getContain() {
		return contain;
	}

	public void setContain(boolean contain) {
		this.contain = contain;
	}
}
