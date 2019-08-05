package com.hoolai.bi.wxadmin.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hoolai.bi.wxadmin.entity.*;
import com.hoolai.bi.wxadmin.entity.Games;
import com.hoolai.bi.wxadmin.mapper.*;
import com.hoolai.bi.wxadmin.serverImpl.DauServerImpl;
import com.hoolai.bi.wxadmin.utils.AesCbcUtil;
import com.hoolai.bi.wxadmin.utils.WxUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hoolai.bi.wxadmin.TokenContext;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

	@Autowired
	public TokenContext tokenContext;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private ResourceMapper resourceMapper;

	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Autowired
	private UserGameMapper userGameMapper;

	@Autowired
	private GamesMapper gamesMapper;

	@Autowired
	private DauServerImpl dauServer;

	@ApiOperation(value = "登录", notes = "登录")
	@PostMapping("/api/user/login")
	public Map<String, Object> login(String encryptedData, String iv, String code) {
		if (StringUtils.isEmpty(code)) {
			throw new RuntimeException("openid is empty");
		}

		JSONObject wxInfo = WxUtil.produceWxInfo(code);
		String openId = wxInfo.getString("openid");
		try {
			String result = AesCbcUtil.decrypt(encryptedData, wxInfo.getString("session_key"), iv, "UTF-8");
			System.out.println(result);
			JSONObject jsonObject = JSON.parseObject(result);
			User user = userMapper.selectById(openId);
			if (user == null) {
				user = new User();
				user.initUserInfo(jsonObject);
				user.insert();
				userRoleMapper.insert(new UserRole(user.getOpenId(), SysRole.NORMAI.code));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		String token = UUID.randomUUID().toString();
		tokenContext.add(token, openId);

		return MapBuilder.newBuilder().put("success", true).put("token", token).asMap();
	}

	@ApiOperation(value = "查找所有用户", notes = "查找所有用户")
	@PostMapping("/api/user/findAllUser")
	public Map<String, Object> findAllUser() {
		List<User> users = userMapper.selectList(new QueryWrapper<>());
		return MapBuilder.newBuilder().put("success", true).put("msg", JSON.toJSONString(users)).asMap();
	}

	@ApiOperation(value = "查看用户详情", notes = "查看用户详情")
	@PostMapping("/api/user/findUserDetail")
	public Map<String, Object> findUserDetail(String openId) {
		User user = userMapper.selectById(openId);
		if (user == null) {
			return MapBuilder.newBuilder().put("success", false).put("msg", "用户不存在").asMap();
		}

		UserRole userRole = userRoleMapper.selectOne(new QueryWrapper<UserRole>().eq("open_id", openId));
		if (userRole != null) {
			int roleId = userRole.getRoleId();
			Role role = roleMapper.selectById(roleId);
			List<RoleResource> roleResources = roleResourceMapper.selectList(new QueryWrapper<RoleResource>().eq("role_id", roleId));
			List<Resource> resources = Lists.newArrayListWithCapacity(roleResources.size());
			for (RoleResource roleResource : roleResources) {
				Resource resource = resourceMapper.selectById(roleResource.getResourceId());
				resources.add(resource);
			}

			role.setResources(resources);
			user.setRole(role);
		}
		return MapBuilder.newBuilder().put("success", true).put("msg", JSON.toJSONString(user)).asMap();
	}

	@ApiOperation(value = "修改用户游戏列表", notes = "修改用户游戏列表")
	@PostMapping("/api/user/modifyUser")
	public Map<String, Object> modifyUserGameInfo() {
		List<User> users = userMapper.selectList(new QueryWrapper<>());
		return MapBuilder.newBuilder().put("success", true).put("msg", JSON.toJSONString(users)).asMap();
	}

	@ApiOperation(value = "授予用户角色", notes = "授予用户角色")
	@PostMapping("/api/user/GrantUserRole")
	public Map<String, Object> grantUserRole(String openId, int roleId) {
		UserRole userRole = userRoleMapper.selectOne(new QueryWrapper<UserRole>().eq("open_id", openId));
		if (userRole != null) {
			userRole.setRoleId(roleId);
			userRole.updateById();
		} else {
			userRole = new UserRole(openId, roleId);
			userRole.insert();
		}

		return MapBuilder.newBuilder().put("success", true).put("msgs", JSON.toJSONString(userRole)).asMap();
	}

	@PostMapping("/api/user/userManager")
	public Map<String, Object> userManager() {
		return MapBuilder.newBuilder().put("success", true).asMap();
	}

	@PostMapping("/api/resourceType")
	public Map reourceType(HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("token");
		String openId = (String) tokenContext.find(token);
		UserRole userRole = userRoleMapper.selectOne(new QueryWrapper<UserRole>().lambda().eq(UserRole::getOpenId, openId));
		List<Resource> resources = resourceMapper.selectByType(ResourceType.menu.getType(), userRole.getRoleId());
		//底部菜单选项
		//		List<Resource> resourcesType = resourceMapper.selectList(new QueryWrapper<Resource>().lambda().eq(Resource::getType, 1));
		return MapBuilder.newBuilder().put("success", true).put("resourceType", resources).asMap();
	}

	@PostMapping("/api/gamesUrl")
	public Map gamesUrl(HttpServletRequest httpServletRequest, String openId) {
		if (StringUtils.isEmpty(openId)) {
			String token = httpServletRequest.getHeader("token");
			openId = (String) tokenContext.find(token);
		}

		List<UserGame> userGames = userGameMapper.selectList(new QueryWrapper<UserGame>().lambda().eq(UserGame::getOpenId, openId));
		List<Games> gamesList = Lists.newArrayList();
		for (UserGame userGame : userGames) {
			Games games = gamesMapper.selectOne(new QueryWrapper<Games>().lambda().eq(Games::getId, userGame.getGameId()));
			games.setContain(true);
			gamesList.add(games);
		}
		List<Games> games = gamesMapper.selectList(null);
		for (Games game : games) {
			game.setContain(false);
		}
		return MapBuilder.newBuilder().put("success", true).put("gamesList", gamesList).put("games", games).asMap();
	}

	@PostMapping("/api/gamesIdsByOpenId")
	public Map gamesIdsByOpenId(String openId, int[] gameIds) {
		//删除openId games
		userGameMapper.delete(new QueryWrapper<UserGame>().lambda().eq(UserGame::getOpenId, openId));

		List<Integer> gameIdList = Arrays.stream(gameIds).boxed().collect(Collectors.toList());
		gameIdList.forEach(info -> {
			UserGame userGame = new UserGame();
			userGame.setOpenId(openId);
			userGame.setGameId(info);
			userGameMapper.insert(userGame);
		});
		return MapBuilder.newBuilder().put("success", true).asMap();
	}

	@GetMapping("/api/druidText")
	@TargetDataSource(dataSource = "twoDataSource")
	public Map druidText() {
		List<Dau> daus = dauServer.queryDauList();
		System.out.println(daus);
		return MapBuilder.newBuilder().put("success", true).asMap();
	}

}
