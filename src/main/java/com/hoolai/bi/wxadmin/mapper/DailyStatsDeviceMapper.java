package com.hoolai.bi.wxadmin.mapper;

import com.hoolai.bi.wxadmin.entity.ShareDailyResultType;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-02 20:42
 * Description:
 */
public interface DailyStatsDeviceMapper {

	List<ShareDailyResultType> queryDailyStatsDeviceList(int gameid, int data);
}
