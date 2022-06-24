package com.bkhech.home.practice.socket.bio.server;

import com.bkhech.home.practice.socket.bio.SocketSupport;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.bkhech.home.practice.socket.bio.Constants.SPECIFIC_TIPS;

/**
 * socket 服务端
 *
 * @author guowm
 * @date 2021/12/10
 */
@Slf4j
public class SocketServer {
    /**
     * 两个作用：
     * 1. 让服务端的任务可以异步执行
     * 2. 管理可以同时处理的客户端请求数量。 此时是 5 个
     */
    private static final ThreadPoolExecutor COLLECT_POLL = new ThreadPoolExecutor(
            4, 4,
            10, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(1),
            r -> {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                thread.setName("socket-server");
                return thread;
            });


    public static void main(String[] args) {
        SocketServer socketServer = new SocketServer();
        socketServer.start();
    }

    /**
     * 启动服务端
     */
    public void start() {
        log.info(Thread.currentThread().getId() + "：SocketServer 服务端开始启动");

        ServerSocket serverSocket = null;
        try {
            // 初始化服务端
            serverSocket = new ServerSocket();
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress("localhost", 7007));
            log.info(Thread.currentThread().getId() + "：SocketServer 服务端启动成功");
        } catch (Exception e) {
            log.error(Thread.currentThread().getId() + "：SocketServer - start 服务端启动失败", e);
            throw new RuntimeException(e);
        }

        try {
            //自旋，让服务端一直获取客户端的请求，如果暂时没有客户端的请求，会一直阻塞
            while (true) {
                // 接收客户端请求
                Socket clientSocket = serverSocket.accept();
                // 如果队列中有多于一个数据了，说明服务端已经到了并发处理的极限了，此时需要返回客户端有意义的信息
                /**
                 * 我们使用 collectPoll.getQueue().size() >= 1 来判断目前服务端是否已经到达处理的极限了，
                 * 如果队列中有一个任务正在排队，说明当前服务端已经超负荷运行了，新的请求应该拒绝掉，如果队列中没有数据，
                 * 说明服务端还可以接受新的请求。
                 */
                if (COLLECT_POLL.getQueue().size() >= 1) {
                    log.info(Thread.currentThread().getId() + "：SocketServer 服务端处理能力到顶，需要控制客户端的请求");
                    //返回处理结果给客户端
                    rejectRequest(clientSocket);
                    continue;
                }

                try {
                    COLLECT_POLL.submit(new SocketServerHandler(clientSocket));
                } catch (Exception e) {
                    log.error("业务处理失败", e);
                    clientSocket.close();
                }
            }
        } catch (Throwable e) {
            log.error("SocketServer unknown error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回特定的错误消息给客户端
     *
     * @param clientSocket
     * @throws IOException
     */
    private void rejectRequest(Socket clientSocket) throws IOException {
        OutputStream outputStream = null;
        try {
            outputStream = clientSocket.getOutputStream();
            byte[] bytes = SPECIFIC_TIPS.getBytes(StandardCharsets.UTF_8);
            SocketSupport.segmentWrite(bytes, outputStream);
            clientSocket.shutdownOutput();
        } finally {
            SocketSupport.close(clientSocket, outputStream, null, null, null);
        }
    }
}
