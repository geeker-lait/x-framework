package com.tasfe.framework.uid.handler;

import com.tasfe.framework.uid.util.KryoUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author lait.zhang
 */
public class DecoderHandler extends ByteToMessageDecoder{
	
	private Class<?> type;
	
	public DecoderHandler(Class<?> type) {
		super();
		this.type = type;
	}


	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		if(in.readableBytes() < 4) {
			return;
		}
		in.markReaderIndex();
		int dataLength = in.readInt();
		if(dataLength < 0) {
			ctx.close();
		}
		if(in.readableBytes() < dataLength) {
			in.resetReaderIndex();
		}
		byte[] data = new byte[dataLength];
		in.readBytes(data);
		Object obj = KryoUtil.byteToObj(data, type);
		out.add(obj);
	}

}
