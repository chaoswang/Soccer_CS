package com.dszuqiu.common.entity;

public class InvestSuggestion {
	String time;
	int ruleType;
	
	public InvestSuggestion(String time, int ruleType) {
		this.time = time;
		this.ruleType = ruleType;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getRuleType() {
		return ruleType;
	}
	public void setRuleType(int ruleType) {
		this.ruleType = ruleType;
	}
	
	
}
