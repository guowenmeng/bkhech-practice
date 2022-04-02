package com.bkhech.home.practice.designpattern.chain_of_responsibility.core;

import lombok.Builder;
import lombok.Data;

import java.lang.annotation.Annotation;

/**
 * @author guowm
 * @date 2021/12/10
 */
@Data
@Builder
public class RequestMetaData {
    private String[] parameterNames;
    private Object[] parameterValues;
    private Annotation[][] parameterAnnotations;
}
