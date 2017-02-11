package com.dszuqiu.clnt.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import com.dszuqiu.clnt.view.SoccerUI;
import com.dszuqiu.common.utils.DebugLogger;

public class NettyClientBootstrap {
	private static final Logger logger = DebugLogger.getLogger(NettyClientBootstrap.class);
	private int port;
	private String host;
	private SoccerUI ui;

	public NettyClientBootstrap(int port, String host, SoccerUI ui) throws InterruptedException {
		this.port = port;
		this.host = host;
		this.ui = ui;
		start();
	}

	private void start() throws InterruptedException {

		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

		try {

			Bootstrap bootstrap = new Bootstrap();
			bootstrap.channel(NioSocketChannel.class);
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.group(eventLoopGroup);
			bootstrap.remoteAddress(host, port);
			bootstrap.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel)
						throws Exception {
					socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024*1024));
					socketChannel.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
					socketChannel.pipeline().addLast(new NettyClientHandler(ui));
				}
			});
			ChannelFuture future = bootstrap.connect(host, port).sync();
			if (future.isSuccess()) {
				SocketChannel socketChannel = (SocketChannel) future.channel();
				logger.info("----------------connect server success----------------");
			}
			future.channel().closeFuture().sync();
		} finally {
			eventLoopGroup.shutdownGracefully();
		}
	}
}