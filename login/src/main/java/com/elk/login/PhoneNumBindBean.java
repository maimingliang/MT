package com.elk.login;

import java.io.Serializable;

/**
 * 类 名: PhoneNumBindBean
 * 说 明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright  2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2017/3/20
 * author lixuan
 */

public class PhoneNumBindBean {
    /**
     * code : ELK_000000
     * data : {"token":"16403015353"}
     */
    public String code;


    private String message;

    public Data data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }



    public static class Data implements Serializable {
        /**
         * token : 16403015353
         */
        public String id;
        public String token;
        public String phoneNo;
        public int weChatStatus;
        public int passwdStatus;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getWeChatStatus() {
            return weChatStatus;
        }

        public void setWeChatStatus(int weChatStatus) {
            this.weChatStatus = weChatStatus;
        }

        public int getPasswdStatus() {
            return passwdStatus;
        }

        public void setPasswdStatus(int passwdStatus) {
            this.passwdStatus = passwdStatus;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id='" + id + '\'' +
                    ", token='" + token + '\'' +
                    ", phoneNo='" + phoneNo + '\'' +
                    ", weChatStatus=" + weChatStatus +
                    ", passwdStatus=" + passwdStatus +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PhoneNumBindBean{" +
                "code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
