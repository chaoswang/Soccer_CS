package com.dszuqiu.common.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DebugLogger {
	
	static{
		PropertyConfigurator.configure("conf/debug.properties");
	}

	public static Logger getLogger(Class<?> clazz) {
		Logger logger = Logger.getLogger(clazz);
		return logger;
	}
}
