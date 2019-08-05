package com.hoolai.bi.wxadmin.entity;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder {

	private Map<String, Object> params;

	private MapBuilder() {
		params = new HashMap<>();
	}

	public MapBuilder put(String key, Object value) {
		params.put(key, value);
		return this;
	}

	public MapBuilder putAll(Map<String, Object> params) {
		this.params.putAll(params);
		return this;
	}

	public static MapBuilder newBuilder() {
		MapBuilder builder = new MapBuilder();
		builder.put("verifyToken",true);
		return builder;
	}

	public Map<String, Object> asMap() {
		return params;
	}
}
