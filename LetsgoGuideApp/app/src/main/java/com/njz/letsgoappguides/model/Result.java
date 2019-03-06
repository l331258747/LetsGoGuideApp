package com.njz.letsgoappguides.model;
import com.njz.letsgoappguides.constant.Constant;

/**
 * Created by Administrator on 2018/11/2.
 */

public class Result<T> {

    private int code;
    private T data;
    private String msg;

    private int errno;
    private String errmsg;
    private String url;

    public Result(int code, T data) {
        this.code = code;
        this.data = data;
    }

    //添加对返回状态成功的判断
    public boolean isSuccess() {
        return code == Constant.SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public int getErrno() {
        return errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", errno=" + errno +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
