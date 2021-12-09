package com.bkhech.home.practice.socket.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author guowm
 * @date 2021/1/5
 * @description
 */
@Slf4j
public class NettyClient {

    private String host;
    private int port;
    private SocketChannel channel;

    public NettyClient(int port) {
        this.host = "127.0.0.1";
        this.port = port;
    }

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap()
                .group(workerGroup)
                // 设置IO模型为NIO
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                // 绑定连接初始化器
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("Connecting...");
                        ch.pipeline()
                                .addLast(new IdleStateHandler(30, 0, 0, TimeUnit.SECONDS))
                                //encoder
                                .addLast(new LengthFieldBasedFrameDecoder(4096, 0, 4, 0, 4, true))
                                //encoder
                                .addLast(new LengthFieldPrepender(4))

                                .addLast(new StringDecoder(CharsetUtil.UTF_8))
                                .addLast(new StringEncoder(CharsetUtil.UTF_8))

                                //handler
                                .addLast(new LoggingHandler());
                    }
                });
        // 发起异步连接请求，绑定连接端口和host信息
        ChannelFuture channelFuture = b.connect(host, port).sync();
        channelFuture.addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("Connect server success.");

            } else {
                System.out.println("Connect server failure.");
                future.cause().printStackTrace();
                //关闭线程组
                workerGroup.shutdownGracefully();
            }

        });

        channel = (SocketChannel) channelFuture.channel();
    }

    public SocketChannel getChannel() {
        return channel;
    }
}
