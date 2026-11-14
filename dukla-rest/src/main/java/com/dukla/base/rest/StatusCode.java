package com.dukla.base.rest;

/**
 * 响应状态码
 */
public enum StatusCode {
    SUCCEED("SUCCEED", "操作成功"),
    FAILED("FAILED", "操作失败"),
    ;

    private String code;
    private String msg;

    StatusCode(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
