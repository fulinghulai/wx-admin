package com.hoolai.bi.wxadmin.serverImpl;

import com.hoolai.bi.wxadmin.entity.Dau;
import com.hoolai.bi.wxadmin.mapper.DauMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-02 10:50
 * Description:
 */
@Service
public class DauServerImpl {

	@Autowired
	private DauMapper dauMapper;

	public List<Dau> queryDauList() {
		 return dauMapper.queryAauList();
	}

}
