package com.bkhech.bigdata.hadoop;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

import java.io.IOException;

/**
 * hadoop 测试样例
 * <p>
 * # 所有机器都需编辑 hosts 文件
 * vi /etc/hosts
 * <p>
 * # 添加如下内容
 * 192.168.71.161 hadoop01
 * 192.168.71.162 hadoop02
 * 192.168.71.163 hadoop03
 *
 * @author guowm
 * @date 2022/6/23
 */
@Slf4j
public class HadoopDemo {

    static FileSystem fileSystem;

    static {
        //当前机器的hadoop客户端安装目录，此时应用程序相当于一个客户端
        System.setProperty("hadoop.home.dir", "E:\\hadoop\\hadoop-3.2.3");
        //设置有操作权限的用户，否则无法操作数据
        System.setProperty("HADOOP_USER_NAME", "hadoop");

        Configuration configuration = new Configuration();
        //指定我们使用的文件系统类型:
        configuration.set("fs.defaultFS", "hdfs://hadoop01:9000");
        //获取指定的文件系统
        try {
            fileSystem = FileSystem.get(configuration);
            System.out.println("--------" + fileSystem.toString() + "--------");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        final HadoopDemo hadoopDemo = new HadoopDemo();
        hadoopDemo.mkdirs();
        hadoopDemo.listFiles();
        fileSystem.close();
    }

    public void mkdirs() throws IOException {
        //2.创建文件夹
        boolean b = fileSystem.mkdirs(new Path("/aaa/bbb/ccc"));
        System.out.println(b);
    }

    public void listFiles() throws Exception {
        //获取RemoteIterator 得到所有的文件或者文件夹，第一个参数指定遍历的路径，第二个参数表示是否要递归遍历
        // 只能获取到文件
        RemoteIterator<LocatedFileStatus> locatedFileStatusRemoteIterator = fileSystem.listFiles(new Path("/"), true);
        while (locatedFileStatusRemoteIterator.hasNext()) {
            LocatedFileStatus next = locatedFileStatusRemoteIterator.next();
            System.out.println("-----****------" + next.getPath().toString());
        }
    }

}