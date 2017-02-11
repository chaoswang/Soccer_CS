package com.dszuqiu.svr.html;

import java.util.Timer;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.dszuqiu.common.GlobalParam;
import com.dszuqiu.common.utils.DebugLogger;
import com.dszuqiu.svr.db.impl.JdbcSoccerRepository;
import com.dszuqiu.svr.netty.NettyServerBootstrap;

public class MatchLoader {
	private static final Logger logger = DebugLogger.getLogger(MatchLoader.class);
	
	//初始化数据访问
	private static JdbcSoccerRepository dao = null;
	
	public HtmlParser load(){
		 Timer timer = new Timer();
		 HtmlParser htmlParser = new HtmlParser();
		 timer.scheduleAtFixedRate(htmlParser, 0, GlobalParam.HTTP_REQ_PERIOD);
		 return htmlParser;
	}
	
	
	@SuppressWarnings("resource")
	public static JdbcSoccerRepository getDao(){
		if(dao == null){
			String[] path = {"src/applicationContext.xml"};
			ApplicationContext ctx = new FileSystemXmlApplicationContext(path);
			dao = (JdbcSoccerRepository) ctx.getBean("soccerRepository");
		}
		return dao;
	}
	
}
