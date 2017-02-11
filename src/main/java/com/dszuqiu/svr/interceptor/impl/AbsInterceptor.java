package com.dszuqiu.svr.interceptor.impl;

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.utils.MatchUtil;
import com.dszuqiu.svr.interceptor.Interceptor;

public abstract class AbsInterceptor implements Interceptor {

	double chuPan;
	int zhuScore;
	int keScore;
	double bigSmall;
	double chuPanBigSmall;
	int totalScore;
	int hostconcede;
	int guestconcede;
	int shiJian;
	double panKou;

	@Override
	public void process(Match match) {
		chuPan = MatchUtil.getChuPan(match);
		zhuScore = MatchUtil.getZhuScore(match);
		keScore = MatchUtil.getKeScore(match);
		bigSmall = MatchUtil.getCurrentBigSmall(match);
		chuPanBigSmall = MatchUtil.getChuPan(match);
		totalScore = zhuScore + keScore;
		hostconcede = zhuScore - keScore;
		guestconcede = keScore - zhuScore;
		shiJian = MatchUtil.getShiJian(match);
		panKou = MatchUtil.getBigSmallPankou(match);

		if (shiJian == Integer.MAX_VALUE) {
			return;
		}
		if (MatchUtil.isUpwindBall(match)) {// ²»×·Äæ·çÇò
			return;
		}

		makeSuggestion(match);
	}

	protected abstract void makeSuggestion(Match match);
}
