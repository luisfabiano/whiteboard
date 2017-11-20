package com.mp.serice.core.net.server.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class SimpleTelnetInitializer extends ChannelInitializer<SocketChannel> {
	private static final StringDecoder DECODER = new StringDecoder();
	private static final StringEncoder ENCODER = new StringEncoder();
	private static final int DEFAULT_MAX_DECODED_FRAME = 8192;

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		ChannelPipeline pipeline = channel.pipeline();
		pipeline.addLast(new DelimiterBasedFrameDecoder(DEFAULT_MAX_DECODED_FRAME, Delimiters.lineDelimiter()) ) ;
		// 添加编码和解码的类
		pipeline.addLast(DECODER);
		pipeline.addLast(ENCODER);

		// 注册命令解析处理类
		pipeline.addLast(new SimpleTelnetHandler()) ;
	}
}