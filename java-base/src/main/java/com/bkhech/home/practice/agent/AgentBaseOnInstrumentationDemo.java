package com.bkhech.home.practice.agent;

import com.bkhech.object.size.SizeOfObject;

/**
 *
 * java agent 实践之：
 * 一个Java对象到底占用多大内存
 *
 * 启动 java -javaagent:object-size-agent-1.0.0.jar -XX:-UseCompressedOops -jar your.jar
 * 对象占用的内存大小收到VM参数UseCompressedOops的影响。
 *
 *
 * skywalking zipkin 等也是使用java agent 技术实现
 * @author guowm
 * @date 2021/6/3
 * @see https://www.cnblogs.com/zhanjindong/p/3757767.html
 */
public class AgentBaseOnInstrumentationDemo {
    public static void main(String[] args) {
        String a = "aa";
        System.out.println(SizeOfObject.sizeOf(a));
    }

}
