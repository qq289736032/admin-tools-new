package com.lingyu.noark.amf3.exception;

import java.util.Arrays;

public class UnknownTypeException extends RuntimeException {

	private static final long serialVersionUID = -8973401601270366490L;

	public UnknownTypeException(String string) {
		super(string);
	}

	public UnknownTypeException(String msg, byte[] bytes, Exception e) {
		super(msg + Arrays.toString(bytes), e);
	}
}