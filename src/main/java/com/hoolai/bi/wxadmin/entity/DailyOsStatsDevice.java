package com.hoolai.bi.wxadmin.entity;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-21 15:42
 * Description:
 */
public class DailyOsStatsDevice {

	private int gameid;
	/*日期*/
	private String ds;
	/*操作系统*/
	private String os;
	/*总用户数*/
	private int totalNum;
	/*活跃数*/
	private int dauNum;
	/*注册数*/
	private int installNum;
	/*付费人数*/
	private int payCount;
	/*付费金额*/
	private int payAmount;
	/*付费次数*/
	private int payTimes;
	/*安装付费人数*/
	private int payInstallCount;
	/*安装付费金额*/
	private int payInstallAmount;
	/*安装付费次数*/
	private int payInstallTimes;

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

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getDauNum() {
		return dauNum;
	}

	public void setDauNum(int dauNum) {
		this.dauNum = dauNum;
	}

	public int getInstallNum() {
		return installNum;
	}

	public void setInstallNum(int installNum) {
		this.installNum = installNum;
	}

	public int getPayCount() {
		return payCount;
	}

	public void setPayCount(int payCount) {
		this.payCount = payCount;
	}

	public int getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}

	public int getPayTimes() {
		return payTimes;
	}

	public void setPayTimes(int payTimes) {
		this.payTimes = payTimes;
	}

	public int getPayInstallCount() {
		return payInstallCount;
	}

	public void setPayInstallCount(int payInstallCount) {
		this.payInstallCount = payInstallCount;
	}

	public int getPayInstallAmount() {
		return payInstallAmount;
	}

	public void setPayInstallAmount(int payInstallAmount) {
		this.payInstallAmount = payInstallAmount;
	}

	public int getPayInstallTimes() {
		return payInstallTimes;
	}

	public void setPayInstallTimes(int payInstallTimes) {
		this.payInstallTimes = payInstallTimes;
	}
}
