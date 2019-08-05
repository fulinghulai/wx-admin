package com.hoolai.bi.wxadmin.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hoolai.bi.wxadmin.TokenContext;
import com.hoolai.bi.wxadmin.entity.MapBuilder;
import com.hoolai.bi.wxadmin.entity.Resource;
import com.hoolai.bi.wxadmin.mapper.ResourceMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ResourceController {

	@Autowired
	private TokenContext tokenContext;
	@Autowired
	private ResourceMapper resourceMapper;

	private final String perfix = "/api";

	@ApiOperation(value = "资源列表", notes = "资源列表")
	@PostMapping(value = "/api/resource/findAllResource")
	public Map<String, Object> findAllResource() {
		List<Resource> resources = resourceMapper.selectList(new QueryWrapper<>());
		return MapBuilder.newBuilder().put("success", true).put("msg", JSON.toJSONString(resources)).asMap();
	}

	@PostMapping("/api/my")
	public Map my() {
		List<Resource> resources = resourceMapper.selectList(new QueryWrapper<>());
		return MapBuilder.newBuilder().put("success", true).put("msg", resources).asMap();
	}

	@ApiOperation(value = "新增资源", notes = "新增资源")
	@PostMapping("/api/resource/addResource")
	public Map<String, Object> addResource(String url, String name, int type) {
		String resourceUrl = url;
		int count = resourceMapper.selectCount(new QueryWrapper<Resource>().eq("url", resourceUrl).eq("name", name));
		if (count > 0) {
			return MapBuilder.newBuilder().put("success", false).put("msg", "资源已存在").asMap();
		}

		Resource resource = new Resource();
		resource.setName(name);
		resource.setUrl(resourceUrl);
		resource.setType(type);
		resourceMapper.insertResource(resource);
		return MapBuilder.newBuilder().put("success", true).put("msg", "添加成功").asMap();
	}

	@ApiOperation(value = "删除资源", notes = "删除资源")
	@PostMapping("/api/resource/deleteResource")
	public Map<String, Object> deleteResource(int id) {
		Resource resource = new Resource();
		resource.setId(id);
		resource.deleteById();
		return MapBuilder.newBuilder().put("success", true).put("msg", "删除成功").asMap();
	}

	@ApiOperation(value = "修改资源名字", notes = "修改资源")
	@PostMapping("/api/resource/modifyResource")
	public Map<String, Object> modifyResource(int id, String name, String url, int type) {
		Resource resource = resourceMapper.selectById(id);
		resource.setName(name);
		resource.setUrl(url);
		resource.setType(type);
		resourceMapper.updateByresource(resource);
		return MapBuilder.newBuilder().put("success", true).put("msg", "修改成功").asMap();
	}

	@PostMapping("/api/resource/resourceManager")
	public Map<String, Object> roleManager() {
		return MapBuilder.newBuilder().put("success", true).asMap();
	}

	@ApiOperation(value = "查询资源", notes = "查询资源")
	@PostMapping("/api/resource/findResourceById")
	public Map<String, Object> findResource(Integer id) {
		Resource resource = resourceMapper.selectResourceById(id);
		return MapBuilder.newBuilder().put("success", true).put("msg", resource).asMap();
	}
}
