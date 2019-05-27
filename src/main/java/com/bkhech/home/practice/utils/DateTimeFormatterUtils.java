package com.bkhech.home.practice.utils;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;

import static java.time.temporal.ChronoField.*;

/**
 * @author guowm[guowm@5fun.com]
 * @date 2018/11/29
 */
public class DateTimeFormatterUtils {

    public static final DateTimeFormatter LOCAL_DATE_TIME;
    public static final DateTimeFormatter LOCAL_DATE;

    static {
        LOCAL_DATE_TIME = new DateTimeFormatterBuilder()
                .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
                .appendLiteral('-')
                .appendValue(MONTH_OF_YEAR, 2)
                .appendLiteral('-')
                .appendValue(DAY_OF_MONTH, 2)
                .appendLiteral(" ")
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(MINUTE_OF_HOUR, 2)
                .optionalStart()
                .appendLiteral(':')
                .appendValue(SECOND_OF_MINUTE, 2)
                .toFormatter();

        LOCAL_DATE = new DateTimeFormatterBuilder()
                .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
                .appendLiteral('-')
                .appendValue(MONTH_OF_YEAR, 2)
                .appendLiteral('-')
                .appendValue(DAY_OF_MONTH, 2)
                .toFormatter();
    }



    public static LocalDate parseLocalDate(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, dtf);
    }

    public static void main(String[] args) {
//        LocalDate localDate = parseLocalDate("2018-15-20");
//        System.out.println("localDate = " + localDate.toString());
        String str1="2018-07-05";
//        DateTimeFormatter dtf = DateFormat.getInstance().format();
//        LocalDateTime parse = LocalDateTime.parse(str1, dtf);
//        System.out.println(parse);

    }


}
