package com.dszuqiu.common;

public class GlobalParam {
	//1分钟刷一次，频率太快被封ip
	public static final long HTTP_REQ_PERIOD = 61 * 1000L;
	public static final int RULE_TYPE_BIG_BALL = 1;
    public static final int RULE_TYPE_CONCEDE = 2;
    
    public static final String UTF8 = "UTF-8";
}
