package com.hoolai.bi.wxadmin.mapper;

import com.hoolai.bi.wxadmin.entity.ShareDailyResultType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-03 10:31
 * Description:
 */
@Service
public interface DailyOsCreativeStatsDeviceMapper {

	List<ShareDailyResultType> queryDailyOsCreativeStatsDeviceList(int gameid, int data, int osType, int ccNum);
}
