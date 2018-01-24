package com.mokylin.cabal.common.io;

import java.util.UUID;

/**
 * @description 一个简单的替换加密算法，实现了编码、解码函数对
 * @author hehj 2011-11-2 下午2:08:25
 */
public class SimpleEncrypt {

	private String ticket;// "NITAMAHAIZHENGEINIPOLE";
	private byte[] ticketBytes;// = ticket.getBytes();
	private int ticketLength;// = ticketBytes.length;
	private int ticketIndex = 0;
	private int deTicketIndex = 0;

	private String key;
	private byte[] keyBytes;
	private int keyLength;
	private int keyIndex;
	private int deKeyIndex;

	/**
	 * @param key
	 *            明钥
	 * @param ticket
	 *            密钥
	 */
	public SimpleEncrypt(String key, String ticket) {

		if (null == key || "".equals(key))
			throw new RuntimeException("key must be not null or empty.");
		if (null == ticket || "".equals(ticket))
			throw new RuntimeException("ticket must be not null or empty.");

		this.ticket = ticket;
		this.ticketBytes = this.ticket.getBytes();
		this.ticketLength = this.ticketBytes.length;
		this.ticketIndex = 0;
		this.deTicketIndex = 0;

		this.key = key;
		this.keyBytes = this.key.getBytes();
		this.keyLength = this.keyBytes.length;
		this.keyIndex = 0;
		this.deKeyIndex = 0;
	}

	/**
	 * 编码
	 * 
	 * @param data
	 * 
	 */
	public byte[] encode(byte[] dataBytes) {

		int dataLength = dataBytes.length;

		for (int i = 0; i < dataLength; i++) {
			int tkValue = ticketBytes[ticketIndex];
			ticketIndex = ++ticketIndex % ticketLength;

			int keyValue = keyBytes[keyIndex];
			keyIndex = ++keyIndex % keyLength;

			dataBytes[i] = (byte) ((dataBytes[i] + tkValue - keyValue));
		}

		return dataBytes;
	}

	/**
	 * 解码
	 * 
	 * @param data
	 * 
	 */
	public byte[] decode(byte[] dataBytes) {

		int dataLength = dataBytes.length;
		// this.deKeyIndex =0;
		// this.deTicketIndex =0;
		for (int i = 0; i < dataLength; i++) {
			int tkValue = ticketBytes[deTicketIndex];
			deTicketIndex = ++deTicketIndex % ticketLength;

			int keyValue = keyBytes[deKeyIndex];
			deKeyIndex = ++deKeyIndex % keyLength;

			dataBytes[i] = (byte) ((dataBytes[i] - tkValue + keyValue));
		}

		return dataBytes;
	}

	public static String createKey() {

		return UUID.randomUUID().toString();
		// System.out.println(ret);
		// return "09e04227-2183-42e7-bc16-07f79b5d3cfc";
	}

	public String getTicket() {
		return ticket;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
