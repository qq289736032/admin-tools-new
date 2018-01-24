package com.lingyu.noark.amf3;

public interface Amf3Type {
	// AMF marker constants
	int kUndefinedType = 0;
	int kNullType = 1;
	int kFalseType = 2;
	int kTrueType = 3;
	int kIntegerType = 4;
	int kDoubleType = 5;
	int kStringType = 6;
	int kXMLType = 7;
	int kDateType = 8;
	int kArrayType = 9;
	int kObjectType = 10;
	int kAvmPlusXmlType = 11;
	int kByteArrayType = 12;

	String EMPTY_STRING = "";

	/**
	 * Internal use only.
	 * 
	 * @exclude
	 */
	int UINT29_MASK = 0x1FFFFFFF; // 2^29 - 1

	/**
	 * The maximum value for an <code>int</code> that will avoid promotion to an
	 * ActionScript Number when sent via AMF 3 is 2<sup>28</sup> - 1, or
	 * <code>0x0FFFFFFF</code>.
	 */
	int INT28_MAX_VALUE = 0x0FFFFFFF; // 2^28 - 1

	/**
	 * The minimum value for an <code>int</code> that will avoid promotion to an
	 * ActionScript Number when sent via AMF 3 is -2<sup>28</sup> or
	 * <code>0xF0000000</code>.
	 */
	int INT28_MIN_VALUE = 0xF0000000; // -2^28 in 2^29 scheme
}