package com.hoolai.bi.wxadmin.serverImpl;

import com.hoolai.bi.wxadmin.entity.ShareDailyResultType;
import com.hoolai.bi.wxadmin.mapper.DailyStatsDeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-02 20:42
 * Description:
 */
@Service
public class DailyStatsDeviceServerImpl {

	@Autowired
	private DailyStatsDeviceMapper dailyStatsDeviceMapper;

	public List<ShareDailyResultType> queryDailyStatsDeviceList(int gameid, int data) {
		return dailyStatsDeviceMapper.queryDailyStatsDeviceList(gameid, data);
	}
}
