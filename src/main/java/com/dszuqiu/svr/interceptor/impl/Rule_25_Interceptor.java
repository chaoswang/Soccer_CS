package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_25_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){
		 //主让球，已有进球,比如初盘是-2，现在比分是1：0，则可以追
        if(shiJian < 70 && chuPan < 0 && totalScore >=1 && hostconcede <= Math.abs(chuPan)-1 && bigSmall <= chuPanBigSmall)  { 
        	suggestionHandler.addSuggestion(Rule.RULE_25, match); 
        } 
	}
}
