package com.bkhech.home.practice.socket.bio.client;

import com.bkhech.home.practice.socket.bio.SocketSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 客户端任务（业务）处理逻辑
 *
 * @author guowm
 * @date 2021/12/9
 */
@Slf4j
public class SocketClientHandler {

    private final Socket socket;

    public SocketClientHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * 发送 tcp 消息
     *
     * @param content    发送内容
     * @throws IOException
     * @return
     */
    public String doSend(String content) throws IOException {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            // 准备发送消息给服务端
            outputStream = socket.getOutputStream();
            // 设置 StandardCharsets.UTF_8, 防止乱码
            byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
            // 输出字节
            SocketSupport.segmentWrite(bytes, outputStream);
            // 关闭输出
            socket.shutdownOutput();
            log.info(Thread.currentThread().getId() + "：发送内容为: {}", content);

            // 等待服务端的数据返回，50 秒还没有得到数据，直接断开连接
            socket.setSoTimeout(50_000);
            // 得到服务端的返回流
            inputStream = socket.getInputStream();
            // 使用 StandardCharsets.UTF_8 编码响应内容, 防止乱码
            isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            // 从流中读取返回值
            StringBuffer response = SocketSupport.segmentRead(br);
            // 关闭输入流
            socket.shutdownInput();

            // 关闭各种流和套接字
            SocketSupport.close(socket, outputStream, inputStream, isr, br);

            return response.toString();
        } finally {
            try {
                SocketSupport.close(socket, outputStream, inputStream, isr, br);
            } catch (Exception e) {
                // do nothing
                System.out.println(Thread.currentThread().getId() + "：" + e.getMessage());
            }
        }
    }

}
