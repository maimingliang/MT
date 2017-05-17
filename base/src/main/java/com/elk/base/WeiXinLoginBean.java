package com.elk.base;

/**
 * 类 名: WeiXinLoginBean
 * 说 明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright  2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2017/3/18
 * author lixuan
 */

public class WeiXinLoginBean {
    public static final int WEIXIN_HAS_ASSCOCIATE = 1;
    public static final int WEIXIN_NOT_ASSCOCIATE = 0;
    public static final int PASSWORD_HAS_SET = 1;
    public static final int PASSWORD_NOT_SET = 0;
    public static final String KEY_WEIXIN_CODE ="wei_xin_code";
    public static final String KEY_WEIXIN_ACCESS_TOKEN ="wei_xin_access_token";
    public static final String KEY_WEIXIN_REFRESH_ACCESS_CODE ="wei_xin_refresh_access_code";
    public static final String KEY_WEIXIN_OPEN_ID ="wei_xin_open_id";
    public static final String KEY_WEIXIN_UNIN_ID ="wei_xin_unin_id";


    /**
     * code : ELK_000000
     * data : {"id":"42a2c3f5-d8fc-43a7-9ac4-eca200531567","phoneNo":"13824363913","weChatStatus":1,"token":"ME05MDg1NDc1NzI2NTg0MzE5NDJhMmMzZjUtZDhmYy00M2E3LTlhYzQtZWNhMjAwNTMxNTY3N","passwdStatus":1}
     */

    private String code;

    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 42a2c3f5-d8fc-43a7-9ac4-eca200531567
         * phoneNo : 13824363913
         * weChatStatus : 1
         * token : ME05MDg1NDc1NzI2NTg0MzE5NDJhMmMzZjUtZDhmYy00M2E3LTlhYzQtZWNhMjAwNTMxNTY3N
         * passwdStatus : 1
         */

        private String id;
        private String phoneNo;
        private int weChatStatus;
        private String token;
        private int passwdStatus;

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

        public int getWeChatStatus() {
            return weChatStatus;
        }

        public void setWeChatStatus(int weChatStatus) {
            this.weChatStatus = weChatStatus;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getPasswdStatus() {
            return passwdStatus;
        }

        public void setPasswdStatus(int passwdStatus) {
            this.passwdStatus = passwdStatus;
        }
    }
}
