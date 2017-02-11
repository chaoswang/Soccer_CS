package com.dszuqiu.svr.strategy.impl;

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Order;
import com.dszuqiu.common.utils.MatchUtil;
import com.dszuqiu.svr.strategy.OrderStrategy;

public class BigStrategy extends OrderStrategy{
	
	public BigStrategy(Order order, Match finishedMatch){
		super.order = order;
		super.finishedMatch = finishedMatch;
	}
	
    public double getInterest(){
		float orderBigsmall = MatchUtil.getCurrentBigSmall(order.getMatch());
		int finalScore = MatchUtil.getTotalScore(finishedMatch);
		if(finalScore - orderBigsmall >= 0.5f){
			return order.getCost() + order.getCost() * (order.getOdds()-1);
		}
		if(finalScore - orderBigsmall == 0.25f){
			return order.getCost() + order.getCost() * (order.getOdds()-1) * 0.5;
		}
		if(finalScore - orderBigsmall == -0.25f){
			return -order.getCost() * 0.5;
		}
		if(finalScore - orderBigsmall <= -0.5f){
			return -order.getCost();
		}
		return 0d;
	}
    
    public int isWin(){
		float orderBigsmall = MatchUtil.getCurrentBigSmall(order.getMatch());
		int finalScore = MatchUtil.getTotalScore(finishedMatch);
		if(finalScore - orderBigsmall >= 0.5f){
			return 2;
		}
		if(finalScore - orderBigsmall == 0.25f){
			return 1;
		}
		if(finalScore - orderBigsmall == -0.25f){
			return -1;
		}
		if(finalScore - orderBigsmall <= -0.5f){
			return -2;
		}
		return 0;
	}
}
