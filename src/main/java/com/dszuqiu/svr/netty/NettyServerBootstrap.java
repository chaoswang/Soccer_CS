package com.dszuqiu.svr.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import com.dszuqiu.common.utils.DebugLogger;
import com.dszuqiu.svr.html.HtmlParser;

public class NettyServerBootstrap {

	private static Logger logger = DebugLogger.getLogger(NettyServerBootstrap.class);

	private int port;
	
	private HtmlParser htmlParser;

	public NettyServerBootstrap(int port, HtmlParser htmlParser) {
		this.port = port;
		this.htmlParser = htmlParser;
		bind();
	}

	private void bind() {

		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();

		try {

			ServerBootstrap bootstrap = new ServerBootstrap();

			bootstrap.group(boss, worker);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.option(ChannelOption.SO_BACKLOG, 1024); // ������
			bootstrap.option(ChannelOption.TCP_NODELAY, true); // ���ӳ٣���Ϣ��������
			bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true); // ������
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel)
						throws Exception {
					ChannelPipeline p = socketChannel.pipeline();
					p.addLast(new LineBasedFrameDecoder(1024));
					p.addLast(new StringDecoder(Charset.forName("UTF-8")));
					p.addLast(new NettyServerHandler(htmlParser));
				}
			});
			ChannelFuture f = bootstrap.bind(port).sync();
			if (f.isSuccess()) {
				logger.info("����Netty����ɹ����˿ںţ�" + this.port);
			}
			// �ر�����
			f.channel().closeFuture().sync();

		} catch (Exception e) {
			logger.error("����Netty�����쳣���쳣��Ϣ��" + e.getMessage());
			e.printStackTrace();
		} finally {
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}
}
