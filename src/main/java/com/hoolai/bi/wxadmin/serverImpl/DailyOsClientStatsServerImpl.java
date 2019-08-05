package com.hoolai.bi.wxadmin.serverImpl;

import com.hoolai.bi.wxadmin.entity.ShareDailyResultType;
import com.hoolai.bi.wxadmin.mapper.DailyOsClientStatsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-02 20:52
 * Description:
 */
@Service
public class DailyOsClientStatsServerImpl {

	@Autowired
	private DailyOsClientStatsMapper dailyOsClientStatsMapper;

	public List<ShareDailyResultType> queryDailyOsClientStatsList(int gameid, int data, int osType, int ccNum) {
		return dailyOsClientStatsMapper.queryDailyOsClientStatsList(gameid, data, osType, ccNum);
	}
}
