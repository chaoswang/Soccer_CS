package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_2_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){
		 //客让球，主队先进球1:0 
		if(shiJian < 50 && (chuPan > 0 && zhuScore == 1 && keScore == 0) && bigSmall < 3)  { 
        	suggestionHandler.addSuggestion(Rule.RULE_2, match); 
        } 
	}
}
        
