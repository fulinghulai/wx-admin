package com.hoolai.bi.wxadmin.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hoolai.bi.wxadmin.entity.*;
import com.hoolai.bi.wxadmin.mapper.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoolai.bi.wxadmin.TokenContext;

@WebFilter(filterName = "pemVerifyFilter", urlPatterns = "/api/*")
public class PemVerifyFilter implements Filter {

	private Set<String> ignoreVerifyUrls = new HashSet<>();

	@Autowired
	private TokenContext tokenCtx;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Autowired
	private ResourceMapper resourceMapper;

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
		ignoreVerifyUrls.add("/api/user/login");
		ignoreVerifyUrls.add("/api/user/test");
		ignoreVerifyUrls.add("/api/user/findAllUser");
		ignoreVerifyUrls.add("/api/user/verifyToken");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String servletPath = req.getServletPath();
		System.out.println(servletPath);
		for (String url : ignoreVerifyUrls) {
			if (Pattern.matches(url, servletPath)) {
				chain.doFilter(request, response);
				return;
			}
		}

		String token = req.getHeader("token");
		if (StringUtils.isEmpty(token)) {
			Map<String, Object> result = MapBuilder.newBuilder().put("success", false).put("verifyToken",false).put("msg", "token不存在").asMap();
			mapper.writeValue(response.getOutputStream(), result);
			return;
		}

		String openid = (String) tokenCtx.find(token);
		if (openid == null) {
			Map<String, Object> result = MapBuilder.newBuilder().put("success", false).put("verifyToken",false).put("msg", "token已过期").asMap();
			mapper.writeValue(response.getOutputStream(), result);
			return;
		}

		Set<String> allowAccessUrls = new HashSet<>();
		allowAccessUrls.addAll(getAllowAccessUrls(openid));

		if (!allowAccessUrls.contains(servletPath)) {
			Map<String, Object> result = MapBuilder.newBuilder().put("success", false).put("msg", "权限不足").asMap();
			mapper.writeValue(response.getOutputStream(), result);
			return;
		}

		chain.doFilter(request, response);
	}

	private Set<String> getAllowAccessUrls(String openId) {
		Set<String> result = new HashSet<>();
		User user = userMapper.selectById(openId);
		List<UserRole> userRoles = userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("open_id",openId));
		for (UserRole userRole: userRoles) {
			List<RoleResource> roleResources = roleResourceMapper.selectList(new QueryWrapper<RoleResource>().eq("role_id",userRole.getRoleId()));

			for (RoleResource roleResource : roleResources) {
				Resource resource = resourceMapper.selectById(roleResource.getResourceId());
				result.add(resource.getUrl());
			}
		}
		return result;
	}

}
