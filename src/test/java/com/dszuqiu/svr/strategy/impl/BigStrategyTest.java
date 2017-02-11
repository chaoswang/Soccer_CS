package com.dszuqiu.svr.strategy.impl;

import org.junit.Assert;
import org.junit.Test;

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Order;
import com.dszuqiu.common.entity.Rule;
import com.dszuqiu.common.utils.CostUtil;
import com.dszuqiu.svr.strategy.StrategyFactory;

public class BigStrategyTest {

	@Test
	public void testWinAll(){
		Match orderMatch = new Match();
		orderMatch.setCurrentBigSmall("1.75 ");
		Order order = new Order(Rule.RULE_1, orderMatch, CostUtil.COST_100, 1.75f);
		Match finishedMatch = new Match();
		finishedMatch.setCurrentScore("1 : 2 ");
		StrategyFactory strategy = new StrategyFactory(order, finishedMatch);
		Assert.assertEquals(2, strategy.isWin());
	}
	
	@Test
	public void testWinHalf(){
		Match orderMatch = new Match();
		orderMatch.setCurrentBigSmall("1.75 ");
		Order order = new Order(Rule.RULE_1, orderMatch, CostUtil.COST_100, 1.75f);
		Match finishedMatch = new Match();
		finishedMatch.setCurrentScore("0 : 2 ");
		StrategyFactory strategy = new StrategyFactory(order, finishedMatch);
		Assert.assertEquals(1, strategy.isWin());
	}
	
	@Test
	public void testFloatPlus(){
		double threeTenths1 = 0.3;
		double threeTenths2 = 0.1 + 0.1 + 0.1;

		if (threeTenths1 == threeTenths2) {
		   System.out.println("Math is a world of absolute truth.");
		} else {
		   System.out.println("This is not logical!");
		}
	}
}
