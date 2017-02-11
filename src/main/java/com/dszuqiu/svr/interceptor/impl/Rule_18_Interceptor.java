package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_18_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){
		if(totalScore == 0 && (chuPanBigSmall >= 2.25 && chuPanBigSmall <= 2.5) && (bigSmall <= 1.75)){ 
        	suggestionHandler.addSuggestion(Rule.RULE_18, match); 
        } 
	}
}
