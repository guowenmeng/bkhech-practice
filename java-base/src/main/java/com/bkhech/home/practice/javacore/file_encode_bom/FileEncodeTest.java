package com.bkhech.home.practice.javacore.file_encode_bom;

import java.io.*;

/**
 * 文件编码 with BOM
 *
 *
 * 在java中，class文件的编码方式与编译时javac选择的参数有关（-encoding<编码>指定源文件使用的字符编码），
 * Java的字符串是永远都是unicode的。
 *
 * 问题背景：
 * 比较文本文件（UTF-8编码）中一个首行固定值strA与java中定义的常量strB是否相同，因为strA在首行且在改行的开头，
 * 在比较中始终无法相同，在改变文编码格式为GBk后则可以工作，确认与文件编码格式有关，最后确认是由于UTF-8的文件有BOM和无BOM之区别，
 * 在有BOM的UTF-8的文件中需要注意文件开头会多出字符，我遇到的情况是多出？问号（i其实就是乱码了），在处理文件时规避掉这个BOM。
 *
 * 运行结果：
 * "D:\Program Files\Java\jdk1.8.0_144\bin\java.exe" "-javaagent:E:\Program Files\JetBrains\IntelliJ IDEA 2022.1.1\lib\idea_rt.jar=53774:E:\Program Files\JetBrains\IntelliJ IDEA 2022.1.1\bin" -Dfile.encoding=UTF-8 -classpath "D:\Program Files\Java\jdk1.8.0_144\jre\lib\charsets.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\deploy.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\access-bridge-64.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\cldrdata.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\dnsns.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\jaccess.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\jfxrt.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\localedata.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\nashorn.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\sunec.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\sunjce_provider.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\sunmscapi.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\sunpkcs11.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\zipfs.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\javaws.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\jce.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\jfr.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\jfxswt.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\jsse.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\management-agent.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\plugin.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\resources.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\rt.jar;D:\IdeaProjects\myproject\bkhech-practice\target\classes;D:\maven_repository\org\springframework\boot\spring-boot-starter\2.5.12\spring-boot-starter-2.5.12.jar;D:\maven_repository\org\springframework\boot\spring-boot\2.5.12\spring-boot-2.5.12.jar;D:\maven_repository\org\springframework\spring-context\5.3.18\spring-context-5.3.18.jar;D:\maven_repository\org\springframework\boot\spring-boot-autoconfigure\2.5.12\spring-boot-autoconfigure-2.5.12.jar;D:\maven_repository\org\springframework\boot\spring-boot-starter-logging\2.5.12\spring-boot-starter-logging-2.5.12.jar;D:\maven_repository\ch\qos\logback\logback-classic\1.2.11\logback-classic-1.2.11.jar;D:\maven_repository\ch\qos\logback\logback-core\1.2.11\logback-core-1.2.11.jar;D:\maven_repository\org\apache\logging\log4j\log4j-to-slf4j\2.17.2\log4j-to-slf4j-2.17.2.jar;D:\maven_repository\org\apache\logging\log4j\log4j-api\2.17.2\log4j-api-2.17.2.jar;D:\maven_repository\org\slf4j\jul-to-slf4j\1.7.36\jul-to-slf4j-1.7.36.jar;D:\maven_repository\jakarta\annotation\jakarta.annotation-api\1.3.5\jakarta.annotation-api-1.3.5.jar;D:\maven_repository\org\springframework\spring-core\5.3.18\spring-core-5.3.18.jar;D:\maven_repository\org\springframework\spring-jcl\5.3.18\spring-jcl-5.3.18.jar;D:\maven_repository\org\yaml\snakeyaml\1.28\snakeyaml-1.28.jar;D:\maven_repository\org\projectlombok\lombok\1.18.22\lombok-1.18.22.jar;D:\maven_repository\org\slf4j\slf4j-api\1.7.36\slf4j-api-1.7.36.jar;D:\maven_repository\org\hamcrest\hamcrest\2.2\hamcrest-2.2.jar;D:\maven_repository\com\alibaba\fastjson\1.2.53\fastjson-1.2.53.jar;D:\maven_repository\org\springframework\boot\spring-boot-starter-web\2.5.12\spring-boot-starter-web-2.5.12.jar;D:\maven_repository\org\springframework\boot\spring-boot-starter-json\2.5.12\spring-boot-starter-json-2.5.12.jar;D:\maven_repository\com\fasterxml\jackson\core\jackson-databind\2.12.6.1\jackson-databind-2.12.6.1.jar;D:\maven_repository\com\fasterxml\jackson\core\jackson-annotations\2.12.6\jackson-annotations-2.12.6.jar;D:\maven_repository\com\fasterxml\jackson\core\jackson-core\2.12.6\jackson-core-2.12.6.jar;D:\maven_repository\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.12.6\jackson-datatype-jdk8-2.12.6.jar;D:\maven_repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.12.6\jackson-datatype-jsr310-2.12.6.jar;D:\maven_repository\com\fasterxml\jackson\module\jackson-module-parameter-names\2.12.6\jackson-module-parameter-names-2.12.6.jar;D:\maven_repository\org\springframework\boot\spring-boot-starter-tomcat\2.5.12\spring-boot-starter-tomcat-2.5.12.jar;D:\maven_repository\org\apache\tomcat\embed\tomcat-embed-core\9.0.60\tomcat-embed-core-9.0.60.jar;D:\maven_repository\org\apache\tomcat\embed\tomcat-embed-el\9.0.60\tomcat-embed-el-9.0.60.jar;D:\maven_repository\org\apache\tomcat\embed\tomcat-embed-websocket\9.0.60\tomcat-embed-websocket-9.0.60.jar;D:\maven_repository\org\springframework\spring-web\5.3.18\spring-web-5.3.18.jar;D:\maven_repository\org\springframework\spring-beans\5.3.18\spring-beans-5.3.18.jar;D:\maven_repository\org\springframework\spring-webmvc\5.3.18\spring-webmvc-5.3.18.jar;D:\maven_repository\org\springframework\spring-aop\5.3.18\spring-aop-5.3.18.jar;D:\maven_repository\org\springframework\spring-expression\5.3.18\spring-expression-5.3.18.jar;D:\maven_repository\com\google\guava\guava\28.0-jre\guava-28.0-jre.jar;D:\maven_repository\com\google\guava\failureaccess\1.0.1\failureaccess-1.0.1.jar;D:\maven_repository\com\google\guava\listenablefuture\9999.0-empty-to-avoid-conflict-with-guava\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;D:\maven_repository\com\google\code\findbugs\jsr305\3.0.2\jsr305-3.0.2.jar;D:\maven_repository\org\checkerframework\checker-qual\2.8.1\checker-qual-2.8.1.jar;D:\maven_repository\com\google\errorprone\error_prone_annotations\2.3.2\error_prone_annotations-2.3.2.jar;D:\maven_repository\com\google\j2objc\j2objc-annotations\1.3\j2objc-annotations-1.3.jar;D:\maven_repository\org\codehaus\mojo\animal-sniffer-annotations\1.17\animal-sniffer-annotations-1.17.jar;D:\maven_repository\com\google\zxing\core\3.3.3\core-3.3.3.jar;D:\maven_repository\com\google\zxing\javase\3.3.3\javase-3.3.3.jar;D:\maven_repository\com\beust\jcommander\1.72\jcommander-1.72.jar;D:\maven_repository\com\github\jai-imageio\jai-imageio-core\1.4.0\jai-imageio-core-1.4.0.jar;D:\maven_repository\com\google\protobuf\protobuf-java\3.11.1\protobuf-java-3.11.1.jar;D:\IdeaProjects\myproject\bkhech-practice\libs\jieba-analysis-1.0.3-SNAPSHOT.jar;D:\maven_repository\org\ansj\ansj_seg\5.1.6\ansj_seg-5.1.6.jar;D:\maven_repository\org\nlpcn\nlp-lang\1.7.7\nlp-lang-1.7.7.jar;D:\maven_repository\cglib\cglib\3.3.0\cglib-3.3.0.jar;D:\maven_repository\org\ow2\asm\asm\7.1\asm-7.1.jar;D:\maven_repository\io\netty\netty-all\4.1.56.Final\netty-all-4.1.56.Final.jar;D:\maven_repository\io\reactivex\rxjava2\rxjava\2.2.20\rxjava-2.2.20.jar;D:\maven_repository\org\reactivestreams\reactive-streams\1.0.3\reactive-streams-1.0.3.jar;D:\maven_repository\junit\junit\4.13.2\junit-4.13.2.jar;D:\maven_repository\org\hamcrest\hamcrest-core\2.2\hamcrest-core-2.2.jar;D:\IdeaProjects\myproject\bkhech-practice\libs\object-size-agent-1.0.0.jar;D:\maven_repository\io\projectreactor\reactor-core\3.4.16\reactor-core-3.4.16.jar;D:\maven_repository\org\apache\commons\commons-lang3\3.12.0\commons-lang3-3.12.0.jar;D:\maven_repository\org\openjdk\jol\jol-core\0.16\jol-core-0.16.jar" com.bkhech.home.practice.javacore.file_encode_bom.FileEncodeTest
 * EF BB BF 54 68 69 73 20 69 73 20 74 68 65 20 66 69 72 73 74 20 6C 69 6E 65 2E
 * 54 68 69 73 20 69 73 20 73 65 63 6F 6E 64 20 6C 69 6E 65 2E
 *
 * 其中 EF BB BF 是UTF-8 with BOM头信息
 * @author guowm
 * @date 2022/6/17
 */
public class FileEncodeTest {
    public static void main(String[] args) throws IOException {
        File f  = new File("src/main/java/com/bkhech/home/practice/javacore/file_encode_bom/11.txt");
        FileInputStream in = new FileInputStream(f);
        // 指定读取文件时以UTF-8的格式读取
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

        String line = br.readLine();
        while(line != null)
        {
            byte[] allbytes = line.getBytes("UTF-8");
            for (int i=0; i < allbytes.length; i++)
            {
                int tmp = allbytes[i];
                String hexString = Integer.toHexString(tmp);
                // 1个byte变成16进制的，只需要2位就可以表示了，取后面两位，去掉前面的符号填充
                hexString = hexString.substring(hexString.length() -2);
                System.out.print(hexString.toUpperCase());
                System.out.print(" ");
            }
            System.out.println();
            line = br.readLine();
        }
    }
}
