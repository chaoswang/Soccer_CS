package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_12_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){
		 if(shiJian < 30 && totalScore == 3 && panKou < 1) {  
			suggestionHandler.addSuggestion(Rule.RULE_12, match);  
        } 
	}
}
        
