package com.dszuqiu.svr.strategy;

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Order;

public abstract class OrderStrategy {
	protected Order order;
	protected Match finishedMatch;

	// 算出此单投注能赚多少钱，或亏多少钱
	public abstract double getInterest();

	// 赢还是输,2表示赢，1表示赢一半，0表示走水
	public abstract int isWin();
}
