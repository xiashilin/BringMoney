package com.gy.bringmoney.bean;

/**
 * Created by xsl on 2018/4/14.
 * 通用javabean
 */

public class BaseBean {
    /**
     * status : 100
     * message : 成功！
     */

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}