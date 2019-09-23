package com.bkhech.home.practice.javacore.core.system;

import java.io.*;
import java.util.Scanner;

/**
 * @author guowm
 * @date 2019/9/23
 * @description
 * 改变默认输入输出 （默认是控制台）
 *
 */
public class SystemSetOutSetInTest {

    public static void main(String[] args) {
//        setOutTest();
        setInTest();
    }

    public static void setOutTest() {

        try ( PrintStream out = System.out;
              PrintStream ps = new PrintStream("./log.txt")) {
            System.setOut(ps);
            System.out.println("你好");
            System.out.println("重置");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setInTest() {
        try (InputStream in = System.in;
             InputStream is = new FileInputStream("./log.txt")) {
            System.setIn(is);
            Scanner scanner = new Scanner(System.in);
            String line = "";
            while (scanner.hasNext()) {
                line = scanner.next();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
