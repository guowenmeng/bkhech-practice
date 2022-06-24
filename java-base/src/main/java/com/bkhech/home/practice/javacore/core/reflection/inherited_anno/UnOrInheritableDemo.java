package com.bkhech.home.practice.javacore.core.reflection.inherited_anno;

import com.bkhech.home.practice.javacore.core.reflection.custom_annotation.TestAn;

/**
 * 点睛之笔
 * Java有四种元注解:
 * 使用@Retention
 * 使用@Target
 * 使用@Documented
 * 使用@Inherited
 * {@link java.lang.annotation.Inherited}  注解使用样例
 * @author guowm
 * @date 2021/7/8
 */
@TestAn("InheritableDemo")
public class UnOrInheritableDemo extends Base {

    public static void main(String[] args) {
        // 打印InheritableDemo类是否具有@InheritedAn修饰
        System.out.println(UnOrInheritableDemo.class
                .isAnnotationPresent(InheritableAn.class));

        /**
         * "D:\Program Files\Java\jdk1.8.0_144\bin\java.exe" "-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2019.3.1\lib\idea_rt.jar=64236:D:\Program Files\JetBrains\IntelliJ IDEA 2019.3.1\bin" -Dfile.encoding=UTF-8 -classpath "D:\Program Files\Java\jdk1.8.0_144\jre\lib\charsets.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\deploy.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\access-bridge-64.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\cldrdata.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\dnsns.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\jaccess.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\jfxrt.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\localedata.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\nashorn.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\sunec.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\sunjce_provider.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\sunmscapi.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\sunpkcs11.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\ext\zipfs.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\javaws.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\jce.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\jfr.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\jfxswt.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\jsse.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\management-agent.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\plugin.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\resources.jar;D:\Program Files\Java\jdk1.8.0_144\jre\lib\rt.jar;D:\IdeaProjects\myproject\bkhech-practice\target\classes;D:\maven_repository\org\springframework\boot\spring-boot-starter\2.3.2.RELEASE\spring-boot-starter-2.3.2.RELEASE.jar;D:\maven_repository\org\springframework\boot\spring-boot\2.3.2.RELEASE\spring-boot-2.3.2.RELEASE.jar;D:\maven_repository\org\springframework\spring-context\5.2.8.RELEASE\spring-context-5.2.8.RELEASE.jar;D:\maven_repository\org\springframework\boot\spring-boot-autoconfigure\2.3.2.RELEASE\spring-boot-autoconfigure-2.3.2.RELEASE.jar;D:\maven_repository\org\springframework\boot\spring-boot-starter-logging\2.3.2.RELEASE\spring-boot-starter-logging-2.3.2.RELEASE.jar;D:\maven_repository\ch\qos\logback\logback-classic\1.2.3\logback-classic-1.2.3.jar;D:\maven_repository\ch\qos\logback\logback-core\1.2.3\logback-core-1.2.3.jar;D:\maven_repository\org\apache\logging\log4j\log4j-to-slf4j\2.13.3\log4j-to-slf4j-2.13.3.jar;D:\maven_repository\org\apache\logging\log4j\log4j-api\2.13.3\log4j-api-2.13.3.jar;D:\maven_repository\org\slf4j\jul-to-slf4j\1.7.30\jul-to-slf4j-1.7.30.jar;D:\maven_repository\jakarta\annotation\jakarta.annotation-api\1.3.5\jakarta.annotation-api-1.3.5.jar;D:\maven_repository\org\springframework\spring-core\5.2.8.RELEASE\spring-core-5.2.8.RELEASE.jar;D:\maven_repository\org\springframework\spring-jcl\5.2.8.RELEASE\spring-jcl-5.2.8.RELEASE.jar;D:\maven_repository\org\yaml\snakeyaml\1.26\snakeyaml-1.26.jar;D:\maven_repository\org\projectlombok\lombok\1.18.12\lombok-1.18.12.jar;D:\maven_repository\org\slf4j\slf4j-api\1.7.30\slf4j-api-1.7.30.jar;D:\maven_repository\org\hamcrest\hamcrest\2.2\hamcrest-2.2.jar;D:\maven_repository\com\alibaba\fastjson\1.2.53\fastjson-1.2.53.jar;D:\maven_repository\org\springframework\boot\spring-boot-starter-web\2.3.2.RELEASE\spring-boot-starter-web-2.3.2.RELEASE.jar;D:\maven_repository\org\springframework\boot\spring-boot-starter-json\2.3.2.RELEASE\spring-boot-starter-json-2.3.2.RELEASE.jar;D:\maven_repository\com\fasterxml\jackson\core\jackson-databind\2.11.1\jackson-databind-2.11.1.jar;D:\maven_repository\com\fasterxml\jackson\core\jackson-annotations\2.11.1\jackson-annotations-2.11.1.jar;D:\maven_repository\com\fasterxml\jackson\core\jackson-core\2.11.1\jackson-core-2.11.1.jar;D:\maven_repository\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.11.1\jackson-datatype-jdk8-2.11.1.jar;D:\maven_repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.11.1\jackson-datatype-jsr310-2.11.1.jar;D:\maven_repository\com\fasterxml\jackson\module\jackson-module-parameter-names\2.11.1\jackson-module-parameter-names-2.11.1.jar;D:\maven_repository\org\springframework\boot\spring-boot-starter-tomcat\2.3.2.RELEASE\spring-boot-starter-tomcat-2.3.2.RELEASE.jar;D:\maven_repository\org\apache\tomcat\embed\tomcat-embed-core\9.0.37\tomcat-embed-core-9.0.37.jar;D:\maven_repository\org\glassfish\jakarta.el\3.0.3\jakarta.el-3.0.3.jar;D:\maven_repository\org\apache\tomcat\embed\tomcat-embed-websocket\9.0.37\tomcat-embed-websocket-9.0.37.jar;D:\maven_repository\org\springframework\spring-web\5.2.8.RELEASE\spring-web-5.2.8.RELEASE.jar;D:\maven_repository\org\springframework\spring-beans\5.2.8.RELEASE\spring-beans-5.2.8.RELEASE.jar;D:\maven_repository\org\springframework\spring-webmvc\5.2.8.RELEASE\spring-webmvc-5.2.8.RELEASE.jar;D:\maven_repository\org\springframework\spring-aop\5.2.8.RELEASE\spring-aop-5.2.8.RELEASE.jar;D:\maven_repository\org\springframework\spring-expression\5.2.8.RELEASE\spring-expression-5.2.8.RELEASE.jar;D:\maven_repository\com\google\guava\guava\28.0-jre\guava-28.0-jre.jar;D:\maven_repository\com\google\guava\failureaccess\1.0.1\failureaccess-1.0.1.jar;D:\maven_repository\com\google\guava\listenablefuture\9999.0-empty-to-avoid-conflict-with-guava\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;D:\maven_repository\com\google\code\findbugs\jsr305\3.0.2\jsr305-3.0.2.jar;D:\maven_repository\org\checkerframework\checker-qual\2.8.1\checker-qual-2.8.1.jar;D:\maven_repository\com\google\errorprone\error_prone_annotations\2.3.2\error_prone_annotations-2.3.2.jar;D:\maven_repository\com\google\j2objc\j2objc-annotations\1.3\j2objc-annotations-1.3.jar;D:\maven_repository\org\codehaus\mojo\animal-sniffer-annotations\1.17\animal-sniffer-annotations-1.17.jar;D:\maven_repository\com\google\zxing\core\3.3.3\core-3.3.3.jar;D:\maven_repository\com\google\zxing\javase\3.3.3\javase-3.3.3.jar;D:\maven_repository\com\beust\jcommander\1.72\jcommander-1.72.jar;D:\maven_repository\com\github\jai-imageio\jai-imageio-core\1.4.0\jai-imageio-core-1.4.0.jar;D:\maven_repository\com\google\protobuf\protobuf-java\3.11.1\protobuf-java-3.11.1.jar;D:\IdeaProjects\myproject\bkhech-practice\libs\jieba-analysis-1.0.3-SNAPSHOT.jar;D:\maven_repository\org\ansj\ansj_seg\5.1.6\ansj_seg-5.1.6.jar;D:\maven_repository\org\nlpcn\nlp-lang\1.7.7\nlp-lang-1.7.7.jar;D:\maven_repository\cglib\cglib\3.3.0\cglib-3.3.0.jar;D:\maven_repository\org\ow2\asm\asm\7.1\asm-7.1.jar;D:\maven_repository\io\netty\netty-all\4.1.56.Final\netty-all-4.1.56.Final.jar;D:\maven_repository\io\reactivex\rxjava2\rxjava\2.2.20\rxjava-2.2.20.jar;D:\maven_repository\org\reactivestreams\reactive-streams\1.0.3\reactive-streams-1.0.3.jar;D:\maven_repository\junit\junit\4.13.2\junit-4.13.2.jar;D:\maven_repository\org\hamcrest\hamcrest-core\2.2\hamcrest-core-2.2.jar;D:\IdeaProjects\myproject\bkhech-practice\libs\object-size-agent-1.0.0.jar" com.bkhech.home.practice.javacore.core.reflection.inherited_anno.InheritableDemo
         * true
         *
         * Process finished with exit code 0
         *
         * 运行结果为true, 说明 InheritableTest虽然没直接用@Inheritable修饰，但也具有该注解。
         */

        // 打印InheritableDemo类是否具有@UnheritedAn修饰
        System.out.println(UnOrInheritableDemo.class
                .isAnnotationPresent(UnheritableAn.class));


    }

}
