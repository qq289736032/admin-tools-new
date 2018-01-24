package com.mokylin.cabal.init;

import com.mokylin.cabal.common.codec.Amf3Decoder;
import com.mokylin.cabal.common.codec.Amf3Encoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.UUID;

public class AdminServerChannelPipelineFactory extends ChannelInitializer<Channel> {
    // private ExecutionHandler executionHandler = new ExecutionHandler(new
    // OrderedMemoryAwareThreadPoolExecutor(16, 1048576, 1048576, 30L,
    // TimeUnit.SECONDS,
    // new GameThreadFactory("netty-executor")));

    public AdminServerChannelPipelineFactory() {

    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        String key = UUID.randomUUID().toString();
        MutableBoolean common = new MutableBoolean(true);
        //SimpleEncrypt encrypt = new SimpleEncrypt(key, ConfigConstants.TICKET);
        ChannelPipeline p = ch.pipeline();
        //p.addLast("idle", new IdleStateHandler(0, 0, 0));
        // 既然消息会被路由处理，这里就没必要通过ExecutionHandler线程池来异步处理ChannelHandler链
        // ret.addLast("executor", executionHandler);
        p.addLast("decoder", new Amf3Decoder(common));
        p.addLast("encoder", new Amf3Encoder(common));
        p.addLast("handle", new AdminServerHandler());
    }
}