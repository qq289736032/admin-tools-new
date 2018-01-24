package com.mokylin.cabal.init;

import com.mokylin.cabal.common.config.TCPConfig;
import com.mokylin.cabal.common.service.ServiceException;
import com.mokylin.cabal.common.utils.SpringContextHolder;
import com.mokylin.cabal.modules.game.service.ChatMonitorManager;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/3/31 17:35
 * 项目: admin-tools
 */
public class TcpServerInitializerImpl implements AdminInitializer {

    private static final Logger logger = LoggerFactory.getLogger(TcpServerInitializerImpl.class);

    TCPConfig tcpConfig = SpringContextHolder.getBean(TCPConfig.class);

    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    @Override
    public void init() {
        logger.info("初始化TCP服务开始");
        // Configure the server.
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.SO_REUSEADDR, true);
        // Set up the pipeline factory.
        bootstrap.childHandler(new AdminServerChannelPipelineFactory());
        // Bind and start to accept incoming connections.
        try {
            bootstrap.bind(new InetSocketAddress(tcpConfig.getPort())).sync();
            logger.info("admin tcp server start on {}", tcpConfig.getPort());
            logger.info("初始化TCP服务完毕");
        } catch (Exception e) {
            logger.info("初始化TCP服务失败");
            throw new ServiceException(e);
        }
    }

    @Override
    public void destroy() {
        ChatMonitorManager manager = SpringContextHolder.getBean(ChatMonitorManager.class);
        manager.closeAllMonitors();
        
        try{
	        // Shut down all event loops to terminate all threads.
	 		bossGroup.shutdownGracefully();
	 		// Wait until all threads are terminated.
	 		bossGroup.terminationFuture().sync();
	
	 		// Shut down all event loops to terminate all threads.
	 		workerGroup.shutdownGracefully();
	 		// Wait until all threads are terminated.
	 		workerGroup.terminationFuture().sync();
        }catch(Exception e){
        	logger.error("", e);
        }
    }
}
