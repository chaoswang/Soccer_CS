package com.dszuqiu.svr.interceptor.impl; 

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.svr.handler.SuggestionHandler;

public class Rule_26_Interceptor extends AbsInterceptor{ 
	private static final SuggestionHandler suggestionHandler = SuggestionHandler.getInstance();
	
	public void makeSuggestion(Match match){
		//�ļ٣�ͷ10�����޽���׷��
        if(shiJian > 10 && shiJian < 15 && match.getType().indexOf("��") != -1) { 
        	suggestionHandler.addSuggestion(Rule.RULE_26, match); 
        } 
	}
}
