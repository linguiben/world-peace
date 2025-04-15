package com.jupiter.mvc.exception;

import lombok.Getter;

/**
 * @desc: BizException
 * @author: Jupiter.Lin
 * @date: 2025/1/25
 */
@Getter
public class BizException extends RuntimeException {
    private String errorCode;
    private String message;

    public BizException(String message) {
        super(message);
    }

    public BizException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
