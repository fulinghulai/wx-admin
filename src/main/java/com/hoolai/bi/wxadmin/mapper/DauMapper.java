package com.hoolai.bi.wxadmin.mapper;

import com.hoolai.bi.wxadmin.entity.Dau;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-02 10:22
 * Description:
 */
public interface DauMapper {

	@Select("select * from dau")
	List<Dau> queryAauList();
}
