package com.hoolai.bi.wxadmin.mapper;

import com.hoolai.bi.wxadmin.entity.ShareDailyResultType;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-02 20:15
 * Description:
 */
public interface DailyStatsMapper {

	List<ShareDailyResultType> queryDailyStatsList(int gameid, int data);
}
