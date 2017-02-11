package com.dszuqiu.svr.html;

import java.util.HashSet;
import java.util.Set;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.utils.DebugLogger;
import com.dszuqiu.svr.handler.OrderHandler;
import com.dszuqiu.svr.interceptor.InterceptorChain;
import com.dszuqiu.svr.interceptor.InterceptorChainService;

public class HtmlParser extends TimerTask{
	private static final Logger logger = DebugLogger.getLogger(HtmlParser.class);
	
	private static final String WEBSITE_URL = "http://live.dszuqiu.com/";
	private static final String GECKO_DRIVER = "D:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe";
	private static final String	FIREFOX_BIN = "D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
			
	private InterceptorChain chain = InterceptorChainService.newInterceptorChain();
	
	//match内存池   
	private Set<Match> matchesSet = new HashSet<Match>();
	
	private WebDriver driver;
	
	public HtmlParser(){
		System.setProperty("webdriver.gecko.driver", GECKO_DRIVER);
		System.setProperty("webdriver.firefox.bin", FIREFOX_BIN);
		driver = new FirefoxDriver();
	}

	@Override
	public void run() {
		Document doc = getHtmlDoc();
		
		Match match = null;
		try{
			Element content = doc.getElementById("data_table");
			Elements trs = content.select("tr[id~=r\\d++]");
			Set<Match> matches = new HashSet<Match>();
			
			for (Element tr: trs) {
				match = new Match();
				match.setTrId(tr.id());
				Elements tds = tr.getElementsByTag("td");
				if(tds.size()>0){
					String type = tds.get(0).text();
					match.setType(type);
					String time = tds.get(2).text();
					if(time.indexOf("未") != -1){
						continue;
					}
					match.setTime(time);
					String host = tds.get(3).text();
					match.setHostAndConcede(host);
					String score = tds.get(4).text();
					match.setCurrentScore(score);
					String guest = tds.get(5).text();
					match.setBigSmallAndGuest(guest);
					String dynamic = tds.get(6).text();
					match.setDynamicInfo(dynamic);
					String hostOdds = tds.get(7).text();
					match.setHostOdds(parseFloat(hostOdds));
					String concede = tds.get(8).text();
					match.setCurrentConcede(concede);
				}
				
				//保存角球的index
				int index = -1;
				for(Element td: tds){
					String linkHref = td.attr("class");
					String corner = td.text();
					if("text-center blue-color".equals(linkHref)){
						match.setCorner(corner);
						index = tds.indexOf(td);
						break;
					}
				}
				if(index == -1){
					logger.error("error:"+match);
					continue;
				}
				String guestOdds = tds.get(index-1).text();
				match.setGuestOdds(parseFloat(guestOdds));
				
				String bigOdds = tds.get(index+1).text();
				match.setBigOdds(parseFloat(bigOdds));
				
				String bigSmall = tds.get(index+2).text();
				match.setCurrentBigSmall(bigSmall);
				
				//保存"析"的index
				index = -1;
				for(Element td: tds){
					String xi = td.text();
					if("析".equals(xi)){
						index = tds.indexOf(td);
						break;
					}
				}
				
				String small = tds.get(index-1).text();
				match.setSmallOdds(parseFloat(small));
				matches.add(match);
			}
			updateMatches(matches);
		}catch(Exception e){
			logger.warn(""+match, e);
		}
		
	}

	private Document getHtmlDoc() {
		Document doc = null;
		try {
	        driver.get(WEBSITE_URL);
	        // 通过 id找到DOM
	        WebElement element = driver.findElement(By.id("data_table"));
	        
	        // 通过element不为空，来判断javascript加载完了没
	        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	                return element != null;
	            }
	        });
	        doc = Jsoup.parse(driver.getPageSource());
		} catch (Exception e) {
			logger.error("HtmlParser failed.", e);
			
		}
		return doc;
	}
	
	private float parseFloat(String data) {
		float rtn = 0;
		try {
			rtn = Float.parseFloat(data);
		} catch (NumberFormatException e) {
			logger.warn("NumberFormatException:" + data);
		}
		return rtn;
	}

	private void processFinishedMatch(Set<Match> finished) {
		logger.info("finished match:" + finished);
		//历史比赛入库
		for(Match match : finished){
			long matchId = MatchLoader.getDao().insertMatch(match);
			//订单结算
			OrderHandler.getInstance().closeOrder(match, matchId);
		}
		
	}

	private void updateMatches(Set<Match> newMatches) {
		
		// 找出内存池有，而最新的比赛集合没有的，表示比赛已经结束
		Set<Match> intersection = new HashSet<Match> (matchesSet);
		intersection.retainAll(newMatches);
		 
		Set<Match> finished = new HashSet<Match> (matchesSet);
		finished.removeAll(intersection);
		processFinishedMatch(finished);
		
		//更新match内存池
		matchesSet = newMatches;
    	for(Match newMatch : matchesSet){
			chain.callChain(newMatch);
    	}
		
    }
	
	public Set<Match> getAllCurrentMatch(){
		return new HashSet<Match>(matchesSet);
	}

}
