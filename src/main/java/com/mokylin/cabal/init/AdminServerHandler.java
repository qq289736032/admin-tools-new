package com.mokylin.cabal.init;

import com.mokylin.cabal.common.io.Session;
import com.mokylin.cabal.common.io.SessionManager;
import com.mokylin.cabal.common.service.ServiceException;
import com.mokylin.cabal.common.utils.SpringContextHolder;
import com.mokylin.cabal.modules.game.service.AdminProcessor;
import com.mokylin.cabal.modules.game.service.AdminProcessorImpl;
import com.mokylin.cabal.modules.game.service.ChatMonitorManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class AdminServerHandler extends SimpleChannelInboundHandler<Object[]> {
	private static final Logger logger = LogManager.getLogger(AdminServerHandler.class);
	private static AdminProcessor processor = SpringContextHolder.getBean(AdminProcessorImpl.class);
	private static ChatMonitorManager chatMonitorManager= SpringContextHolder.getBean(ChatMonitorManager.class);
	public AdminServerHandler() {
		logger.info("创建AdminServerHandler");
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object e) throws Exception {
		if (e instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) e;
			Session session = SessionManager.getInstance().getSession(ctx.channel());
			if (session != null) {
				logger.info("session={}", session.toString());
			} else {
				logger.info("session 为空");
			}
			logger.warn("close the channel: heartbeat {},type={}", ctx.channel(), event.state());
			ctx.close();
		}
	}

	/** 建立连接 channelConnected */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		logger.info("{} connect", channel);
		SessionManager.getInstance().addSession(channel);

 	}

	// /** 服务器主动关闭会触发该方法 */
	// @Override
	// public void closeRequested(ChannelHandlerContext ctx, ChannelStateEvent
	// e) throws Exception {
	// //
	// logger.info("服务器主动关闭连接  closeRequested:{} ", ctx.getChannel());
	// ctx.sendDownstream(e);
	// }
	//
	// /** 当本地远程客户机与本主机建立连接后，连接断开才会触发该方法,所以客户端关闭或者服务器主动关闭都会触发这个方法 */
	// public void channelDisconnected(ChannelHandlerContext ctx,
	// ChannelStateEvent e) throws Exception {
	// logger.info("{} channelDisconnected  ", ctx.getChannel());
	// }

	/** 断开连接 原先的channelClosed */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.info("{} channelClosed", ctx.channel());
		Session session = SessionManager.getInstance().getSession(ctx.channel());
		if (session == null) {
			logger.warn("{} channel closed: no session id founded", ctx.channel());
		} else {
			String userId=SessionManager.getInstance().removeSession(ctx.channel());
			chatMonitorManager.remove4ObserverList(userId);
			logger.info("{}/{} closed", ctx.channel(), session.getId());
		}
	}

	/** messageReceived */
	@Override
	public void channelRead0(ChannelHandlerContext ctx, Object[] msg) {
		try {
			int msgType = (int) msg[0];
			Object instance = msg[1];
			Object[] content = null;
			if (instance != null) {
				if (instance instanceof Object[]) {
					content = (Object[]) instance;
				} else {
					content = new Object[] { instance };
					logger.warn("需要客户端调整的 msgType={}", msgType);
				}
			} else {
				content = new Object[] {};
				logger.warn("需要客户端调整的 msgType={}", msgType);
			}
			processMsg(ctx.channel(), msgType, content);
		} catch (ServiceException e) {
			logger.error("processMsg failed: " + e.getMessage(), e);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
		logger.error(e.getMessage(), e);
		if (ctx.channel().isActive()) {
			ctx.close();
		}
	}

	private void processMsg(Channel channel, int msgType, Object[] content) {
		Session session = SessionManager.getInstance().getSession(channel);
		processor.dispatch(session, msgType, content);
	}
}