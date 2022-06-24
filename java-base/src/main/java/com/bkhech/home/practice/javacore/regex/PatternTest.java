package com.bkhech.home.practice.javacore.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author guowm
 * @date 2019/5/27
 */
public class PatternTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
        test7();
    }

    /**
     * 多行匹配模式
     */
    public static void test1() {
        Pattern p1 = Pattern.compile("^.*b.*$");
        //输出false,因为正则表达式中出现了^或$，默认只会匹配第一行，第二行的b匹配不到。
        System.out.println(p1.matcher("a\nb").find());
        Pattern p2 = Pattern.compile("^.*b.*$",Pattern.MULTILINE);
        //输出true,指定了Pattern.MULTILINE模式，就可以匹配多行了。
        System.out.println(p2.matcher("a\nb").find());
    }

    public static void test2() {
        Pattern p1 = Pattern.compile("\\d{6}");
        Matcher matcher = p1.matcher("12200382554d8722e589750004");
       while (matcher.find()) {
           String s = matcher.group();
           System.out.println("p1 = " + s);
       }
    }

    /**
     * 匹配以逗号分隔，后跟0个或者多个空白字符
     */
    public static void test3() {
        String regex = ",\\s*";
        String str = "1, 李洪鹤, 110, lihh@tedu.cn, 北京海淀区";
        String[] data=str.split(regex);
        System.out.println(data[1]);
    }

    /**
     * 分组
     */
    /**
     * 捕获组
     * 1. group(0) = group()
     * 2. matcher.groupCount());不包含group()，即group(0)
     *
     * 总结下来分组 的作用：
     * 1.将某些规律看成是一组，然后进行组级别的重复，可以得到意想不到的效果。
     * 2.分组之后，可以通过后向引用简化表达式（\1 或者$1）。
     * @link https://blog.csdn.net/qq_41084324/article/details/83989223
     */
    public static void test4() {
        String str = "192.168.71.106.92.68.1.16";

//        String regex = "(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})";
        //反向引用
        String regex = "(\\d{1,3})(\\.(\\d{1,3})){3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        System.out.println("matcher.groupCount() = " + matcher.groupCount());//不包含group()，即group(0)

        while (matcher.find()) {
            System.out.println("matcher.group() = " + matcher.group());
            System.out.println("matcher.group1() = " + matcher.group(1));
            System.out.println("matcher.group2() = " + matcher.group(2));
            System.out.println("matcher.group3() = " + matcher.group(3));
        }
    }


    /**
     * 非捕获组。以 (?) 开头的组是纯的非捕获组
     *
     * 这四个非捕获组用于匹配表达式X，但是不包含表达式的文本。
     *
     * (?=X )	零宽度正先行断言。仅当子表达式 X 在 此位置的右侧匹配时才继续匹配。例如，/w+(?=/d) 与后跟数字的单词匹配，而不与该数字匹配。此构造不会回溯。
     * (?!X)	零宽度负先行断言。仅当子表达式 X 不在 此位置的右侧匹配时才继续匹配。例如，例如，/w+(?!/d) 与后不跟数字的单词匹配，而不与该数字匹配。
     * (?<=X)	零宽度正后发断言。仅当子表达式 X 在 此位置的左侧匹配时才继续匹配。例如，(?<=19)99 与跟在 19 后面的 99 的实例匹配。此构造不会回溯。
     * (?<!X)	零宽度负后发断言。仅当子表达式 X 不在此位置的左侧匹配时才继续匹配。例如，(?<!19)99 与不跟在 19 后面的 99 的实例匹配
     *
     *
     *  一般来说要如果是要对一个字符组合采用否定判定时一般都会用上先行断言和后发断言。例如左边不能包含1302和1301的字符串，因为你没法使用某种方式来否定一个1302四个字符的组合(注意：[^（1302)|(1301)]表示不能是1或者3或者0或者2，而不是1302一个整体)，只有先行或者后发断言可以表示一个整体 : 456(?<!1302|1301)789.
     *
     * @link https://blog.csdn.net/lovingprince/article/details/2774819
     */
    public static void test5() {
        //eg1.匹配后面的文本是数字时才继续匹配
        String str = "34837ak3d";
        String regex = "\\w+(?=\\d)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        System.out.println("matcher.groupCount() = " + matcher.groupCount());
        while (matcher.find()) {
            System.out.println("matcher.group() = " + matcher.group(0));
        }
    }

    /**
     * 需求：匹配
     * 56前面不能是4，后面必须是9组成
     * @link https://blog.csdn.net/lovingprince/article/details/2774819
     */
    public static void test6() {
        //eg2. (?<!4)56(?=9)
        // 因此，可以匹配如下文本 5569  ，与4569不匹配。
        String str = "5569";
        String regex = "(?<!4)56(?=9)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        System.out.println("matcher.groupCount() = " + matcher.groupCount());
        while (matcher.find()) {
            // 结果是56:
            // 为什么不是4569呢？ 因为非捕获组用于匹配表达式X，但是 不包含 表达式的文本
            System.out.println("matcher.group() = " + matcher.group(0));
        }
    }

    /**
     * 需求：提取字符串
     * 提取 da12bka3434bdca4343bdca234bm   提取包含在字符a和b之间的数字，但是这个a之前的字符不能是c，且 b后面的字符必须是d才能提取。
     * @link https://blog.csdn.net/lovingprince/article/details/2774819
     */
    public static void test7() {
        String str = "da12bka3434bdca4343bdca234bm";
        String regex = "(?<=c)a(\\d+)bd";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        System.out.println("matcher.groupCount() = " + matcher.groupCount());
        while (matcher.find()) {
            System.out.println("matcher.group(1) = " + matcher.group(1));//我们只要捕获组1的数字即可。结果 3434
            /**
             * 0组是整个表达式，看这里，并没有提炼出(?<!c)的字符 。结果 a3434bd
             * 可以看到，非捕获组，最后是不会返回结果的，因为它本身并不捕获文本。
             */
            System.out.println("matcher.group(0) = " + matcher.group(0));
        }
    }

}
