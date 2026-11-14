package com.dukla.base.rest;

import java.io.Serializable;

/**
 * 标准响应结果
 */
public class RestfulResponse<T> implements Serializable {

    private final String status;
    private final T val;
    private final String errCode;
    private final String errMsg;
    private final String extMsg;

    public String getStatus() {
        return status;
    }

    public T getVal() {
        return val;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public String getExtMsg() {
        return extMsg;
    }

    public static <T> Builder<T> builder(){
        return new Builder<>();
    }

    private RestfulResponse(Builder<T> builder){
        this.status = builder.status;
        this.val = builder.val;
        this.errCode = builder.errCode;
        this.errMsg = builder.errMsg;
        this.extMsg = builder.extMsg;
    }

    public static class Builder<T>{
        private String status;
        private T val;
        private String errCode;
        private String errMsg;
        private String extMsg;

        public RestfulResponse<T> build(){
            return new RestfulResponse<>(this);
        }

        public Builder<T> status(StatusCode statusCode){
            this.status = statusCode.name();
            return this;
        }

        public Builder<T> errCode(String errCode){
            this.errCode = errCode;
            return this;
        }

        public Builder<T> errMsg(String errMsg){
            this.errMsg = errMsg;
            return this;
        }

        public Builder<T> extMsg(String extMsg){
            this.extMsg = extMsg;
            return this;
        }

        public Builder<T> val(T val){
            this.val = val;
            return this;
        }
    }



    @Override
    public String toString() {
        return "{'status:'" + status + '\'' +
                ", val:" + val +
                ", errCode:'" + errCode + '\'' +
                ", errMsg:'" + errMsg + '\'' +
                ", extMsg:'" + extMsg + '\'' +
                '}';
    }

}
