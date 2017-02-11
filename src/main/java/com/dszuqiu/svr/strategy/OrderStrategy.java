package com.dszuqiu.svr.strategy;

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Order;

public abstract class OrderStrategy {
	protected Order order;
	protected Match finishedMatch;

	// ����˵�Ͷע��׬����Ǯ���������Ǯ
	public abstract double getInterest();

	// Ӯ������,2��ʾӮ��1��ʾӮһ�룬0��ʾ��ˮ
	public abstract int isWin();
}
