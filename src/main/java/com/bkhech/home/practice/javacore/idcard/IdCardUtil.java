package com.bkhech.home.practice.javacore.idcard;

import com.alibaba.fastjson.JSON;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guowm
 * @date 2019/11/28
 * @description
 */
public class IdCardUtil {
    /**
     * 通过身份证号码获取出生日期、性别、年龄
     * @param idNum
     * @return 返回的出生日期格式：1990-01-01   性别格式：2-女，1-男
     */
    public static Map<String, String> getBirAgeSex(String idNum) {
        String birthday = "";
        String age = "";
        String sexCode = "";

        int year = Calendar.getInstance().get(Calendar.YEAR);
        char[] number = idNum.toCharArray();
        boolean flag = true;
        if (number.length == 15) {
            for (int x = 0; x < number.length; x++) {
                if (!flag) {
                    return new HashMap<>();
                }
                flag = Character.isDigit(number[x]);
            }
        } else if (number.length == 18) {
            for (int x = 0; x < number.length - 1; x++) {
                if (!flag) {
                    return new HashMap<>();
                }
                flag = Character.isDigit(number[x]);
            }
        }
        if (flag && idNum.length() == 15) {
            birthday = "19" + idNum.substring(6, 8) + "-"
                    + idNum.substring(8, 10) + "-"
                    + idNum.substring(10, 12);
            sexCode = Integer.parseInt(idNum.substring(idNum.length() - 3)) % 2 == 0 ? "2" : "1";
            age = (year - Integer.parseInt("19" + idNum.substring(6, 8))) + "";
        } else if (flag && idNum.length() == 18) {
            birthday = idNum.substring(6, 10) + "-"
                    + idNum.substring(10, 12) + "-"
                    + idNum.substring(12, 14);
            sexCode = Integer.parseInt(idNum.substring(idNum.length() - 4, idNum.length() - 1)) % 2 == 0 ? "2" : "1";
            age = (year - Integer.parseInt(idNum.substring(6, 10))) + "";
        }
        Map<String, String> map = new HashMap<>();
        map.put("birthday", birthday);
        map.put("age", age);
        map.put("sexCode", sexCode);
        return map;
    }

    public static void main(String[] args) {
        Map<String, String> birAgeSex = getBirAgeSex("412728900401687");
        System.out.println("JSON.toJSONString(birAgeSex) = " + JSON.toJSONString(birAgeSex));

    }
}
