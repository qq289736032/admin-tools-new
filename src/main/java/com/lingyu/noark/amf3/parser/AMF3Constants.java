package com.lingyu.noark.amf3.parser;

public interface AMF3Constants {

    public static final byte AMF3_UNDEFINED = 0x00;
    public static final byte AMF3_NULL = 0x01;
    public static final byte AMF3_BOOLEAN_FALSE = 0x02;
    public static final byte AMF3_BOOLEAN_TRUE = 0x03;
    public static final byte AMF3_INTEGER = 0x04;
    public static final byte AMF3_NUMBER = 0x05;
    public static final byte AMF3_STRING = 0x06;
    public static final byte AMF3_XML = 0x07;
    public static final byte AMF3_DATE = 0x08;
    public static final byte AMF3_ARRAY = 0x09;
    public static final byte AMF3_OBJECT = 0x0A;
    public static final byte AMF3_XMLSTRING = 0x0B;
    public static final byte AMF3_BYTEARRAY = 0x0C;
    
    public static final int AMF3_INTEGER_MAX = Integer.MAX_VALUE >> 3;
    public static final int AMF3_INTEGER_MIN = Integer.MIN_VALUE >> 3;
}