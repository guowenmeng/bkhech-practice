package com.bkhech.home.practice.socket.bio.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * socket 客户端
 *
 * @author guowm
 * @date 2021/12/9
 */
@Slf4j
public class SocketClient {
    /**
     * 发送数据线程池 worker
     */
    private static final ThreadPoolExecutor SOCKET_POLL = new ThreadPoolExecutor(
            50, 50,
            10, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(400),
            r -> {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                thread.setName("socket-client");
                return thread;
            });

    public static void main(String[] args) throws IOException, InterruptedException {
        final SocketClient socketClient = new SocketClient();
        //模拟客户端同时向服务端发送 6 条消息
        for (int i = 0; i < 6; i++) {
            SOCKET_POLL.submit(() -> {
                String response = socketClient.send("localhost", 7007, "Hello BIO!");
                log.info(Thread.currentThread().getId() + "：接收到服务端返回的内容为: {}", response);
            });
        }

        new CountDownLatch(1).await();
    }

    /**
     * 发送 tcp 消息
     *
     * @param domainName 域名
     * @param port       端口
     * @param content    发送内容
     * @return
     */
    private String send(String domainName, int port, String content) {
        log.info(Thread.currentThread().getId() + "：客户端开始运行");

        if (StringUtils.isEmpty(domainName)) {
            throw new RuntimeException("domianName must not be null");
        }

        Socket socket = null;
        try {
            // 无参构造器初始化 Socket，默认底层协议是 TCP
            socket = new Socket();
            socket.setReuseAddress(true);
            // 客户端准备连接服务端，设置超时时间 10s
            socket.connect(new InetSocketAddress(domainName, port), 10_000);
            log.info(Thread.currentThread().getId() + "：成功和服务端建立连接");
            // 发送数据，进行业务处理
            SocketClientHandler socketClientHandler = new SocketClientHandler(socket);
            return socketClientHandler.doSend(content);
        } catch (ConnectException e) {
            log.error(Thread.currentThread().getId() + "：TCPClient - send socket 连接失败", e);
            throw new RuntimeException("socket 连接失败");
        } catch (Exception e) {
            log.error(Thread.currentThread().getId() + "：TCPClient - send unknown error", e);
            throw new RuntimeException("发送数据发生未知异常");
        }
    }

}
