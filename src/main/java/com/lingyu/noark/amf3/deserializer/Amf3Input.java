package com.lingyu.noark.amf3.deserializer;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lingyu.noark.amf3.parser.AMF3Constants;
import com.lingyu.noark.amf3.parser.AbstractInstanciator;
import com.lingyu.noark.amf3.parser.ActionScriptClassDescriptor;
import com.lingyu.noark.amf3.parser.DefaultActionScriptClassDescriptor;
import com.lingyu.noark.amf3.parser.Externalizer;

public class Amf3Input implements AMF3Constants {
	private byte[] buffer;
	private int pos;
	protected final List<String> storedStrings = new ArrayList<String>();
	protected final List<Object> storedObjects = new ArrayList<Object>();
	protected final List<ActionScriptClassDescriptor> storedClassDescriptors = new ArrayList<ActionScriptClassDescriptor>();

	public void reset() {
		this.buffer = null;
		this.pos = 0;
		this.storedStrings.clear();
		this.storedObjects.clear();
		this.storedClassDescriptors.clear();
	}

	public void reset(byte[] data) {
		this.buffer = data;
		this.pos = 0;
	}

	public Object readObject() {
		int type = readAMF3Integer();
		return readObject(type);
	}

	protected Object readObject(int type) {
		switch (type) {
		case AMF3_UNDEFINED: // 0x00;
		case AMF3_NULL: // 0x01;
			return null;
		case AMF3_BOOLEAN_FALSE: // 0x02;
			return Boolean.FALSE;
		case AMF3_BOOLEAN_TRUE: // 0x03;
			return Boolean.TRUE;
		case AMF3_INTEGER: // 0x04;
			int i = readAMF3Integer();
			i = (i << 3) >> 3;
			return Integer.valueOf(i);
		case AMF3_NUMBER: // 0x05;
			return readAMF3Double();
		case AMF3_STRING: // 0x06;
			return readAMF3String();
		case AMF3_DATE: // 0x08;
			return readAMF3Date();
		case AMF3_ARRAY: // 0x09;
			return readAMF3Array();
		case AMF3_OBJECT: // 0x0A;
			return readAMF3Object();
		case AMF3_BYTEARRAY: // 0x0C;
			return readAMF3ByteArray();
		default:
			throw new IllegalArgumentException("未实现的类型: " + type);
		}
	}

	protected byte[] readAMF3ByteArray() {
		byte[] result = null;

		int type = readAMF3Integer();
		if ((type & 0x01) == 0) // stored object.
			result = (byte[]) getFromStoredObjects(type >> 1);
		else {
			result = readBytes(type >> 1);
			addToStoredObjects(result);
		}

		return result;
	}

	protected byte[] readBytes(int count) {
		byte[] bytes = new byte[count];
		readFully(bytes);
		return bytes;
	}

	protected Object readAMF3Object() {
		Object result = null;
		int type = readAMF3Integer();
		if ((type & 0x01) == 0) // stored object.
			result = getFromStoredObjects(type >> 1);
		else {
			boolean inlineClassDef = (((type >> 1) & 0x01) != 0);
			// read class decriptor.
			ActionScriptClassDescriptor desc = null;
			if (inlineClassDef) {
				int propertiesCount = type >> 4;

				byte encoding = (byte) ((type >> 2) & 0x03);

				String className = readAMF3String();

				desc = new DefaultActionScriptClassDescriptor(className, encoding);
				addToStoredClassDescriptors(desc);

				for (int i = 0; i < propertiesCount; i++) {
					String name = readAMF3String();
					desc.defineProperty(name);
				}
			} else
				desc = getFromStoredClassDescriptors(type >> 2);

			int objectEncoding = desc.getEncoding();

			// Find externalizer and create Java instance.
			Externalizer externalizer = desc.getExternalizer();
			if (externalizer != null) {
				try {
					// result = externalizer.newInstance(desc.getType(), this);
				} catch (Exception e) {
					throw new RuntimeException("Could not instantiate type: " + desc.getType(), e);
				}
			} else
				result = desc.newJavaInstance();

			int index = addToStoredObjects(result);

			// read object content...
			if ((objectEncoding & 0x01) != 0) {
				// externalizer.
				if (externalizer != null) {
					// try {
					// //externalizer.readExternal(result, this);
					// } catch (IOException e) {
					// throw e;
					// } catch (Exception e) {
					// throw new
					// RuntimeException("Could not read externalized object: " +
					// result, e);
					// }
				}
				// legacy externalizable.
				else {
					try {
						// ((Externalizable) result).readExternal(this);
					} catch (Exception e) {
						throw new RuntimeException("Could not read externalizable object: " + result, e);
					}
				}
			} else {
				// defined values...
				if (desc.getPropertiesCount() > 0) {
					for (int i = 0; i < desc.getPropertiesCount(); i++) {
						byte vType = readByte();
						Object value = readObject(vType);
						desc.setPropertyValue(i, result, value);
					}
				}

				// dynamic values...
				if (objectEncoding == 0x02) {
					while (true) {
						String name = readAMF3String();
						if (name.length() == 0)
							break;
						byte vType = readByte();
						Object value = readObject(vType);
						desc.setPropertyValue(name, result, value);
					}
				}
			}

			if (result instanceof AbstractInstanciator) {
				try {
					result = ((AbstractInstanciator<?>) result).resolve();
				} catch (Exception e) {
					throw new RuntimeException("Could not instantiate object: " + result, e);
				}
				setStoredObject(index, result);
			}
		}

		return result;
	}

	protected ActionScriptClassDescriptor getFromStoredClassDescriptors(int index) {
		ActionScriptClassDescriptor desc = storedClassDescriptors.get(index);
		return desc;
	}

	protected void setStoredObject(int index, Object o) {
		storedObjects.set(index, o);
	}

	protected Object readAMF3Array() {
		Object result = null;

		int type = readAMF3Integer();
		if ((type & 0x01) == 0) // stored array.
			result = getFromStoredObjects(type >> 1);
		else {
			final int size = type >> 1;

			String key = readAMF3String();
			if (key.length() == 0) {
				Object[] objects = new Object[size];
				addToStoredObjects(objects);

				for (int i = 0; i < size; i++)
					objects[i] = readObject();

				result = objects;
			} else {
				Map<Object, Object> map = new LinkedHashMap<Object, Object>();
				addToStoredObjects(map);

				while (key.length() > 0) {
					map.put(key, readObject());
					key = readAMF3String();
				}
				for (int i = 0; i < size; i++)
					map.put(Integer.valueOf(i), readObject());

				result = map;
			}
		}

		return result;
	}

	private byte readByte() {
		return readUnsignedByte();
	}

	public final byte readUnsignedByte() {
		return buffer[pos++];
	}

	protected int readAMF3Integer() {
		// int result = 0;
		//
		// int n = 0;
		// int b = readUnsignedByte();
		// while ((b & 0x80) != 0 && n < 3) {
		// result <<= 7;
		// result |= (b & 0x7f);
		// b = readUnsignedByte();
		// n++;
		// }
		// if (n < 3) {
		// result <<= 7;
		// result |= b;
		// } else {
		// result <<= 8;
		// result |= b;
		// if ((result & 0x10000000) != 0)
		// result |= 0xe0000000;
		// }
		//
		// return result;
		int value;

		// Each byte must be treated as unsigned
		int b = buffer[pos++] & 0xFF;

		if (b < 128) {
			return b;
		}

		value = (b & 0x7F) << 7;
		b = buffer[pos++] & 0xFF;

		if (b < 128) {
			return (value | b);
		}

		value = (value | (b & 0x7F)) << 7;
		b = buffer[pos++] & 0xFF;

		if (b < 128) {
			return (value | b);
		}

		value = (value | (b & 0x7F)) << 8;
		b = buffer[pos++] & 0xFF;

		value |= b;

		return value;
	}

	private long readLong() {
		return ((((long) buffer[pos++] & 0xff) << 56) | (((long) buffer[pos++] & 0xff) << 48) | (((long) buffer[pos++] & 0xff) << 40)
				| (((long) buffer[pos++] & 0xff) << 32) | (((long) buffer[pos++] & 0xff) << 24) | (((long) buffer[pos++] & 0xff) << 16)
				| (((long) buffer[pos++] & 0xff) << 8) | (((long) buffer[pos++] & 0xff) << 0));
	}

	public final double readDouble() {
		return Double.longBitsToDouble(readLong());
	}

	protected Double readAMF3Double() {
		double d = readDouble();
		Double result = (Double.isNaN(d) ? null : Double.valueOf(d));
		return result;
	}

	private void readFully(byte[] bytearr) {
		System.arraycopy(buffer, pos, bytearr, 0, bytearr.length);
		pos += bytearr.length;
	}

	protected String readAMF3String() {
		String result = null;

		int type = readAMF3Integer();
		if ((type & 0x01) == 0) // stored string
			result = getFromStoredStrings(type >> 1);
		else {
			int length = type >> 1;

			if (length > 0) {

				byte[] utfBytes = new byte[length];
				char[] utfChars = new char[length];

				readFully(utfBytes);

				int c, c2, c3, iBytes = 0, iChars = 0;
				while (iBytes < length) {
					c = utfBytes[iBytes++] & 0xFF;
					if (c <= 0x7F)
						utfChars[iChars++] = (char) c;
					else {
						switch (c >> 4) {
						case 12:
						case 13:
							c2 = utfBytes[iBytes++];
							// if ((c2 & 0xC0) != 0x80)
							// throw new
							// UTFDataFormatException("Malformed input around byte "
							// + (iBytes - 2));
							utfChars[iChars++] = (char) (((c & 0x1F) << 6) | (c2 & 0x3F));
							break;
						case 14:
							c2 = utfBytes[iBytes++];
							c3 = utfBytes[iBytes++];
							// if (((c2 & 0xC0) != 0x80) || ((c3 & 0xC0) !=
							// 0x80))
							// throw new
							// UTFDataFormatException("Malformed input around byte "
							// + (iBytes - 3));
							utfChars[iChars++] = (char) (((c & 0x0F) << 12) | ((c2 & 0x3F) << 6) | ((c3 & 0x3F) << 0));
							break;
						default:
							// throw new
							// UTFDataFormatException("Malformed input around byte "
							// + (iBytes - 1));
						}
					}
				}
				result = new String(utfChars, 0, iChars);

				addToStoredStrings(result);
			} else
				result = "";
		}
		return result;
	}

	protected String getFromStoredStrings(int index) {
		String s = storedStrings.get(index);
		return s;
	}

	protected void addToStoredStrings(String s) {
		storedStrings.add(s);
	}

	protected Date readAMF3Date() {
		Date result = null;

		int type = readAMF3Integer();
		if ((type & 0x01) == 0) // stored Date
			result = (Date) getFromStoredObjects(type >> 1);
		else {
			result = new Date((long) readDouble());
			addToStoredObjects(result);
		}

		return result;
	}

	protected Object getFromStoredObjects(int index) {
		return storedObjects.get(index);
	}

	protected void addToStoredClassDescriptors(ActionScriptClassDescriptor desc) {
		storedClassDescriptors.add(desc);
	}

	protected int addToStoredObjects(Object o) {
		int index = storedObjects.size();
		storedObjects.add(o);
		return index;
	}

}
