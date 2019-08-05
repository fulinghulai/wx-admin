package com.hoolai.bi.wxadmin.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hoolai.bi.wxadmin.TokenContext;
import com.hoolai.bi.wxadmin.entity.*;
import com.hoolai.bi.wxadmin.mapper.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class RoleController {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private ResourceMapper resourceMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Autowired
	private TokenContext tokenContext;

	@ApiOperation(value = "角色列表", notes = "角色列表")
	@PostMapping("/api/role/findAllRole")
	public Map<String, Object> findAllRole() {
		List<Role> roles = roleMapper.selectList(new QueryWrapper<>());
		return MapBuilder.newBuilder().put("success", true).put("msg", JSON.toJSONString(roles)).asMap();
	}

	@ApiOperation(value = "新增角色", notes = "新增角色")
	@PostMapping("/api/role/addRole")
	public Map<String, Object> addResource(String name) {
		int count = roleMapper.selectCount(new QueryWrapper<Role>().eq("name", name));
		if (count > 0) {
			return MapBuilder.newBuilder().put("success", true).put("msg", "角色名已存在").asMap();
		}
		Role role = new Role();
		role.setName(name);
		role.insert();
		return MapBuilder.newBuilder().put("success", true).put("msg", "添加成功").asMap();
	}

	@ApiOperation(value = "角色查用户", notes = "角色查用户")
	@PostMapping("/api/role/findUsersByRole")
	public Map<String, Object> findUsersByRole(int roleId) {
		List<UserRole> userRoles = userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("role_id", roleId));
		Set<User> users = Sets.newHashSet();
		for (UserRole userRole : userRoles) {
			User user = userMapper.selectById(userRole.getOpenId());
			users.add(user);
		}
		return MapBuilder.newBuilder().put("success", true).put("msg", JSON.toJSONString(users)).asMap();
	}

	@ApiOperation(value = "删除角色", notes = "删除角色")
	@PostMapping("/api/role/deleteRole")
	public Map<String, Object> deleteRole(int roleId) {
		Role role = roleMapper.selectById(roleId);
		if (role == null) {
			return MapBuilder.newBuilder().put("success", false).put("msg", "当前权限不存在").asMap();
		}

		if (roleId == SysRole.NORMAI.code || roleId == SysRole.SYSADMIN.code) {
			return MapBuilder.newBuilder().put("success", false).put("msg", "系统默认权限不可删除").asMap();
		}

		role.deleteById();
		List<UserRole> userRoles = userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("role_id", roleId));
		for (UserRole userRole : userRoles) {
			userRole.setRoleId(SysRole.NORMAI.code);
			userRole.updateById();
		}
		roleResourceMapper.delete(new QueryWrapper<RoleResource>().eq("role_id", roleId));

		return MapBuilder.newBuilder().put("success", true).put("msg", "删除成功").asMap();
	}

	@ApiOperation(value = "修改角色资源", notes = "修改角色资源")
	@PostMapping("/api/role/modifyRoleResource")
	public Map<String, Object> modifyRoleResource(int roleId, String resourceIds) {
		if (roleId == SysRole.NORMAI.code || roleId == SysRole.SYSADMIN.code) {
			return MapBuilder.newBuilder().put("success", false).put("msg", "系统默认权限不可修改").asMap();
		}
		roleResourceMapper.delete(new QueryWrapper<RoleResource>().eq("role_id", roleId));
		if (!resourceIds.isEmpty()) {
			List<String> ids = Arrays.asList(resourceIds.split(","));
			for (String resourceId : ids) {
				RoleResource roleResource = new RoleResource(roleId, Integer.parseInt(resourceId));
				roleResource.insert();
			}

			return MapBuilder.newBuilder().put("success", true).put("msg", "修改成功").asMap();
		}
		return MapBuilder.newBuilder().put("success", true).put("msg", "该角色将没任何权限").asMap();
	}

	@ApiOperation(value = "角色资源", notes = "角色资源")
	@PostMapping("/api/role/findRoleResource")
	public Map<String, Object> findRoleResource(int roleId) {
		List<RoleResource> roleResources = roleResourceMapper.selectList(new QueryWrapper<RoleResource>().eq("role_id", roleId));
		List<Resource> resources = Lists.newArrayListWithCapacity(roleResources.size());
		for (RoleResource roleResource : roleResources) {
			Resource resource = resourceMapper.selectById(roleResource.getResourceId());
			resources.add(resource);
		}

		List<Resource> allResources = resourceMapper.selectList(new QueryWrapper<>());
		for (Resource resource : allResources) {
			if (resources.contains(resource)) {
				resource.setContain(true);
			}
		}

		return MapBuilder.newBuilder().put("success", true).put("msg", JSON.toJSONString(allResources)).asMap();
	}

	@PostMapping("/api/role/roleManager")
	public Map<String, Object> roleManager() {
		return MapBuilder.newBuilder().put("success", true).asMap();
	}

}
