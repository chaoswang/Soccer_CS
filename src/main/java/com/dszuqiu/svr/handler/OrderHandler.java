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
	//δ���Ķ�������
	private static List<Order> orderList = new LinkedList<Order>(); 
	private static final Logger logger = DebugLogger.getLogger(OrderHandler.class);
	private static final OrderHandler singleton = new OrderHandler();
	
	private OrderHandler(){
	}
	
	public static OrderHandler getInstance(){ 
		return singleton;
	}
	
	/**
	 * �µ�
	 * @param invest
	 * @param match
	 */
	public void handleOrder(Order order){
		//�Ѿ��¹�����
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
		// ���÷����ʼ��Ļ�������
        final Properties props = new Properties();
        /*
         * ���õ����ԣ� mail.store.protocol / mail.transport.protocol / mail.host /
         * mail.user / mail.from
         */
        // ��ʾSMTP�����ʼ�����Ҫ���������֤
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.163.com");
        // �����˵��˺�
        props.put("mail.user", "chaoswang@163.com");
        // ����SMTP����ʱ��Ҫ�ṩ������
        props.put("mail.password", "chaoaijing0104");

        // ������Ȩ��Ϣ�����ڽ���SMTP���������֤
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // �û���������
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // ʹ�û������Ժ���Ȩ��Ϣ�������ʼ��Ự
        Session mailSession = Session.getInstance(props, authenticator);
        // �����ʼ���Ϣ
        MimeMessage message = new MimeMessage(mailSession);
        // ���÷�����
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);

        // �����ռ���
        InternetAddress to = new InternetAddress("263050006@qq.com");
        message.setRecipient(RecipientType.TO, to);

        // ���ó���
//        InternetAddress cc = new InternetAddress("luo_aaaaa@yeah.net");
//        message.setRecipient(RecipientType.CC, cc);

        // �������ͣ��������ռ��˲��ܿ������͵��ʼ���ַ
//        InternetAddress bcc = new InternetAddress("aaaaa@163.com");
//        message.setRecipient(RecipientType.CC, bcc);

        // �����ʼ�����
        message.setSubject(match.getType() +", "+match.getHostAndConcede());

        // �����ʼ���������
        message.setContent(
        				"<li><font color=\"red\"><strong>Ͷ�ʽ��飺"+invest+"</strong></font></li>"
						+ "<li>���Ӽ���������" + match.getHostAndConcede()+"</li>"
        				+ "<li>���̴�С���Ͷӣ�"+match.getBigSmallAndGuest()+"</li>"
        				+ "<li>�Ƿ����̣�" + MatchUtil.isDeepDisk(match)+"</li>"
        				+ "<li>��ʱ��С�̣�" + MatchUtil.getCurrentBigSmall(match) + "</li>"
        				+ "<li>��ʱ�ȷ֣�" + match.getCurrentScore() + "</li>", 
        		"text/html;charset=UTF-8");

        // �����ʼ�
        Transport.send(message);
		
	}

	
}
