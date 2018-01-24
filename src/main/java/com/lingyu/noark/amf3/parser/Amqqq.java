package com.lingyu.noark.amf3.parser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.lingyu.noark.amf3.Amf3Type;
import com.lingyu.noark.amf3.exception.UTFDataFormatException;
import com.lingyu.noark.amf3.exception.UnknownTypeException;

class Amqqq implements Amf3Type {
	private byte[] buffer;
	private int pos;
	protected List<String> stringTable = new ArrayList<>(64);
	protected List<Object> objectTable = new ArrayList<>(64);
	protected List<TraitsInfo> traitsTable = new ArrayList<>(64);

	Amqqq() {
	}

	void reset(byte[] data) {
		this.buffer = data;
		this.pos = 0;
	}

	private byte readByte() {
		return buffer[pos++];
	}

	private void readFully(byte[] bytearr, int start, int utflen) {
		System.arraycopy(buffer, pos, bytearr, 0, utflen);
		pos += utflen;
	}

	private long readLong() {
		return ((((long) readByte() & 0xff) << 56) | (((long) readByte() & 0xff) << 48) | (((long) readByte() & 0xff) << 40)
				| (((long) readByte() & 0xff) << 32) | (((long) readByte() & 0xff) << 24) | (((long) readByte() & 0xff) << 16)
				| (((long) readByte() & 0xff) << 8) | (((long) readByte() & 0xff) << 0));
	}

	public Object readObject() {
		return readObjectValue(this.readByte());
	}

	/**
	 * AMF 3 represents smaller integers with fewer bytes using the most
	 * significant bit of each byte. The worst case uses 32-bits to represent a
	 * 29-bit number, which is what we would have done with no compression.
	 * 
	 * <pre>
	 * 0x00000000 - 0x0000007F : 0xxxxxxx
	 * 0x00000080 - 0x00003FFF : 1xxxxxxx 0xxxxxxx
	 * 0x00004000 - 0x001FFFFF : 1xxxxxxx 1xxxxxxx 0xxxxxxx
	 * 0x00200000 - 0x3FFFFFFF : 1xxxxxxx 1xxxxxxx 1xxxxxxx xxxxxxxx
	 * 0x40000000 - 0xFFFFFFFF : throw range exception
	 * </pre>
	 * 
	 * @return A int capable of holding an unsigned 29 bit integer.
	 */
	private int readUInt29() {
		int value;
		// Each byte must be treated as unsigned
		int b = this.readByte() & 0xFF;
		if (b < 128) {
			return b;
		}
		value = (b & 0x7F) << 7;
		b = this.readByte() & 0xFF;
		if (b < 128) {
			return (value | b);
		}

		value = (value | (b & 0x7F)) << 7;
		b = this.readByte() & 0xFF;
		if (b < 128) {
			return (value | b);
		}

		value = (value | (b & 0x7F)) << 8;
		b = this.readByte() & 0xFF;
		return (value | b);
	}

	private String readString() {
		int ref = readUInt29();
		if ((ref & 1) == 0) {// 前面有引用，直接把引用里的值拿来
			return getStringReference(ref >> 1);
		} else {
			int len = (ref >> 1);
			if (0 == len) {
				return EMPTY_STRING;
			}
			String str = readUTF(len);
			// 字符串需要缓存备用
			stringTable.add(str);
			return str;
		}
	}

	private char[] tempCharArray = null;
	private byte[] tempByteArray = null;

	final char[] getTempCharArray(int capacity) {
		char[] result = this.tempCharArray;
		if ((result == null) || (result.length < capacity)) {
			result = new char[capacity * 2];
			tempCharArray = result;
		}
		return result;
	}

	final byte[] getTempByteArray(int capacity) {
		byte[] result = this.tempByteArray;
		if ((result == null) || (result.length < capacity)) {
			result = new byte[capacity * 2];
			tempByteArray = result;
		}
		return result;
	}

	private String readUTF(int utflen) {
		char[] charr = getTempCharArray(utflen);
		byte[] bytearr = getTempByteArray(utflen);
		int c, char2, char3;
		int count = 0;
		int chCount = 0;

		this.readFully(bytearr, 0, utflen);

		while (count < utflen) {
			c = (int) bytearr[count] & 0xff;
			switch (c >> 4) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				/* 0xxxxxxx */
				count++;
				charr[chCount] = (char) c;
				break;
			case 12:
			case 13:
				/* 110x xxxx 10xx xxxx */
				count += 2;
				if (count > utflen)
					throw new UTFDataFormatException();
				char2 = (int) bytearr[count - 1];
				if ((char2 & 0xC0) != 0x80)
					throw new UTFDataFormatException();
				charr[chCount] = (char) (((c & 0x1F) << 6) | (char2 & 0x3F));
				break;
			case 14:
				/* 1110 xxxx 10xx xxxx 10xx xxxx */
				count += 3;
				if (count > utflen)
					throw new UTFDataFormatException();
				char2 = (int) bytearr[count - 2];
				char3 = (int) bytearr[count - 1];
				if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80))
					throw new UTFDataFormatException();
				charr[chCount] = (char) (((c & 0x0F) << 12) | ((char2 & 0x3F) << 6) | ((char3 & 0x3F) << 0));
				break;
			default:
				/* 10xx xxxx, 1111 xxxx */
				throw new UTFDataFormatException();
			}
			chCount++;
		}
		// The number of chars produced may be less than utflen
		return new String(charr, 0, chCount);
	}

	private Object readObjectValue(int type) {
		Object value = null;
		switch (type) {
		case kIntegerType:
			value = (readUInt29() << 3) >> 3;
			break;
		case kStringType:
			value = readString();
			break;
		case kArrayType:
			value = readArray();
			break;
		case kObjectType:
			value = readScriptObject();
			break;
		case kFalseType:
			value = Boolean.FALSE;

			break;

		case kTrueType:
			value = Boolean.TRUE;

			break;

		case kDoubleType:
			long x = readLong();
			value = new Double(Double.longBitsToDouble(x));

			break;

		case kUndefinedType:
			break;

		case kNullType:

			break;
		case kByteArrayType:
			value = readByteArray();
			break;
		default:
			// Unknown object type tag {type}
			System.err.println(Arrays.toString(buffer));
			UnknownTypeException ex = new UnknownTypeException("未解析的类型，" + type);
			throw ex;
		}

		return value;
	}

	protected byte[] readByteArray() {
		int ref = readUInt29();

		if ((ref & 1) == 0) {
			return new byte[] {};// (byte[])getObjectReference(ref >> 1);
		} else {
			int len = (ref >> 1);

			byte[] ba = new byte[len];

			// Remember byte array object
			// objectTable.add(ba);

			readFully(ba, 0, len);

			return ba;
		}
	}

	/**
	 * @exclude
	 */
	protected Object readArray() {
		int ref = readUInt29();
		int len = (ref >> 1);
		// First, look for any string based keys. If any
		// non-ordinal indices were used, or if the Array is
		// sparse, we represent the structure as a Map.
		for (;;) {
			String name = readString();
			if (name == null || name.length() == 0)
				break;
		}
		// New Flex 2+ behavior is to return Object[] for AS3 Array
		Object array = new Object[len];

		// Remember native Object[]
		objectTable.add(array);

		for (int i = 0; i < len; i++) {
			Object item = readObject();
			Array.set(array, i, item);
		}
		return array;
	}

	private String getStringReference(int ref) {
		return stringTable.get(ref);
	}

	/**
	 * @exclude
	 */
	protected Object getObjectReference(int ref) {
		return objectTable.get(ref);
	}

	/**
	 * @exclude
	 */
	protected TraitsInfo getTraitReference(int ref) {
		return traitsTable.get(ref);
	}

	/**
	 * @exclude
	 */
	protected TraitsInfo readTraits(int ref) {
		if ((ref & 3) == 1) {
			return getTraitReference(ref >> 2);
		} else {
			boolean externalizable = ((ref & 4) == 4);
			boolean dynamic = ((ref & 8) == 8);
			int count = (ref >> 4); /* uint29 */
			String className = readString();
			TraitsInfo ti = new TraitsInfo(className, dynamic, externalizable, count);
			// Remember Trait Info
			traitsTable.add(ti);
			for (int i = 0; i < count; i++) {
				String propName = readString();
				ti.addProperty(propName);
			}
			return ti;
		}
	}

	/**
	 * @exclude
	 */
	protected Object readScriptObject() {
		int ref = readUInt29();

		if ((ref & 1) == 0) {
			return getObjectReference(ref >> 1);
		} else {
			TraitsInfo ti = readTraits(ref);
			HashMap<String, Object> object = new HashMap<>();
			objectTable.add(object);
			int len = ti.getProperties().size();
			for (int i = 0; i < len; i++) {
				String propName = ti.getProperty(i);
				Object value = readObject();
				object.put(propName, value);
			}
			if (ti.isDynamic()) {
				for (;;) {
					String name = readString();
					if (name == null || name.length() == 0)
						break;

					Object value = readObject();
					object.put(name, value);
				}
			}
			return object;
		}
	}

	public void reset() {
		this.buffer = null;
		this.pos = 0;
		this.stringTable.clear();
		this.objectTable.clear();
		this.traitsTable.clear();
	}
}