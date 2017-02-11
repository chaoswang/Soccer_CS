package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.common.utils.MatchUtil;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_11_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){

        //初盘让球不走水法则
        if(MatchUtil.isNumeric(String.valueOf(Math.abs(chuPan))) && chuPan > 0 && totalScore > 0 
        		&& guestconcede == Math.abs(chuPan) && panKou < 1)  { 
        	suggestionHandler.addSuggestion(Rule.RULE_11, match);  
        } 
	}
}
