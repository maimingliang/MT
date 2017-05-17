package com.elk.login;

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
public class WeixinPassWordBindBean implements Serializable {
    /**
     * code : ELK_000000
     * data : {"token":"16403015353"}
     */
    public String code;

    private String message;
    public Data data;

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

    public static class Data implements Serializable{
        /**
         * token : 16403015353
         */
        public String id;
        public String phoneNo;
        public String token;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }


        @Override
        public String toString() {
            return "Data{" +
                    "id='" + id + '\'' +
                    ", phoneNo='" + phoneNo + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WeixinPassWordBindBean{" +
                "code='" + code + '\'' +
                '}';
    }
}
