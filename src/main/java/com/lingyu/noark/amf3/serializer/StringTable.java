package com.lingyu.noark.amf3.serializer;

import java.util.Arrays;


/**
 * 定制一个String索引类.
 * 
 * @author 小流氓<176543888@qq.com>
 */
public class StringTable {

	public static final int DEFAULT_TABLE_SIZE = 10;

	private final Entry[] buckets;
	private final int indexMask;

	public StringTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	public StringTable(int tableSize) {
		this.indexMask = tableSize - 1;
		this.buckets = new Entry[tableSize];
	}

	public final int get(String key) {
		final int bucket = System.identityHashCode(key) & indexMask;
		for (Entry entry = buckets[bucket]; entry != null; entry = entry.next) {
			if (key == entry.key) {
				return entry.value;
			}
		}
		return 0;
	}

	public boolean put(String key, int value) {
		size++;
		final int bucket = System.identityHashCode(key) & indexMask;
		// final int bucket = System.identityHashCode(key) & indexMask;
		for (Entry entry = buckets[bucket]; entry != null; entry = entry.next) {
			if (key == entry.key) {
				entry.value = value;
				return true;
			}
		}
		buckets[bucket] = new Entry(key, value, buckets[bucket]);
		return false;
	}

	private int size;

	public int size() {
		return size;
	}

	static final class Entry {
		final String key;
		int value;
		final Entry next;

		Entry(String key, int value, Entry next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	public void clear() {
		size = 0;
		Arrays.fill(buckets, null);
	}
}