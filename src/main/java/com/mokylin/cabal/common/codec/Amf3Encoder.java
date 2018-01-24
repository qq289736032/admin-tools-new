package com.mokylin.cabal.common.codec;

import org.apache.commons.lang3.mutable.MutableBoolean;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/** 编码器 */
public class Amf3Encoder extends MessageToByteEncoder<byte[]> {
	private MutableBoolean common;//是否正常通信

	public Amf3Encoder(MutableBoolean common) {
		this.common = common;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, byte[] msg, ByteBuf byteBuf) throws Exception {
		if (msg != null) {
			if (common.isTrue()) {
				byteBuf.writeInt(msg.length);
			}
			byteBuf.writeBytes(msg);
		}
	}
}
