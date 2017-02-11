package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_25_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){
		 //���������н���,���������-2�����ڱȷ���1��0�������׷
        if(shiJian < 70 && chuPan < 0 && totalScore >=1 && hostconcede <= Math.abs(chuPan)-1 && bigSmall <= chuPanBigSmall)  { 
        	suggestionHandler.addSuggestion(Rule.RULE_25, match); 
        } 
	}
}
