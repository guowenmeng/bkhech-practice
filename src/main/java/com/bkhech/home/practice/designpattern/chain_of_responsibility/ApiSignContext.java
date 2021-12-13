package com.bkhech.home.practice.designpattern.chain_of_responsibility;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author guowm
 * @date 2021/12/10
 */
@Data
@Builder
public class ApiSignContext {
    private String sign;
    private Map<String, String> paramMap;
    private RequestMetaData requestMetaData;
}
