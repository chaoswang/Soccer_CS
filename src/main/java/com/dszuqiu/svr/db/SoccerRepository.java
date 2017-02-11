package com.dszuqiu.svr.db;

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Order;

public interface SoccerRepository {
	
	public long insertMatch(Match match);
	public void insertOrder(Order order, long matchDbId, Match finishedMatch) ;
}
