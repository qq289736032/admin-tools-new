package com.mokylin.cabal.common.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lingyu.noark.amf3.Amf3;

/** 解码器 */
public class Amf3Decoder extends ByteToMessageDecoder {
	private static final Logger logger = LogManager.getLogger(Amf3Decoder.class);
	private final static String POLICY = "<?xml version=\"1.0\"?><cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy>\0";
	private final static String POLICY_REQUEST = "<policy-file-request/>";
	private MutableBoolean common;// 策略文件请求 false,正常消息true

	public Amf3Decoder(MutableBoolean common) {
		this.common = common;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> objects) throws Exception {
		if (byteBuf.readableBytes() < 4) {
			return;
		}
		// mark读索引
		byteBuf.markReaderIndex();
		int length = byteBuf.readInt();
		if (length > 65536) {
			byteBuf.resetReaderIndex();
			byte[] content = new byte[byteBuf.readableBytes()];
			byteBuf.readBytes(content);
			String request = new String(content);
			// 怀疑是请求安全策略
			if (request.indexOf(POLICY_REQUEST) >= 0) {
				common.setFalse();
				ctx.channel().writeAndFlush(POLICY.getBytes());
				logger.warn("该用户无法访问843端口，从主端口获取安全策略 address={}", ctx.channel().remoteAddress());
			} else {
				logger.error("request msg length > 65536,address={}", ctx.channel().remoteAddress());
				byteBuf.resetReaderIndex();
				ctx.close();
			}
			return;
		}
		if (length < 0) {
			logger.error("request msg length <0,length={}", length);
			byteBuf.resetReaderIndex();
			ctx.close();
			return;
		}

		if (byteBuf.readableBytes() < length) {
			byteBuf.resetReaderIndex();
			return;
		}
		byte[] content = new byte[length];
		byteBuf.readBytes(content);
		// logger.info("length={},msg={}", content.length, content);
		// content = encrypt.decode(content);
		Object[] msg = (Object[]) Amf3.parse(content);
		objects.add(msg);
		// objects.add(content.length);
	}

}
