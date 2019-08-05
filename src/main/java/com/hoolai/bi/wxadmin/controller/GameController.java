package com.hoolai.bi.wxadmin.controller;

import com.google.common.collect.Lists;
import com.hoolai.bi.wxadmin.constant.CreativeClient;
import com.hoolai.bi.wxadmin.constant.DeviceConstants;
import com.hoolai.bi.wxadmin.constant.OS;
import com.hoolai.bi.wxadmin.entity.*;
import com.hoolai.bi.wxadmin.serverImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-21 16:16
 * Description:
 */
@RestController
public class GameController {

	@Autowired private DailyStatsServerImpl dailyStatsServer;
	@Autowired private DailyStatsDeviceServerImpl dailyStatsDeviceServer;
	@Autowired private DailyOsClientStatsServerImpl dailyOsClientStatsServer;
	@Autowired private DailyOsClientStatsDeviceServerImpl dailyOsClientStatsDeviceServer;
	@Autowired private DailyOsCreativeStatsServerImpl dailyOsCreativeStatsServer;
	@Autowired private DailyOsCreativeStatsDeviceServerImpl dailyOsCreativeStatsDeviceServer;

	@PostMapping("/api/daily")
	@TargetDataSource(dataSource = "threeDataSource")
	public Map<String, Object> dailyCreativeStats(int gameid, int deviceType, int osType, int data, int ccType, int ccNum, int echartsType, int hxType) {
		List<ShareDailyResultType> shareDailyResultTypes = Lists.newArrayList();
		List<Integer> dauNumOrInstallNumList = Lists.newArrayList();
		//用户
		if (deviceType == DeviceConstants.USER) {
			//All 无渠道/分服
			if (osType == OS.OS) {
				shareDailyResultTypes = dailyStatsServer.queryDailyStatsList(gameid, data);
				dauNumOrInstallNumList = dauOrInstall(hxType, shareDailyResultTypes);
			} else {
				//渠道
				if (ccType == CreativeClient.CREATIVE) {
					shareDailyResultTypes = dailyOsCreativeStatsServer.queryDailyOsCreativeStatsList(gameid, data, osType, ccNum);
					dauNumOrInstallNumList = dauOrInstall(hxType, shareDailyResultTypes);
				}
				//分服
				if (ccType == CreativeClient.CLIENT) {
					shareDailyResultTypes = dailyOsClientStatsServer.queryDailyOsClientStatsList(gameid, data, osType, ccNum);
					dauNumOrInstallNumList = dauOrInstall(hxType, shareDailyResultTypes);
				}
			}
		}
		//设备
		if (deviceType == DeviceConstants.DEVICE) {
			//All 无渠道/分服
			if (osType == OS.OS) {
				shareDailyResultTypes = dailyStatsDeviceServer.queryDailyStatsDeviceList(gameid, data);
				dauNumOrInstallNumList = dauOrInstall(hxType, shareDailyResultTypes);
			} else {
				//渠道
				if (ccType == CreativeClient.CREATIVE) {
					shareDailyResultTypes = dailyOsCreativeStatsDeviceServer.queryDailyOsCreativeStatsDeviceList(gameid, data, osType, ccNum);
					dauNumOrInstallNumList = dauOrInstall(hxType, shareDailyResultTypes);
				}
				//分服
				if (ccType == CreativeClient.CLIENT) {
					shareDailyResultTypes = dailyOsClientStatsDeviceServer.queryDailyOsClientStatsDeviceList(gameid, data, osType, ccNum);
					dauNumOrInstallNumList = dauOrInstall(hxType, shareDailyResultTypes);
				}
			}
		}

		return MapBuilder.newBuilder().put("success", true)
						.put("shareDailyResultTypes", shareDailyResultTypes)
						.put("dauNumOrInstallNumList", dauNumOrInstallNumList)
						.asMap();
	}

	private List<Integer> dauOrInstall(int hxType, List<ShareDailyResultType> shareDailyResultTypes) {
		List<Integer> dauNumOrInstallNumList;
		if (hxType == 0) {
			dauNumOrInstallNumList = shareDailyResultTypes.stream().map(info -> info.getDauNum()).collect(Collectors.toList());
			dauNumOrInstallNumList.subList(0, 6 > dauNumOrInstallNumList.size() ? dauNumOrInstallNumList.size() : 6);
		} else {
			dauNumOrInstallNumList = shareDailyResultTypes.stream().map(info -> info.getInstallNum()).collect(Collectors.toList());
			dauNumOrInstallNumList.subList(0, 6 > dauNumOrInstallNumList.size() ? dauNumOrInstallNumList.size() : 6);
		}
		return dauNumOrInstallNumList;
	}
}
