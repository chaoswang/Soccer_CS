package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_19_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){
		if(totalScore == 1 && (chuPanBigSmall >= 3.25 && chuPanBigSmall <= 3.5) && (bigSmall <= 3)){ 
        	suggestionHandler.addSuggestion(Rule.RULE_19, match); 
        } 
	}
}
