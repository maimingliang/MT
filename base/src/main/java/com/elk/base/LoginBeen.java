package com.elk.base;


import java.io.Serializable;

/**
 * 类       名:
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2016/8/22 18:00
 * author caojiaxu
 */

public class LoginBeen implements Serializable {


    /**
     * code : ELK_000000
     * data : {"id":"a3a57851-3dd6-4cd3-95df-b05f5af656fe","phoneNo":"15018155281","token":"ODc5NDBiMjItZDAyMC00MzA4LWIxYzQtMTNjYzlkNDFjM2NlMTUwMTgxNTUyODE=N"}
     */
    private String code;
    private String message;
    private DataBeen data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBeen getData() {
        return data;
    }

    public void setData(DataBeen data) {
        this.data = data;
    }

    public static class DataBeen implements Serializable{
        /**
         * id : a3a57851-3dd6-4cd3-95df-b05f5af656fe
         * phoneNo : 15018155281
         * token : ODc5NDBiMjItZDAyMC00MzA4LWIxYzQtMTNjYzlkNDFjM2NlMTUwMTgxNTUyODE=N
         */
        public String id;
        public String phoneNo;
        public String token;

        @Override
        public String toString() {
            return "DataBeen{" +
                    "id='" + id + '\'' +
                    ", phoneNo='" + phoneNo + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getId() {
            return id;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public String getToken() {
            return token;
        }
    }
}
