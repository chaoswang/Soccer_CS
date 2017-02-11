package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_1_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){
        //主让球，客队先进球0:1 
        if(shiJian < 50 && (chuPan < 0 && zhuScore == 0 && keScore == 1) && bigSmall < 3 ) { 
        	suggestionHandler.addSuggestion(Rule.RULE_1, match); 
        } 
	}
}
