package com.hoolai.bi.wxadmin.entity;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-21 16:24
 * Description:
 */
public class Retention {

	private int gameid;
	/*日期*/
	private String ds;
	/*第几日留存*/
	private int dr;
	/*留存用户数*/
	private int retention;

	public int getGameid() {
		return gameid;
	}

	public void setGameid(int gameid) {
		this.gameid = gameid;
	}

	public String getDs() {
		return ds;
	}

	public void setDs(String ds) {
		this.ds = ds;
	}

	public int getDr() {
		return dr;
	}

	public void setDr(int dr) {
		this.dr = dr;
	}

	public int getRetention() {
		return retention;
	}

	public void setRetention(int retention) {
		this.retention = retention;
	}
}
