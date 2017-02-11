package com.dszuqiu.common.entity;

public class Order {
	//规则类型
	Rule rule; 
	//下注的比赛
	Match match;
	//投注金额
	float cost;

	//赔率
	float odds;
	
	public Order(Rule rule, Match match, float cost, float odds) {
		super();
		this.rule = rule;
		this.match = match;
		this.cost = cost;
		this.odds = odds;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getOdds() {
		return odds;
	}

	public void setOdds(float odds) {
		this.odds = odds;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((match == null) ? 0 : match.hashCode());
		result = prime * result + ((rule == null) ? 0 : rule.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (match == null) {
			if (other.match != null)
				return false;
		} else if (!match.equals(other.match))
			return false;
		if (rule != other.rule)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [rule=" + rule + ", match=" + match + ", cost=" + cost
				+ ", odds=" + odds + "]";
	}
	
	
}
