package com.dszuqiu.svr.interceptor;

import com.dszuqiu.common.entity.Match;

public interface Interceptor {
	void process(Match match);
}
