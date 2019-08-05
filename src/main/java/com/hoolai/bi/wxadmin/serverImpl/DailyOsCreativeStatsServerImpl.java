package com.hoolai.bi.wxadmin.serverImpl;

import com.hoolai.bi.wxadmin.entity.ShareDailyResultType;
import com.hoolai.bi.wxadmin.mapper.DailyOsCreativeStatsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-03 10:21
 * Description:
 */
@Service
public class DailyOsCreativeStatsServerImpl {

	@Autowired private DailyOsCreativeStatsMapper dailyOsCreativeStatsMapper;

	public List<ShareDailyResultType> queryDailyOsCreativeStatsList(int gameid, int data, int osType, int ccNum) {
		return dailyOsCreativeStatsMapper.queryDailyOsCreativeStatsList(gameid, data, osType, ccNum);
	}
}
