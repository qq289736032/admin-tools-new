package com.lingyu.noark.amf3.deserializer;

public class Amf3Deserializer implements AutoCloseable {
	private final static ThreadLocal<Amf3Deserializer> Amf3ParserLocal = new ThreadLocal<>();
	private final Amf3Input input;

	private Amf3Deserializer() {
		input = new Amf3Input();
	}

	public static Amf3Deserializer get() {
		Amf3Deserializer serializer = Amf3ParserLocal.get();
		if (serializer == null) {
			serializer = new Amf3Deserializer();
			Amf3ParserLocal.set(serializer);
		}
		return serializer;
	}

	public Object read(byte[] bytes) {
		input.reset(bytes);
		return input.readObject();
	}

	@Override
	public void close() {
		input.reset();
	}
}