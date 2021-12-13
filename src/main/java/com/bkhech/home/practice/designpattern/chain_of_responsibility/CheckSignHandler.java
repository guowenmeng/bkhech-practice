package com.bkhech.home.practice.designpattern.chain_of_responsibility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author guowm
 * @date 2021/12/10
 */
@Slf4j
public class CheckSignHandler {

    private static final CheckSignHandler INSTANCE = new CheckSignHandler();

    private CheckSignHandler() {
    }

    public static CheckSignHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 校验 sign
     *
     * @param sourceSign
     * @param validationMap
     */
    public void checkSign(String sourceSign, Map<String, String> validationMap) {
        if (!StringUtils.hasText(sourceSign)) {
            log.info("丢失 sign 参数");
            throw new SignException("丢失 sign 参数");
        }

        //实际业务处理
        String validSign = "";

        if (!validSign.equals(sourceSign)) {
            log.warn("validSign failure! {} | {}", sourceSign, validSign);
            throw new SignException("数据token错误");
        }
    }

}
