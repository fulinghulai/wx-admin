package com.hoolai.bi.wxadmin.mapper;

import com.hoolai.bi.wxadmin.entity.ShareDailyResultType;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-03 10:22
 * Description:
 */
public interface DailyOsCreativeStatsMapper {

	List<ShareDailyResultType> queryDailyOsCreativeStatsList(int gameid, int data, int osType, int ccNum);
}
