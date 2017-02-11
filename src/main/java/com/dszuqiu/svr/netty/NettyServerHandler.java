package com.dszuqiu.svr.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.dszuqiu.common.GlobalParam;
import com.dszuqiu.common.entity.InvestSuggestion;
import com.dszuqiu.common.entity.Match;
import com.dszuqiu.common.entity.ServerMatchBean;
import com.dszuqiu.common.utils.DebugLogger;
import com.dszuqiu.common.utils.JsonUtil;
import com.dszuqiu.svr.handler.SuggestionHandler;
import com.dszuqiu.svr.html.HtmlParser;

public class NettyServerHandler extends ChannelHandlerAdapter {
	private static Logger logger = DebugLogger.getLogger(NettyServerHandler.class);
	
	HtmlParser htmlParser;
	
	public NettyServerHandler(HtmlParser htmlParser){
		this.htmlParser = htmlParser;
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {

//		ByteBuf buf = (ByteBuf) msg;
//
//		String recieved = getMessage(buf);
		String recieved = (String)msg;
		logger.info("服务器接收到消息：" + recieved);

		Set<Match> allMatches = htmlParser.getAllCurrentMatch();
		
//		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<ServerMatchBean> list = new ArrayList<ServerMatchBean>();
		
		for(Match match : allMatches){
			ServerMatchBean bean = new ServerMatchBean();
			bean.setMatch(match);
			bean.setSuggestion(SuggestionHandler.getInstance().getSuggestionByTrId(match.getTrId()));
			list.add(bean);
		}
		
		try {
			ctx.writeAndFlush(getSendByteBuf(JsonUtil.createJsonString(list)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 从ByteBuf中获取信息 使用UTF-8编码返回
	 */
	private String getMessage(ByteBuf buf) {

		byte[] con = new byte[buf.readableBytes()];
		buf.readBytes(con);
		try {
			return new String(con, GlobalParam.UTF8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ByteBuf getSendByteBuf(String message)
			throws UnsupportedEncodingException {

		byte[] req = (message +System.getProperty("line.separator")).getBytes("UTF-8");
		ByteBuf pingMessage = Unpooled.buffer();
		pingMessage.writeBytes(req);

		return pingMessage;
	}
}