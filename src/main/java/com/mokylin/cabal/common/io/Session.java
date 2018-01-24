package com.mokylin.cabal.common.io;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lingyu.noark.amf3.Amf3;

public class Session {
	private static final Logger logger = LogManager.getLogger(Session.class);
	public static final int STATUS_NORMAL = 1;
	public static final int STATUS_JICHU = 2;
	private Channel channel;
	private String userId = "0";
	private String pid;
	private String userName;
	private String id;
	private int status = STATUS_NORMAL;
	private String ip;

	public Session(Channel channel, String sessionId) {
		this.channel = channel;
		this.id = sessionId;
	}

	// 注入第三方参数
	public Channel getChannel() {
		return channel;
	}

	public void sendMsg(byte[] content) {
		channel.writeAndFlush(content);
	}

	public void sendMsg(Object[] msg) {
		byte[] content = Amf3.toBytes(msg);
		this.sendMsg(content);
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String sessionId) {
		this.id = sessionId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void close() {
		if (channel.isActive()) {
			channel.close().awaitUninterruptibly();
		}
	}

	public String getClientIp() {
		if (ip == null) {
			InetSocketAddress remoteAddr = (InetSocketAddress) this.getChannel().remoteAddress();
			ip = remoteAddr.getAddress().getHostAddress();
		}
		return ip;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Session))
			return false;

		Session session = (Session) o;

		return id == session.id;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
