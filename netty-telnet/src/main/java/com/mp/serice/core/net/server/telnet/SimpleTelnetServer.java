package com.mp.serice.core.net.server.telnet;

import com.mp.serice.core.net.commons.exception.NetArgumentsException;
import com.mp.serice.core.net.server.exception.ServerInitException;
import com.mp.serice.core.net.server.handler.SimpleTelnetInitializer;
import com.mp.serice.core.net.util.IPAddrUtil;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class SimpleTelnetServer {
	private final static int DEFAULT_TELNET_SERVER_PORT = 23;
	private final static String DEFAULT_TELNET_SERVER_HOST = "127.0.0.1";
	private final static int DEFAULT_BACKLOG = 1024;
	private int port;
	private String host;
	private Long hostNumberCode;

	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	private ServerBootstrap serverBootstrap ;

	public SimpleTelnetServer() throws ServerInitException {
		this(DEFAULT_TELNET_SERVER_PORT);
	}

	public SimpleTelnetServer(int port) throws ServerInitException {
		this(port, DEFAULT_TELNET_SERVER_HOST);
	}

	public SimpleTelnetServer(int port, String host) throws ServerInitException {
		if(port <1)
			throw new ServerInitException("Invalid port") ;

		if(null == host || host.trim().length()<1) {
			throw new ServerInitException("Invalid host") ;
		}

		try {
			this.hostNumberCode = IPAddrUtil.getNumierCodeFormIpAddr(host) ;
			System.out.println(String.format("host:%s ---> numberCode:%s", host, this.hostNumberCode));
		} catch (NetArgumentsException e) {
			throw new ServerInitException(e) ;
		}

		this.port = port;
		this.host = host;
		this.bossGroup = new NioEventLoopGroup(1);
		this.workerGroup = new NioEventLoopGroup();

	}

	public void open() throws InterruptedException {
		this.serverBootstrap = new ServerBootstrap();
		this.serverBootstrap.option(ChannelOption.SO_BACKLOG, DEFAULT_BACKLOG);
		this.serverBootstrap.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new SimpleTelnetInitializer());
		Channel channel = serverBootstrap.bind(this.host, this.port).sync().channel();
		channel.closeFuture().sync();
	}

	public void close() {
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}
}