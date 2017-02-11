package com.dszuqiu.svr.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Order;
import com.dszuqiu.common.utils.MatchUtil;
import com.dszuqiu.svr.db.SoccerRepository;
import com.dszuqiu.svr.html.MatchLoader;
import com.dszuqiu.svr.strategy.StrategyFactory;

@Repository
public class JdbcSoccerRepository implements SoccerRepository {
	private JdbcOperations jdbcOperations;

	private static final String INSERT_MATCH = "insert into soccer.match(matchType,time,"
			+ "host,concede,finalScore,bigSmall,guest,dynamicInfo,corner) values(?,?,?,?,?,?,?,?,?)";

	private static final String INSERT_ORDER = "insert into soccer.order(ruleIndex,matchId,"
			+ "cost,odds,interest,win,hostOdds,currentConcede"
			+ ",guestOdds,currentCorner,bigOdds,currentBigSmall,smallOdds,"
			+ "hostScore,guestScore,currentTime, orderTime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())";

	public JdbcSoccerRepository(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}

	public long insertMatch(Match match) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcOperations.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						INSERT_MATCH, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, match.getType());
				ps.setInt(2, MatchUtil.getShiJian(match));
				ps.setString(3, match.getHostAndConcede());
				ps.setFloat(4, MatchUtil.getChuPan(match));
				ps.setString(5, MatchUtil.getCurrentScore(match));
				ps.setFloat(6, MatchUtil.getChuPanBigSmall(match));
				ps.setString(7, match.getBigSmallAndGuest());
				ps.setString(8, match.getDynamicInfo());
				ps.setString(9, match.getCorner());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	/**
	 * 
	 * @param order
	 *            结算的订单
	 * @param matchDbId
	 *            这场比赛在数据库中的id值
	 * @param finishedMatch
	 *            已经结束的比赛，即比赛在90分钟的状态
	 */
	public void insertOrder(Order order, long matchDbId, Match finishedMatch) {
		StrategyFactory strategy = new StrategyFactory(order, finishedMatch);
		jdbcOperations.update(INSERT_ORDER, order.getRule().getIndex(),
				matchDbId, order.getCost(), order.getOdds(), strategy
						.getInterest(), strategy.isWin(), order.getMatch()
						.getHostOdds(), MatchUtil.getCurrentConcede(order
						.getMatch()), order.getMatch().getGuestOdds(), order
						.getMatch().getCorner(), order.getMatch().getBigOdds(),
				MatchUtil.getCurrentBigSmall(order.getMatch()), order
						.getMatch().getSmallOdds(), MatchUtil.getZhuScore(order
						.getMatch()), MatchUtil.getKeScore(order.getMatch()),
				MatchUtil.getShiJian(order.getMatch()));
	}

	public static void main(String[] args) {
		// MatchLoader.getDao().testInsertOrder();
		MatchLoader.getDao().testInsertMatch();
	}

	public long testInsertMatch() {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcOperations.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						INSERT_MATCH, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, "0");
				ps.setInt(2, 0);
				ps.setString(3, "0");
				ps.setFloat(4, 0);
				ps.setString(5, "0");
				ps.setFloat(6, 0);
				ps.setString(7, "0");
				ps.setString(8, "0");
				ps.setString(9, "0");
				return ps;
			}
		}, keyHolder);
		System.out.println("id:" + keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
	}

	public void testInsertOrder() {
		jdbcOperations.update(INSERT_ORDER, 1, 1, 1, 1, 1, 1, 1, 1, 1, "1:1",
				1, 1, 1, 1, 1, 1);
	}
}
