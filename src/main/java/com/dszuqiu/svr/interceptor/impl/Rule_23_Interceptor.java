package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_23_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){
		//�����򣬿Ͷ��Ƚ��򣬼�ʱ��С�򽵵�2
        if(shiJian < 70 && (chuPan < 0 && zhuScore == 0 && keScore == 1) && bigSmall <= 2.5 ) { 
        	suggestionHandler.addSuggestion(Rule.RULE_3, match); 
        } 
	}
}
        
