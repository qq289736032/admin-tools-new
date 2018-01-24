package com.lingyu.noark.amf3.serializer;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.lingyu.noark.amf3.Amf3Type;
import com.lingyu.noark.amf3.exception.MessageException;

/**
 * 实现一个AMF3编码的Buffer类.
 * 
 * @author 小流氓<176543888@qq.com>
 */
public class Amf3Output implements Amf3Type {
	private static final int DEFAULT_BUFFER_SIZE = 512;
	// 几次测试，好像都是HashMap赢了...
	private HashMap<String, Integer> stringTable = new HashMap<>();
	private IdentityHashMap<Object, Integer> objectTable = new IdentityHashMap<>();
	private byte[] buffer;
	private int limit;
	private int pos;

	public Amf3Output() {
		limit = DEFAULT_BUFFER_SIZE;
		buffer = new byte[limit];
		pos = 0;
	}

	/**
	 * 扩容处理，方法名来自ArrayList类.
	 * <p>
	 * 每次扩的量为DEFAULT_BUFFER_SIZE<br>
	 * 如果不满足最小需求就按minCapacity来扩容
	 * 
	 * @param minCapacity 本次扩容的最小需求.
	 */
	private void ensureCapacity(int minCapacity) {
		limit += DEFAULT_BUFFER_SIZE >= minCapacity ? DEFAULT_BUFFER_SIZE : minCapacity;
		byte[] bytes = new byte[limit];
		System.arraycopy(buffer, 0, bytes, 0, pos);
		this.buffer = bytes;
	}

	/**
	 * 写一个字节
	 */
	private void writeRawByte(final byte value) {
		if (pos == limit) {
			this.ensureCapacity(0);
		}
		buffer[pos++] = value;
	}

	/**
	 * 写一个字节，由一个整数值表示。
	 */
	private void writeRawByte(final int value) {
		this.writeRawByte((byte) value);
	}

	/**
	 * 写一个字节
	 */
	private void writeRawLong(final long v) {
		int length = 8;
		if (length > limit - pos) {
			// 需求大于剩余的容量，扩容去吧，带上最小的需求
			this.ensureCapacity(length);
		}
		buffer[pos++] = (byte) (v >>> 56);
		buffer[pos++] = (byte) (v >>> 48);
		buffer[pos++] = (byte) (v >>> 40);
		buffer[pos++] = (byte) (v >>> 32);
		buffer[pos++] = (byte) (v >>> 24);
		buffer[pos++] = (byte) (v >>> 16);
		buffer[pos++] = (byte) (v >>> 8);
		buffer[pos++] = (byte) (v >>> 0);
	}

	/**
	 * 写入字节数组.
	 * 
	 * @param value 字节数组
	 */
	private void writeRawBytes(final byte[] value) {
		int length = value.length;
		if (length > limit - pos) {
			// 需求大于剩余的容量，扩容去吧，带上最小的需求
			this.ensureCapacity(length);
		}
		System.arraycopy(value, 0, buffer, pos, length);
		pos += length;
	}

	private void writeRawBytes(final byte[] value, int start, int length) {
		if (length > limit - pos) {
			// 需求大于剩余的容量，扩容去吧，带上最小的需求
			this.ensureCapacity(length);
		}
		System.arraycopy(value, 0, buffer, pos, length);
		pos += length;
	}

	private boolean byReference(String s) {
		Integer ref = stringTable.get(s);
		if (ref != null) {
			writeUInt29(ref << 1);
		} else {
			stringTable.put(s, stringTable.size());
		}
		return (ref != null);
	}

	private void writeUInt29(int ref) {
		// Represent smaller integers with fewer bytes using the most
		// significant bit of each byte. The worst case uses 32-bits
		// to represent a 29-bit number, which is what we would have
		// done with no compression.

		// 0x00000000 - 0x0000007F : 0xxxxxxx
		// 0x00000080 - 0x00003FFF : 1xxxxxxx 0xxxxxxx
		// 0x00004000 - 0x001FFFFF : 1xxxxxxx 1xxxxxxx 0xxxxxxx
		// 0x00200000 - 0x3FFFFFFF : 1xxxxxxx 1xxxxxxx 1xxxxxxx xxxxxxxx
		// 0x40000000 - 0xFFFFFFFF : throw range exception
		if (ref < 0x80) {
			// 0x00000000 - 0x0000007F : 0xxxxxxx
			this.writeRawByte(ref);
		} else if (ref < 0x4000) {
			// 0x00000080 - 0x00003FFF : 1xxxxxxx 0xxxxxxx
			this.writeRawByte(((ref >> 7) & 0x7F) | 0x80);
			this.writeRawByte(ref & 0x7F);
		} else if (ref < 0x200000) {
			// 0x00004000 - 0x001FFFFF : 1xxxxxxx 1xxxxxxx 0xxxxxxx
			this.writeRawByte(((ref >> 14) & 0x7F) | 0x80);
			this.writeRawByte(((ref >> 7) & 0x7F) | 0x80);
			this.writeRawByte(ref & 0x7F);
		} else if (ref < 0x40000000) {
			// 0x00200000 - 0x3FFFFFFF : 1xxxxxxx 1xxxxxxx 1xxxxxxx xxxxxxxx
			this.writeRawByte(((ref >> 22) & 0x7F) | 0x80);
			this.writeRawByte(((ref >> 15) & 0x7F) | 0x80);
			this.writeRawByte(((ref >> 8) & 0x7F) | 0x80);
			this.writeRawByte(ref & 0xFF);
		} else {
			// 0x40000000 - 0xFFFFFFFF : throw range exception
			throw new MessageException("Integer out of range: " + ref);
		}
	}

	public void writeAMFNull() {
		this.writeRawByte(kNullType);
	}

	public void writeAMFArray(Object o, Class<?> componentType) {
		if (componentType.isPrimitive()) {
			writePrimitiveArray(o);
		} else if (componentType.equals(Byte.class)) {
			writeAMFByteArray((Byte[]) o);
		} else if (componentType.equals(Character.class)) {
			writeCharArrayAsString((Character[]) o);
		} else {
			this.writeObjectArray((Object[]) o);
		}
	}

	/**
	 * @exclude
	 */
	protected void writeObjectArray(Object[] values) {
		this.writeRawByte(kArrayType);
		writeUInt29((values.length << 1) | 1);
		// Send an empty string to imply no named keys
		writeStringWithoutType(EMPTY_STRING);
		for (Object item : values) {
			writeObject(item);
		}
	}

	/**
	 * Serialize an Object using AMF3.
	 */
	public void writeObject(Object o) {
		// Null
		if (o == null) {
			this.writeAMFNull();
		}
		// String
		else if (o instanceof String || o instanceof Character) {
			writeAMFString(o.toString());
		}
		// 数字类型的
		else if (o instanceof Number) {
			if (o instanceof Integer || o instanceof Short || o instanceof Byte) {
				writeAMFInt(((Number) o).intValue());
			} else if ((o instanceof BigInteger || o instanceof BigDecimal)) {
				// Using double to write big numbers such as BigInteger or
				// BigDecimal can result in information loss so we write
				// them as String by default...
				writeAMFString(((Number) o).toString());
			} else {
				writeAMFDouble(((Number) o).doubleValue());
			}
		}
		// Boolean
		else if (o instanceof Boolean) {
			writeAMFBoolean(((Boolean) o).booleanValue());
			// }
			// We have a complex type...
			// else if (o instanceof Date) {
			// writeAMFDate((Date) o);
			// } else if (o instanceof Calendar) {
			// writeAMFDate(((Calendar) o).getTime());
		}
		// Data
		else if (o instanceof Date) {
			writeAMFDate((Date) o);
		} else {
			// We have an Object or Array type...
			Class<?> cls = o.getClass();

			if (o instanceof Map) {
				writeMapAsECMAArray((Map<?, ?>) o);
			} else if (o instanceof Collection) {
				writeObjectArray(((Collection<?>) o).toArray());
			} else if (cls.isArray()) {
				writeAMFArray(o, cls.getComponentType());
			} else {
				// Special Case: wrap RowSet in PageableRowSet for Serialization
				throw new MessageException("未实现的类型：" + cls.getName());
			}
		}
	}

	/**
	 * @exclude
	 */
	protected void writeAMFDate(Date d) {
		this.writeRawByte(kDateType);
		if (!byReference(d)) {
			// Write out an invalid reference
			writeUInt29(1);
			// Write the time as 64bit value in ms
			this.writeRawLong(Double.doubleToLongBits((double) d.getTime()));
		}
	}

	/**
	 * Attempts to serialize the object as a reference. If the object cannot be
	 * serialized as a reference, it is stored in the reference collection for
	 * potential future encounter.
	 * 
	 * @return Success/failure indicator as to whether the object could be
	 *         serialized as a reference.
	 * @exclude
	 */
	protected boolean byReference(Object o) {
		Object ref = objectTable.get(o);
		if (ref != null) {
			int refNum = ((Integer) ref).intValue();
			writeUInt29(refNum << 1);
		} else {
			objectTable.put(o, new Integer(objectTable.size()));
		}
		return (ref != null);
	}

	protected void writeMapAsECMAArray(Map<?, ?> map) {
		this.writeRawByte(kObjectType);
		// 流氓写法，肯定为HashMap了就不去动态计算了，直接写上值就OK了
		// writeUInt29(3 | (externalizable ? 4 : 0) | (dynamic ? 8 : 0) | (count
		// << 4));
		writeUInt29(3 | (map.size() << 4));
		writeStringWithoutType("");
		// Key
		for (Entry<?, ?> e : map.entrySet()) {
			writeStringWithoutType(e.getKey().toString());
		}
		for (Entry<?, ?> e : map.entrySet()) {
			writeObject(e.getValue());
		}
	}

	/**
	 * @exclude
	 */
	protected void writeAMFByteArray(byte[] ba) {
		this.writeRawByte(kByteArrayType);
		writeUInt29((ba.length << 1) | 1);
		this.writeRawBytes(ba);
	}

	/**
	 * @exclude
	 */
	protected void writeAMFByteArray(Byte[] ba) {
		this.writeRawByte(kByteArrayType);
		writeUInt29((ba.length << 1) | 1);
		for (Byte b : ba) {
			if (b == null) {
				this.writeRawByte(0);
			} else {
				this.writeRawByte(b.byteValue());
			}
		}
	}

	/**
	 * @exclude
	 */
	protected void writeAMFBoolean(boolean b) {
		this.writeRawByte(b ? kTrueType : kFalseType);
	}

	/**
	 * @exclude
	 */
	protected void writeAMFInt(int i) {
		if (i >= INT28_MIN_VALUE && i <= INT28_MAX_VALUE) {
			// i = ((i >> 3) & UINT29_MASK);
			i = i & UINT29_MASK; // Mask is 2^29 - 1
			this.writeRawByte(kIntegerType);
			writeUInt29(i);
		} else {
			// Promote large int to a double
			writeAMFDouble(i);
		}
	}

	protected void writeAMFDouble(double d) {
		this.writeRawByte(kDoubleType);
		this.writeRawLong(Double.doubleToLongBits(d));
	}

	/**
	 * Serialize an array of primitives.
	 * <p>
	 * Primitives include the following: boolean, char, double, float, long,
	 * int, short, byte
	 * </p>
	 * 
	 * @param obj An array of primitives
	 * @exclude
	 */
	protected void writePrimitiveArray(Object obj) {
		Class<?> aType = obj.getClass().getComponentType();
		if (aType.equals(Character.TYPE)) {
			char[] c = (char[]) obj;
			writeCharArrayAsString(c);
		} else if (aType.equals(Byte.TYPE)) {
			writeAMFByteArray((byte[]) obj);
		} else {
			this.writeRawByte(kArrayType);
			if (aType.equals(Boolean.TYPE)) {
				boolean[] b = (boolean[]) obj;
				writeUInt29((b.length << 1) | 1);
				writeStringWithoutType(EMPTY_STRING);

				for (int i = 0; i < b.length; i++) {
					writeAMFBoolean(b[i]);
				}
			} else if (aType.equals(Integer.TYPE) || aType.equals(Short.TYPE)) {
				// We have a primitive number, either an int or short
				// We write all of these as Integers...
				int length = Array.getLength(obj);

				// Write out an invalid reference, storing the length in the
				// unused 28-bits.
				writeUInt29((length << 1) | 1);
				// Send an empty string to imply no named keys
				writeStringWithoutType(EMPTY_STRING);
				for (int i = 0; i < length; i++) {
					int v = Array.getInt(obj, i);
					writeAMFInt(v);
				}
			} else {
				// We have a primitive number, either a double, float, or
				// long
				// We write all of these as doubles...
				int length = Array.getLength(obj);

				// Write out an invalid reference, storing the length in the
				// unused 28-bits.
				writeUInt29((length << 1) | 1);
				// Send an empty string to imply no named keys
				writeStringWithoutType(EMPTY_STRING);

				for (int i = 0; i < length; i++) {
					double v = Array.getDouble(obj, i);
					writeAMFDouble(v);
				}
			}
		}
	}

	/**
	 * @exclude
	 */
	protected void writeCharArrayAsString(Character[] ca) {
		int length = ca.length;
		char[] chars = new char[length];

		for (int i = 0; i < length; i++) {
			Character c = ca[i];
			if (c == null)
				chars[i] = 0;
			else
				chars[i] = ca[i].charValue();
		}
		writeCharArrayAsString(chars);
	}

	/**
	 * @exclude
	 */
	protected void writeCharArrayAsString(char[] ca) {
		String str = new String(ca);
		writeAMFString(str);
	}

	/**
	 * @exclude
	 */
	protected void writeAMFString(String s) {
		this.writeRawByte(kStringType);
		writeStringWithoutType(s);
	}

	protected void writeStringWithoutType(String s) {
		if (s.length() == 0) {
			// don't create a reference for the empty string,
			// as it's represented by the one byte value 1
			// len = 0, ((len << 1) | 1).
			writeUInt29(1);
			return;
		}
		if (!byReference(s)) {
			writeAMFUTF(s);
			return;
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

	public void writeAMFUTF(String s) {
		int strlen = s.length();
		int utflen = 0;
		int c, count = 0;

		char[] charr = getTempCharArray(strlen);
		s.getChars(0, strlen, charr, 0);

		for (int i = 0; i < strlen; i++) {
			c = charr[i];
			if (c <= 0x007F) {
				utflen++;
			} else if (c > 0x07FF) {
				utflen += 3;
			} else {
				utflen += 2;
			}
		}

		writeUInt29((utflen << 1) | 1);

		byte[] bytearr = getTempByteArray(utflen);

		for (int i = 0; i < strlen; i++) {
			c = charr[i];
			if (c <= 0x007F) {
				bytearr[count++] = (byte) c;
			} else if (c > 0x07FF) {
				bytearr[count++] = (byte) (0xE0 | ((c >> 12) & 0x0F));
				bytearr[count++] = (byte) (0x80 | ((c >> 6) & 0x3F));
				bytearr[count++] = (byte) (0x80 | ((c >> 0) & 0x3F));
			} else {
				bytearr[count++] = (byte) (0xC0 | ((c >> 6) & 0x1F));
				bytearr[count++] = (byte) (0x80 | ((c >> 0) & 0x3F));
			}
		}
		// out.write(bytearr, 0, utflen);
		this.writeRawBytes(bytearr, 0, utflen);
	}

	final byte[] getTempByteArray(int capacity) {
		byte[] result = this.tempByteArray;
		if ((result == null) || (result.length < capacity)) {
			result = new byte[capacity * 2];
			tempByteArray = result;
		}
		return result;
	}

	public void reset() {
		pos = 0;
		// this.tempByteArray = null;
		// this.tempCharArray = null;
		this.stringTable.clear();
		this.objectTable.clear();
	}

	public byte[] getBytes() {
		if (this.pos == 0) {
			return new byte[0];
		} else {
			byte[] retBytes = new byte[this.pos];
			System.arraycopy(this.buffer, 0, retBytes, 0, this.pos);
			return retBytes;
		}
	}
}