package com.dszuqiu.common.utils;

import io.netty.channel.ChannelHandlerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {
	private static final Logger logger = DebugLogger.getLogger(JsonUtil.class);
	
	public static String createJsonString(Object value) {
		Gson gson = new Gson();
		String str = gson.toJson(value);
		return str;
	}

	// 使用泛型获取javaBean
	public static <T> T getObject(String jsonString, Class<T> cls) {
		T t = null;
		try {
			Gson gson = new Gson();
			t = gson.fromJson(jsonString, cls);
		} catch (Exception e) {
			logger.warn("getObject Exception ", e);
		}
		return t;
	}

	public static <T> List<T> getObjects(String jsonString, Class<T> cls) {
		List<T> list = new ArrayList<T>();
		try {
			Gson gson = new Gson();
			list = gson.fromJson(jsonString, new TypeToken<List<T>>() {}.getType());
		} catch (Exception e) {
			logger.warn("getObjects Exception ", e);
		}
		return list;
	}

	public static List<Map<String, Object>> getMapList(String jsonString) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Gson gson = new Gson();
			list = gson.fromJson(jsonString,new TypeToken<List<Map<String, Object>>>() {}.getType());
		} catch (Exception e) {
			logger.warn("getMapList Exception ", e);
		}
		logger.info("getMapList:" + list);
		return list;
	}
}
