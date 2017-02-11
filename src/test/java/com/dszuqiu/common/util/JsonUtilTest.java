package com.dszuqiu.common.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.ServerMatchBean;
import com.dszuqiu.common.utils.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtilTest {

//	@Test
//	public void testCreateJsonString(){
//		Match match = new Match();
//		System.out.println(JsonUtil.createJsonString(match));
//	}
//	
//	@Test
//	public void testGetObject(){
//		String jsonStr = \"{\\"hostOdds\\":0.0,\\"guestOdds\\":0.0,\\"bigOdds\\":0.0,\\"smallOdds\\":0.0}\";
//		Match match = JsonUtil.getObject(jsonStr, Match.class);
//		System.out.println(match);
//	}
	
	@Test
	public void testCreateComplexJsonString(){
		List<ServerMatchBean> list = new ArrayList<ServerMatchBean>();
		ServerMatchBean bean = new ServerMatchBean();
		Match match = new Match();
		bean.setMatch(match);
		list.add(bean);
		
		System.out.println(JsonUtil.createJsonString(list));
		
	}
	
	@Test
	public void testGetObject(){
		Gson gson = new Gson();  
		String jsonStr = "[{\"match\":{\"hostOdds\":0.0,\"guestOdds\":0.0,\"bigOdds\":0.0,\"smallOdds\":0.0}}]";
		List<ServerMatchBean> list =  gson.fromJson(jsonStr,  
                new TypeToken<List<ServerMatchBean>>() {  
                }.getType());  
		System.out.println(list.get(0).getMatch());
	}
}
