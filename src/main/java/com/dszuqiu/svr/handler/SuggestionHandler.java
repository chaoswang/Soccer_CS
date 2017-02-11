package com.dszuqiu.svr.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.dszuqiu.common.entity.InvestSuggestion;
import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.common.utils.DebugLogger;

public class SuggestionHandler {
	private static Map<String, InvestSuggestion> matchSuggestion = new ConcurrentHashMap<String, InvestSuggestion>();
	
	private static final Logger logger = DebugLogger.getLogger(SuggestionHandler.class);
	private static final SuggestionHandler singleton = new SuggestionHandler();
	
	private SuggestionHandler(){
	}
	
	public static SuggestionHandler getInstance(){ 
		return singleton;
	}
	
	public void addSuggestion(Rule rule, Match match){
		logger.info("addSuggestion:" + match + rule);
		matchSuggestion.put(match.getTrId(), new InvestSuggestion(match.getTime(), rule.getIndex()));
	}
	
	public InvestSuggestion getSuggestionByTrId(String trId){
		return matchSuggestion.get(trId);
	}
}
