package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_24_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){
		//���ð���60�����޽��򣬼�ʱ��С�򽵵�1
        if(shiJian < 70 && (chuPan <= -0.5 && totalScore == 0) && bigSmall <= 0.75 ) { 
        	suggestionHandler.addSuggestion(Rule.RULE_24, match); 
        } 
	}
}
        
