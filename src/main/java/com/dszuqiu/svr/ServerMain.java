package com.dszuqiu.svr;

import org.apache.log4j.Logger;

import com.dszuqiu.common.utils.DebugLogger;
import com.dszuqiu.svr.html.MatchLoader;
import com.dszuqiu.svr.netty.NettyServerBootstrap;

public class ServerMain {
	private static final Logger logger = DebugLogger.getLogger(ServerMain.class);
	
	public static void main(String args[]) {
		logger.info("###赌神一路发 服务端###");
		NettyServerBootstrap server= new NettyServerBootstrap(9999, new MatchLoader().load());
	}
	
	
}
