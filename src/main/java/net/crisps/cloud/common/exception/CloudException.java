package net.crisps.cloud.common.exception;

import net.crisps.framework.tac.base.enums.ResultCode;
import net.crisps.framework.tac.base.exception.AbstractCrispsException;

import java.text.MessageFormat;

/**
 * @Description: TODO
 * @Author Created by yan.x on 2019-07-27 .
 **/
public class CloudException extends AbstractCrispsException {

    private Integer code = 5055;
    private String message;

    public CloudException() {
        super();
    }

    public CloudException(Throwable cause) {
        super(cause);
    }

    public CloudException(String message) {
        super(message);
        super.setCode(code);
        this.message = message;
    }


    public CloudException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        super.setCode(code);
        super.setMessage(message);
    }

    public CloudException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getCode(), resultCode.getMessage(), cause);
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public CloudException(String message, Throwable cause) {
        super(cause, message);
        super.setCode(code);
        this.message = message;
    }

    public CloudException(Integer code, String message) {
        super(code, message);
        this.code = code;
        this.message = message;
    }

    public CloudException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
        this.code = code;
        this.message = message;
    }

    public CloudException(String pattern, Object... params) {
        super(pattern, params);
        super.setCode(code);
        this.message = MessageFormat.format(pattern, params);
    }

    public CloudException(Throwable cause, String pattern, Object... params) {
        super(pattern, params, cause);
        super.setCode(code);
        this.message = MessageFormat.format(pattern, params);
    }
}
