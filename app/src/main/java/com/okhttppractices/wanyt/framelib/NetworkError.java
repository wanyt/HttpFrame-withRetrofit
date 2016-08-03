package com.okhttppractices.wanyt.framelib;

/**
 * Created on 2016/8/3 15:09
 * <p/>
 * author wanyt
 * <p/>
 * Description:
 */
public class NetworkError {

    private int code;
    private String msg;

    public NetworkError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "NetworkError{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

}
