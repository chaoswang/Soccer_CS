package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_7_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){
        //75·ÖÖÓ·¨Ôò
        if((shiJian >= 74 && shiJian <= 75) && totalScore > 0 && 
        		(panKou >= 0.75 || (panKou >= 0.5 && match.getBigOdds() >= 2))) { 
        	suggestionHandler.addSuggestion(Rule.RULE_7, match); 
        } 
	}
}
