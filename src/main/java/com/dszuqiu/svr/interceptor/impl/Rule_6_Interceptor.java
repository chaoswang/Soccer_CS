package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_6_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){
		//65·ÖÖÓ·¨Ôò
        if((shiJian >= 64 && shiJian <= 65) && totalScore > 0 && panKou >= 1 &&  match.getBigOdds() <= 2 ) { 
        	suggestionHandler.addSuggestion(Rule.RULE_6, match); 
        } 
	}
}
