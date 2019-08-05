package com.hoolai.bi.wxadmin.mapper;

import com.hoolai.bi.wxadmin.entity.ShareDailyResultType;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-02 20:52
 * Description:
 */
public interface DailyOsClientStatsMapper {

	/**
	 *
	 * @param gameid
	 * @param data
	 * @param osType
	 * @param ccNum
	 * @return
	 */
	List<ShareDailyResultType> queryDailyOsClientStatsList(int gameid, int data, int osType, int ccNum);
}
