package com.bkhech.home.practice.javacore.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    String regex = "# Time: \\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";

    String reg = "# Time: \\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d+Z\n" +
            "# User@Host: (\\w+)\\[\\w+\\] @  \\[(\\d+\\.\\d+\\.\\d+\\.\\d+)\\]  Id:     \\d{1,9}\n" +
            "# Query_time: (\\d+\\.\\d+)  Lock_time: (\\d+\\.\\d+) Rows_sent: (\\d+)  Rows_examined: (\\d+)\n" +
            "SET timestamp=(\\d+);\n" +
            "(SELECT[\\s]*[f](.*?)\n)";


    String regX = "(?<=# Time: )" +
            "(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d+Z)\n" +
            "# User@Host: (\\w+)\\[\\w+\\] @  \\[(\\d+\\.\\d+\\.\\d+\\.\\d+)\\]  Id:     \\d{1,9}\n" +
            "# Query_time: (\\d+\\.\\d+)  Lock_time: (\\d+\\.\\d+) Rows_sent: (\\d+)  Rows_examined: (\\d+)\n" +
            "SET timestamp=(\\d+);\n" +
            "(SELECT[\\s\\S]*?\n)" +
            "(?=# Time:)";

    public void test() {
        String  str = "/usr/sbin/mysqld, Version: 5.7.25-log (MySQL Community Server (GPL)). started with:\n" +
                "Tcp port: 0  Unix socket: /var/lib/mysql/mysql.sock\n" +
                "Time                 Id Command    Argument\n" +
                "/usr/sbin/mysqld, Version: 5.7.25-log (MySQL Community Server (GPL)). started with:\n" +
                "Tcp port: 0  Unix socket: /var/lib/mysql/mysql.sock\n" +
                "Time                 Id Command    Argument\n" +
                "# Time: 2019-10-24T07:21:05.762551Z\n" +
                "# User@Host: root[root] @  [192.168.71.103]  Id:     3\n" +
                "# Query_time: 9.322713  Lock_time: 0.000128 Rows_sent: 5028344  Rows_examined: 5028344\n" +
                "SET timestamp=1571901665;\n" +
                "SELECT \n" +
                " * \n" +
                "FROM\n" +
                "user \n" +
                "WHERE\n" +
                "\tuser_code =1;\n" +
                "# Time: 2019-10-24T07:23:51.694644Z\n" +
                "# User@Host: root[root] @  [192.168.71.103]  Id:     3\n" +
                "# Query_time: 5.385575  Lock_time: 0.000131 Rows_sent: 5028344  Rows_examined: 5028344\n" +
                "SET timestamp=1571901831;\n" +
                "SELECT\n" +
                "\tid,\n" +
                "\tuser_code,\n" +
                "\tage,\n" +
                "\tcollege,\n" +
                "\t`like`,\n" +
                "\tremarks \n" +
                "FROM\n" +
                "\t`user` \n" +
                "WHERE\n" +
                "\tuser_code =1;\n";


        Pattern pattern = Pattern.compile(regX);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            String group1 = matcher.group(1);
            String group2 = matcher.group(2);
            String group3 = matcher.group(3);
            String group4 = matcher.group(4);
            String group5 = matcher.group(5);
            String group6 = matcher.group(6);
            String group7 = matcher.group(7);
            String group8 = matcher.group(8);
            String group9 = matcher.group(9);
            System.out.println("group = " + group);
            System.out.println("group1 = " + group1);
            System.out.println("group2 = " + group2);
            System.out.println("group3 = " + group3);
            System.out.println("group4 = " + group4);
            System.out.println("group5 = " + group5);
            System.out.println("group6 = " + group6);
            System.out.println("group7 = " + group7);
            System.out.println("group8 = " + group8);
            System.out.println("group9 = " + group9);
        }
    }


}
