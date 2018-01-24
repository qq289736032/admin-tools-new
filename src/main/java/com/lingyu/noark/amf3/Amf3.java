package com.lingyu.noark.amf3;

import com.lingyu.noark.amf3.deserializer.Amf3Deserializer;
import com.lingyu.noark.amf3.exception.UnknownTypeException;
import com.lingyu.noark.amf3.serializer.Amf3Serializer;

/**
 * AMF3序列化工具类.
 * <p>
 * 这个工具类只实现Object[]的编码，只为灵娱历史项目提供服务.
 * 
 * @author 小流氓<176543888@qq.com>
 */
public final class Amf3 {

	// 私有化默认的构造函数
	private Amf3() {
	}

	/**
	 * 将一个Object数据转化成ASM3编码的字节数组.
	 * 
	 * @param object 此对象，必需是一些简单类型的东东.
	 * @return ASM3编码的字节数组.
	 */
	public static byte[] toBytes(Object object) {
		try (Amf3Serializer serializer = Amf3Serializer.get()) {
			return serializer.write(object);
		}
	}

	/**
	 * 把一堆字节以AMF3格式编码成对象.
	 * 
	 * @param bytes 字节数组。
	 * @return AMF3格式编码成对象
	 */
	public static final Object parse(byte[] bytes) {
		try (Amf3Deserializer parser = Amf3Deserializer.get()) {
			return parser.read(bytes);
		} catch (Exception e) {// 对解析失败的字节组数，需要记录处理.
			throw new UnknownTypeException("noark-amf3解析数据时出错啦, 火速联系那个小小流氓，异常数据:", bytes, e);
		}
	}
}