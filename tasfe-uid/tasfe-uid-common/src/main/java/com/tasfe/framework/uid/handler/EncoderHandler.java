package com.tasfe.framework.uid.handler;

import com.tasfe.framework.uid.util.KryoUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author lait.zhang
 */
public class EncoderHandler extends MessageToByteEncoder<Object>{

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out)
			throws Exception {
		byte[] data = KryoUtil.objToByte(msg);
		out.writeInt(data.length);
		out.writeBytes(data);
	}

}
