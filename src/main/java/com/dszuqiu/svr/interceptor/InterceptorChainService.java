package com.dszuqiu.svr.interceptor;


public class InterceptorChainService {
	    public static InterceptorChain newInterceptorChain() { 
	        InterceptorChain chain = new InterceptorChain(); 

//	        chain.add(new HalfReverseInterceptor()); 
//	        chain.add(new TwentyMinRuleInterceptor()); 
//	        chain.add(new Rule334Interceptor()); 
	        
//	        chain.add(new PanziRollingBallInterceptor());
//	        chain.add(new UniqueInterceptor());

	        return chain; 
	    } 
}
