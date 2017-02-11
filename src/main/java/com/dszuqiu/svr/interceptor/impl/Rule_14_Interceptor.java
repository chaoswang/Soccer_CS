package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_14_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){
		if(shiJian < 10 && totalScore == 1 && (chuPanBigSmall >= 3.5 && chuPanBigSmall <= 4)){ 
        	suggestionHandler.addSuggestion(Rule.RULE_14, match); 
        } 
	}
}
        
