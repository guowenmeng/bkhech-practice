package com.bkhech.home.practice.designpattern.chain_of_responsibility;

import lombok.Getter;

/**
 * @author guowm
 * @date 2021/12/10
 */
@Getter
public class SignException extends RuntimeException {

    public SignException(String errorMessage) {
        super(errorMessage);
    }

}
