1. 打成依赖包后，运行报错
   org.apache.hadoop.fs.UnsupportedFileSystemException: No FileSystem for scheme "hdfs"
    >  java -cp hadoop-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.bkhech.bigdata.hadoop.HadoopDemo

    解决： 在pom中添加依赖
    ```xml
        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-hdfs-client</artifactId>
            <version>3.2.3</version>
        </dependency>
    ```
2. 