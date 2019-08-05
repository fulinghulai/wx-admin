package com.hoolai.bi.wxadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hoolai.bi.wxadmin.entity.Resource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ResourceMapper extends BaseMapper<Resource> {

	@Select("select * from resource where id = #{id}")
	Resource selectResourceById(@Param("id") Integer id);

	void updateByresource(Resource resource);

	void insertResource(Resource resource);

	List<Resource> selectByType(Integer menu,Integer roleId);
}
