package com.tasfe.framework.uid.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayOutputStream;

/**
 * Kryo序列化工具类
 *
 * @author lait.zhang
 */
public class KryoUtil {

	private static Kryo kryo = new Kryo();
	
	public static <T> T byteToObj(byte[] buffer, Class<T> type) {
		kryo.setReferences(false);
		kryo.setRegistrationRequired(false);
		Input input = new Input(buffer);
		return kryo.readObject(input, type);
	}
	
	public static <T> byte[] objToByte(T t) {
		kryo.setReferences(false);
		kryo.setRegistrationRequired(false);
		Output output = new Output(new ByteArrayOutputStream());
		kryo.writeObject(output, t);
		return output.toBytes();
	}
}
