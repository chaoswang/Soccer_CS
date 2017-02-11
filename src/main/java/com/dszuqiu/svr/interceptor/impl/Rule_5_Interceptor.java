package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_5_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){
		//已有进球,降到初盘
        if(shiJian < 60 && chuPan != 0 && totalScore >=1 && bigSmall < chuPanBigSmall)  { 
        	suggestionHandler.addSuggestion(Rule.RULE_5, match); 
        } 
	}
}
