package com.hoolai.bi.wxadmin.entity;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-21 16:34
 * Description:
 */
public class RetentionOsCreativeDevice {

	private int gameid;
	/*日期*/
	private String ds;
	/*操作系统：IOS,ANDROID*/
	private String os;
	/*渠道*/
	private String creative;
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

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getCreative() {
		return creative;
	}

	public void setCreative(String creative) {
		this.creative = creative;
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
