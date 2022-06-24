package com.bkhech.home.practice.socket.netty;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author guowm
 * @date 2021/1/5
 * @description
 */
@Slf4j
public class Main {

    public static void main(String[] args) throws Exception {
        NettyClient nettyClient = new NettyClient(9999);
        //启动服务
        nettyClient.start();

        SocketChannel channel = nettyClient.getChannel();
        channel.writeAndFlush("guowm");
//        sendMsg(nettyClient.getChannel());
    }

    public static void sendMsg(Channel channel) {
        try {
            while (true) {
                printArgs();
                String inputLine;
                String[] inputArgs;
                BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
                inputLine = consoleInput.readLine();
                inputArgs = inputLine.split(" ");
                switch (inputArgs[0]) {
                    case "send":
                        String msg = inputArgs[1];
                        send(channel, msg);
                        break;
                    case "quit":
                        channel.close();
                        System.exit(0);
                        break;

                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            channel.close();
            System.exit(-1);
        }
    }

    public static void printArgs() {
        System.out.println("--------Test client -------");
        System.out.println("send msg 发消息");
        System.out.println("quit 退出");
        System.out.println("-----------------");
    }

    public static void send(Channel channel, String msg) {
        System.out.println(msg);
        channel.writeAndFlush(msg);
    }
}
