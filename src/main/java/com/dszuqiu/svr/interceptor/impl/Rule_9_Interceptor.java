package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.common.utils.MatchUtil;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_9_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){
        
        //°ëÇò·¨Ôò
        if(shiJian == Integer.MIN_VALUE && Math.abs(chuPan) == 0.5 &&
        		Math.abs(MatchUtil.getCurrentConcede(match)) >= 0.5)  { 
        	suggestionHandler.addSuggestion(Rule.RULE_9, match);  
        } 
        
	}
}
