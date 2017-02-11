package com.dszuqiu.svr.interceptor;

import org.apache.log4j.Logger;

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.utils.DebugLogger;

public class InterceptorChain {
	private static final Logger logger = DebugLogger.getLogger(InterceptorChain.class);
	private InterceptorEntry header;

	public void add(Interceptor inp) {
		if (header == null) {
			header = new InterceptorEntry(inp);
		} else {
			InterceptorEntry last = findLast();
			last.setNext(inp);
		}
	}

	private InterceptorEntry findLast() {
		InterceptorEntry entry = header;
		while (entry.next() != null) {
			entry = entry.next();
		}

		return entry;
	}

	private boolean isEmpty() {
		return header == null;
	}

	/** 
     * 依次调用拦截器链中的所有拦截器。 
     *  
     * @param almInfo 
     *            在拦截器中处理的消息 
     */ 
    public boolean callChain(Match match) { 
        if (isEmpty()) { 
            return false; 
        } 

        InterceptorEntry entry = header; 
        do { 
            try { 
                entry.interceptor().process(match);
            } catch (Exception ex) { 
                logger.info("Process failed, inp=" 
                        + entry.interceptor().getClass().getName() 
                        + ", newMatch=" + match, ex); 
            } 

        } while ((entry = entry.next()) != null); 
        return false; 
    } 
}

class InterceptorEntry {

	private Interceptor inp;
	private InterceptorEntry next;

	protected InterceptorEntry(Interceptor inp) {
		this.inp = inp;
	}

	protected void setNext(Interceptor next) {
		this.next = new InterceptorEntry(next);
	}

	protected InterceptorEntry next() {
		return this.next;
	}

	protected Interceptor interceptor() {
		return this.inp;
	}
}
