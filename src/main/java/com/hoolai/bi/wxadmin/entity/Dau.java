package com.hoolai.bi.wxadmin.entity;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-02 10:18
 * Description:
 */
public class Dau {

	private String windowStart;
	private String windowEnd;

	public Dau() {
	}

	public Dau(String windowStart, String windowEnd) {
		this.windowStart = windowStart;
		this.windowEnd = windowEnd;
	}

	public String getWindowStart() {
		return windowStart;
	}

	public void setWindowStart(String windowStart) {
		this.windowStart = windowStart;
	}

	public String getWindowEnd() {
		return windowEnd;
	}

	public void setWindowEnd(String windowEnd) {
		this.windowEnd = windowEnd;
	}
}
