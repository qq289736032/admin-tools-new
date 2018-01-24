package com.lingyu.noark.amf3.serializer;

/**
 * Amf3序列化.
 * 
 * @author 小流氓<176543888@qq.com>
 */
public final class Amf3Serializer implements AutoCloseable {
	// 缓存Amf3Serializer
	private final static ThreadLocal<Amf3Serializer> Amf3SerializerLocal = new ThreadLocal<>();
	// 具体编码Buff类.
	private final Amf3Output buffer;

	private Amf3Serializer() {
		this.buffer = new Amf3Output();
	}

	public byte[] write(Object o) {
		buffer.writeObject(o);
		return buffer.getBytes();
	}

	public static Amf3Serializer get() {
		Amf3Serializer serializer = Amf3SerializerLocal.get();
		if (serializer == null) {
			serializer = new Amf3Serializer();
			Amf3SerializerLocal.set(serializer);
		}
		return serializer;
	}

	@Override
	public void close() {
		buffer.reset();
	}
}