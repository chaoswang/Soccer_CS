package com.dszuqiu.clnt.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.log4j.Logger;

import com.dszuqiu.clnt.view.SoccerUI;
import com.dszuqiu.common.entity.ServerMatchBean;
import com.dszuqiu.common.utils.DebugLogger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class NettyClientHandler extends ChannelHandlerAdapter {
	private static final Logger logger = DebugLogger.getLogger(ChannelHandlerAdapter.class);
	private ByteBuf firstMessage;
	private SoccerUI ui;
	
	public NettyClientHandler(SoccerUI ui){
		this.ui = ui;
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		byte[] data = ("服务器，给我一个APPLE"+System.getProperty("line.separator") ).getBytes("UTF-8");
		firstMessage = Unpooled.buffer();
		firstMessage.writeBytes(data);
		ctx.writeAndFlush(firstMessage);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
//		ByteBuf buf = (ByteBuf) msg;
//		String rev = getMessage(buf);
		String rev = (String) msg;
		logger.info("客户端收到服务器数据:" + rev);
		ui.updateData(new Gson().fromJson(rev,  new TypeToken<List<ServerMatchBean>>() {  
                }.getType()) );
	}

	private String getMessage(ByteBuf buf) {
		byte[] con = new byte[buf.readableBytes()];
		buf.readBytes(con);
		try {
			return new String(con, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
