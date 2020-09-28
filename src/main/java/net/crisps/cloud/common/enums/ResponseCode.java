package net.crisps.cloud.common.enums;


import net.crisps.framework.tac.base.constant.ResultPool;
import net.crisps.framework.tac.base.enums.ResultCode;

/**
 * @Description: 返回状态枚举类
 * @Author Created by yan.x on 2020-01-08 .
 **/
public enum ResponseCode implements ResultCode {
    /**
     *
     */
    SUCCESS(ResultPool.REQUEST_SUCCESS_CODE, "操作成功"),
    UNKNOW_ERROR(ResultPool.UNKNOW_ERROR_CODE, "服务异常,请稍后再试!"),
    FEIGN_FAILURE(ResultPool.FEGIN_ERROR_CODE, "服务调用异常,请稍后再试!"),
    VALIDATE_ERROR(ResultPool.VALIDATE_ERROR_CODE, "参数验证异常"),
    NOT_FOUND(ResultPool.NOT_FOUND_ERROR_CODE, "资源不存在"),
    INVOKE_FAILURE(ResultPool.INVOKE_FAILURE_ERROR_CODE, "访问失败"),
    ACCESS_DENIED_ERROR(ResultPool.ACCESS_DENIED_ERROR_CODE, "访问受限"),
    PARAMETER_ERROR(ResultPool.PARAMETER_ERROR_CODE, "请求参数有误");

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
