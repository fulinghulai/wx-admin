package com.hoolai.bi.wxadmin.serverImpl;

import com.hoolai.bi.wxadmin.entity.ShareDailyResultType;
import com.hoolai.bi.wxadmin.mapper.DailyStatsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-02 20:15
 * Description:
 */
@Service
public class DailyStatsServerImpl {

	@Autowired
	private DailyStatsMapper dailyStatsMapper;

	public List<ShareDailyResultType> queryDailyStatsList(int gameid, int data) {
		return dailyStatsMapper.queryDailyStatsList(gameid,data);
	}
}
