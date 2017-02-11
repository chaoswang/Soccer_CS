package com.dszuqiu.svr.handler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.Order;
import com.dszuqiu.common.utils.DebugLogger;
import com.dszuqiu.common.utils.MatchUtil;
import com.dszuqiu.svr.html.MatchLoader;

public class OrderHandler {
	//未完结的订单集合
	private static List<Order> orderList = new LinkedList<Order>(); 
	private static final Logger logger = DebugLogger.getLogger(OrderHandler.class);
	private static final OrderHandler singleton = new OrderHandler();
	
	private OrderHandler(){
	}
	
	public static OrderHandler getInstance(){ 
		return singleton;
	}
	
	/**
	 * 下单
	 * @param invest
	 * @param match
	 */
	public void handleOrder(Order order){
		//已经下过订单
		if(orderList.contains(order)){
			return;
		}
		
		logger.error(order.getRule().getName() +":" + order.getMatch());
		System.out.println(order.getRule().getName() +":" + order.getMatch());
		orderList.add(order);
//		try {
//			sendMail(order.getRule().getName(), order.getMatch());
//			Thread.sleep(3000L);
//		} catch (Exception e) {
//			logger.warn("sendMail Failed.", e);
//			System.out.println("sendMail Failed.");
//		}
	}
	
	public void closeOrder(Match finishedMatch, long matchDbId) {
		Iterator<Order> itor = orderList.iterator();
		while(itor.hasNext()){
			Order order = itor.next();
			if(order.getMatch().equals(finishedMatch)){
				itor.remove();
				System.out.println(order);
				MatchLoader.getDao().insertOrder(order, matchDbId, finishedMatch);
			}
		}
	}


	private void sendMail(String invest, Match match) throws Exception{
		// 配置发送邮件的环境属性
        final Properties props = new Properties();
        /*
         * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
         * mail.user / mail.from
         */
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.163.com");
        // 发件人的账号
        props.put("mail.user", "chaoswang@163.com");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "chaoaijing0104");

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人
        InternetAddress to = new InternetAddress("263050006@qq.com");
        message.setRecipient(RecipientType.TO, to);

        // 设置抄送
//        InternetAddress cc = new InternetAddress("luo_aaaaa@yeah.net");
//        message.setRecipient(RecipientType.CC, cc);

        // 设置密送，其他的收件人不能看到密送的邮件地址
//        InternetAddress bcc = new InternetAddress("aaaaa@163.com");
//        message.setRecipient(RecipientType.CC, bcc);

        // 设置邮件标题
        message.setSubject(match.getType() +", "+match.getHostAndConcede());

        // 设置邮件的内容体
        message.setContent(
        				"<li><font color=\"red\"><strong>投资建议："+invest+"</strong></font></li>"
						+ "<li>主队及初盘让球：" + match.getHostAndConcede()+"</li>"
        				+ "<li>初盘大小及客队："+match.getBigSmallAndGuest()+"</li>"
        				+ "<li>是否深盘：" + MatchUtil.isDeepDisk(match)+"</li>"
        				+ "<li>即时大小盘：" + MatchUtil.getCurrentBigSmall(match) + "</li>"
        				+ "<li>即时比分：" + match.getCurrentScore() + "</li>", 
        		"text/html;charset=UTF-8");

        // 发送邮件
        Transport.send(message);
		
	}

	
}
