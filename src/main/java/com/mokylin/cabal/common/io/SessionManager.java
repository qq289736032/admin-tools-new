package com.mokylin.cabal.common.io;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lingyu.noark.amf3.Amf3;

public class SessionManager {
	private static final Logger logger = LogManager.getLogger(SessionManager.class);

	private SessionManager() {
	}

	public static SessionManager getInstance() {
		return InstanceHolder.INSTANCE;
	}

	private static class InstanceHolder {
		private static final SessionManager INSTANCE = new SessionManager();
	}

	private ConcurrentMap<Channel, Session> sessionMap = new ConcurrentHashMap<>();
	private ConcurrentMap<String, Session> userMap = new ConcurrentHashMap<>();

	public synchronized Session addSession(Channel channel) {
		String sessionId = DefaultChannelId.newInstance().asLongText();
		Session result = new Session(channel, sessionId);
		logger.info("create session {}", sessionId);
		// sessionIdMap.put(sessionId, result);
		sessionMap.put(channel, result);
		return result;
	}

	public Session getSession4User(String userId) {
		return userMap.get(userId);
	}

	public void addSession4User(String pid, String userId, Session session) {
		session.setUserId(userId);
		session.setPid(pid);
		userMap.put(userId, session);
	}

	public synchronized String removeSession(Channel channel) {
		String ret = "0";
		Session session = sessionMap.remove(channel);
		if (session != null) {
			userMap.remove(session.getUserId());
			ret = session.getUserId();
			logger.info("remove session {}", session.getId());
		}
		return ret;
	}

	public Session getSession(Channel channel) {
		return sessionMap.get(channel);
	}

	public Collection<Session> getOnlineUserList() {
		return sessionMap.values();
	}

	/**
	 * 全服的广播
	 * 
	 * @param roleId 排除自己广播
	 * 
	 * @param msg
	 */
	public void broadcast(long roleId, int type, Object[] msg) {
		Object[] array = new Object[] { type, msg };
		Collection<Session> list = sessionMap.values();
		byte[] content = Amf3.toBytes(array);
		for (Session session : list) {
			session.sendMsg(content);
		}
	}

	public void broadcast(List<String> list, int type, Object[] msg) {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		Object[] array = new Object[] { type, msg };
		byte[] content = Amf3.toBytes(array);
		for (String userId : list) {
			Session session = this.getSession4User(userId);
			if (session != null) {
				session.sendMsg(content);
			}

		}
	}

	public void broadcast(int type, Object[] msg) {
		this.broadcast(0, type, msg);
	}

	public void relayMsg(int command, String userId, Object[] message) {
		Session session = this.getSession4User(userId);
		if (null != session) {
			Object[] array = new Object[] { command, message };
			byte[] content = Amf3.toBytes(array);
			session.sendMsg(content);
		} else {
			logger.error("no session {}", message);
		}
	}
}
