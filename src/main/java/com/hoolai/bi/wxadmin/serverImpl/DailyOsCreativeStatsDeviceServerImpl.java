package com.hoolai.bi.wxadmin.serverImpl;

import com.hoolai.bi.wxadmin.entity.ShareDailyResultType;
import com.hoolai.bi.wxadmin.mapper.DailyOsClientStatsDeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-03 10:31
 * Description:
 */
@Service
public class DailyOsCreativeStatsDeviceServerImpl {

	@Autowired private DailyOsClientStatsDeviceMapper dailyOsClientStatsDeviceMapper;

	public List<ShareDailyResultType> queryDailyOsCreativeStatsDeviceList(int gameid, int data, int osType, int ccNum) {
		return dailyOsClientStatsDeviceMapper.queryDailyOsClientStatsDeviceList(gameid, data, osType, ccNum);
	}
}
