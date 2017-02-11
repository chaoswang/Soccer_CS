package com.dszuqiu.svr.strategy;

import com.dszuqiu.common.GlobalParam;
import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Order;
import com.dszuqiu.svr.strategy.impl.BigStrategy;
import com.dszuqiu.svr.strategy.impl.ConcedeStrategy;

/**
 * 策略模式与简单工厂模式结合 
 */
public class StrategyFactory {
	OrderStrategy strategy = null;
	
	public StrategyFactory(Order order, Match finishedMatch){
		switch(order.getRule().getStrategyType()){
			case GlobalParam.RULE_TYPE_BIG_BALL:
				strategy = new BigStrategy(order, finishedMatch);
				break;
			case GlobalParam.RULE_TYPE_CONCEDE:
				strategy = new ConcedeStrategy(order, finishedMatch);
				break;
		}
	}
	
	public double getInterest(){
		return strategy.getInterest();
	}
    
    public int isWin(){
    	return strategy.isWin();
    }
}
