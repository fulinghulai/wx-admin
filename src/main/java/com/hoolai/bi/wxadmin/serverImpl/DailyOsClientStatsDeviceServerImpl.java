package com.hoolai.bi.wxadmin.serverImpl;

import com.hoolai.bi.wxadmin.entity.ShareDailyResultType;
import com.hoolai.bi.wxadmin.mapper.DailyOsClientStatsDeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-03 10:09
 * Description:
 */
@Service
public class DailyOsClientStatsDeviceServerImpl {

	@Autowired private DailyOsClientStatsDeviceMapper dailyOsClientStatsDeviceMapper;

	public List<ShareDailyResultType> queryDailyOsClientStatsDeviceList(int gameid, int data, int osType, int ccNum) {
		return dailyOsClientStatsDeviceMapper.queryDailyOsClientStatsDeviceList(gameid, data, osType, ccNum);
	}
}
