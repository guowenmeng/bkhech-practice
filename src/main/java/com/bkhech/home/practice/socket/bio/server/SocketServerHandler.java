package com.bkhech.home.practice.socket.bio.server;

import com.bkhech.home.practice.socket.bio.SocketSupport;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 服务端任务（业务）处理逻辑
 *
 * @author guowm
 * @date 2021/12/10
 */
@Slf4j
public class SocketServerHandler implements Runnable {
    private final Socket socket;

    public SocketServerHandler(Socket clientSocket) {
        this.socket = clientSocket;
    }

    @Override
    public void run() {
        log.info(Thread.currentThread().getId() + "：服务端任务开始执行");

        InputStream inputStream = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        OutputStream outputStream = null;
        try {
            // 等待接收客户端消息。10秒还没有得到数据，直接断开连接
            socket.setSoTimeout(10_000);

            // 得到客户端的请求数据流
            inputStream = socket.getInputStream();
            // 使用 StandardCharsets.UTF_8 编码请求内容, 防止乱码
            isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            // 从流中读取数据
            StringBuffer request = SocketSupport.segmentRead(br);
            // 关闭输入流
            socket.shutdownInput();
            log.info(Thread.currentThread().getId() + "：接收到客户端信息：{}", request);

            // 服务端处理，模拟服务端处理耗时
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            // 服务端响应客户端内容
            String response = "服务端响应：" + request;
            // 返回处理结果给客户端
            outputStream = socket.getOutputStream();
            // 设置 StandardCharsets.UTF_8, 防止乱码
            byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
            // 输出响应内容
            SocketSupport.segmentWrite(bytes, outputStream);
            // 关闭输出流
            socket.shutdownOutput();

            // 关闭套接字和各种流
            SocketSupport.close(socket, outputStream, inputStream, isr, br);
            log.info(Thread.currentThread().getId() + "：服务端任务执行完成");
        } catch (IOException e) {
            log.error("SocketServerService IOException", e);
        } catch (Exception e) {
            log.error("SocketServerService Exception", e);
        } finally {
            try {
                SocketSupport.close(socket, outputStream, inputStream, isr, br);
            } catch (IOException e) {
                // do nothing
                System.out.println(Thread.currentThread().getId() + "：" + e.getMessage());
            }
        }

    }
}
