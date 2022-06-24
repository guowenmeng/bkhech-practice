package com.bkhech.home.practice.rpc.protobuf;

import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.io.*;

/**
 * @author guowm[guowm@5fun.com]
 * @date 2020/1/8
 */
public class ProtobufTest {

    private final String message = "hello, 手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打" +
            "手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打" +
            "手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打" +
            "手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打" +
            "hello, 手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打" +
            "手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打" +
            "手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打" +
            "手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打" +
            "hello, 手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打" +
            "手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打" +
            "手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打" +
            "手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打" +
            "手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打手动发了加咖啡机苦辣都放假啊两地分居阿拉山口打";

    //8197
    //8291
    //6780
    @Test
    public void testWriteProtocol() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (int i = 0; i < 10000; i++) {
            RequestProto.ReqProtocol abRequestProto = RequestProto.ReqProtocol.newBuilder()
                    .setType(1)
                    .setRequestId(332445723020L)
                    .setReqMsg(message)
                    .build();
            try (FileOutputStream outputStream = new FileOutputStream(new File("E:\\protocol\\grpc\\ABRequestProto"+ i +".txt"))) {

                abRequestProto.writeTo(outputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    //544
    //484
    //520
    @Test
    public void testReadProtocol() {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (int i = 0; i < 10000; i++) {
            try (FileInputStream inputStream = new FileInputStream(new File("E:\\protocol\\grpc\\ABRequestProto"+ i +".txt"))) {
                RequestProto.ReqProtocol abReqProtocol = RequestProto.ReqProtocol.parseFrom(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());

    }



//  ---------------------------java Serializable------------------------------


    @Data
    @Builder
    private static class ABRequestProto1 implements Serializable{
        private int type;
        private long  requestId;
        private String reqMsg;
    }

    //16220
    //18805
    //18640
    @Test
    public void testWrite() {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (int i = 0; i < 10000; i++) {

            ABRequestProto1 abRequestProto1 = new ABRequestProto1.ABRequestProto1Builder()
                    .type(1)
                    .requestId(332445723020L)
                    .reqMsg(message)
                    .build();

            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("E:\\protocol\\common\\ABRequestProto"+ i +".txt")))) {
                outputStream.writeObject(abRequestProto1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());

    }

    //1587
    //1518
    //1514
    @Test
    public void testRead() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 10000; i++) {

            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("E:\\protocol\\common\\ABRequestProto"+ i +".txt")))) {
                ABRequestProto1 o = (ABRequestProto1) inputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

}
