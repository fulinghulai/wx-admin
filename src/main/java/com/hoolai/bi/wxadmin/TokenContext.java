package com.hoolai.bi.wxadmin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class TokenContext {

	private Map<String, Object> tokens = new ConcurrentHashMap<>();

	public void add(String token, Object val) {
		tokens.put(token, val);
	}

	public Object find(String header) {
		return tokens.get(header);
	}
}
