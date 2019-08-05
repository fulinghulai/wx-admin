package com.hoolai.bi.wxadmin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-29 10:24
 * Description:
 */
public class UserGame extends Model<UserGame> {

	@TableId
	private int id;
	private String openId;
	private int gameId;

	public UserGame() {
	}

	public UserGame(int id, String openId, int gameId) {
		this.id = id;
		this.openId = openId;
		this.gameId = gameId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
}
