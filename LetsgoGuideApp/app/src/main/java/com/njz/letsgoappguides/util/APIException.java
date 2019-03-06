package com.njz.letsgoappguides.util;

/**
 * Created by Administrator on 2018/11/2.
 */

public class APIException extends RuntimeException{
    public int code;
    public String message;

    public APIException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
