package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.common.utils.MatchUtil;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_8_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){
        
        //上半有进球，中场坚挺，下半有球
        if(shiJian == Integer.MIN_VALUE && totalScore > 0 && 
        		(Math.abs(chuPan - MatchUtil.getCurrentConcede(match)) <= 0.25))  { 
        	suggestionHandler.addSuggestion(Rule.RULE_8, match);  
        } 
        
	}
}
