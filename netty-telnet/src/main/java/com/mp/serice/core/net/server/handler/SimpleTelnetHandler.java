package com.mp.serice.core.net.server.handler;

import java.net.InetAddress;
import java.util.Date;

import com.mp.serice.core.net.server.telnet.TelnetConstant;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SimpleTelnetHandler extends SimpleChannelInboundHandler<String> {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// Send greeting for a new connection.
		ctx.write(String.format("Welcome to %s !\r\n", InetAddress.getLocalHost().getHostName()));
		ctx.write(String.format("It is %s now.\r\n", new Date()));
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		String response = null;
		boolean close = false;
		if (msg.isEmpty()) {
			response = "Please type something.\r\nshell>";
		} else if (TelnetConstant.QUIT_KEY.equals(msg.toLowerCase())) {
			response = "Have a good day!\r\n";
			close = true;
		} else {
			response = String.format("\r\nDid you say '%s'?\r\nshell>", msg) ;
		}

		ChannelFuture future = ctx.write(response);
		ctx.flush();
		if (close) {
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}
}