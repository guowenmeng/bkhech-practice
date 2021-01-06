package com.bkhech.home.practice.netty.netty;

import com.bkhech.home.practice.netty.netty.handler.InboundHandler1;
import com.bkhech.home.practice.netty.netty.handler.InboundHandler2;
import com.bkhech.home.practice.netty.netty.handler.InboundHandler3;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author guowm
 * @date 2021/1/5
 * @description
 */
@Slf4j
public class NettyServer {

    public static void main(String[] args) {
        new NettyServer().bind(9999);
    }

    public void bind(int port) {

        //bossGroup负责处理TCP/IP连接的
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //workerGroup负责处理Channel的I/O事件
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    // 设置IO模型为NIO
                    .channel(NioServerSocketChannel.class)
                    // 绑定客户端连接时候触发操作
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline()
                                    //heart beat
                                    .addLast(new IdleStateHandler(60, 0, 0, TimeUnit.SECONDS))
                                    //decoder
                                    .addLast(new LengthFieldBasedFrameDecoder(4096, 0, 4, 0, 4, true))
    //                                    encoder
                                    .addLast(new LengthFieldPrepender(4))
                                    .addLast(new StringDecoder(CharsetUtil.UTF_8))
                                    .addLast(new StringEncoder(CharsetUtil.UTF_8))

                                    //handler
                                    .addLast(new InboundHandler1())
                                    .addLast(new InboundHandler2())
                                    .addLast(new InboundHandler3());
                        }
                    })
                    //初始化服务端可连接队列,指定了队列的大小128
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
                ChannelFuture channelFuture = b.bind(port).sync();
                channelFuture.addListener(future -> {
                if (future.isSuccess()) {
                    log.info("NettyServer startup success");
                } else {
                    log.error("NettyServer startup failure");
                    future.cause().printStackTrace();
                }
            });

            //成功绑定到端口之后,给channel增加一个 管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程。
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("", e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
