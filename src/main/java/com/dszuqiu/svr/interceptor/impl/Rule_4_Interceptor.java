package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_4_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){
		//���������н��� ,���������+1.5�����ڱȷ���0��1�������׷
        if(shiJian < 60 && chuPan > 0 && totalScore >=1 && guestconcede <= chuPan-0.5 && bigSmall < chuPanBigSmall) { 
        	suggestionHandler.addSuggestion(Rule.RULE_4, match); 
        } 
	}
}
        
