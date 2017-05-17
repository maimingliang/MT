package com.elk.base;

/**
 * 类       名:
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2016/8/3
 * author   monster(tanxiang)
 */
public enum OutputCode {

    OK("ELK_000000", "处理成功"),
    PM("ELK_000001", "参数不对"),
    EX("ELK_000002", "系统错误"),
    TX("ELK_000003","登录已过期，请重新登录"),
    DX("ELK_000004","数据格式不对"),
    NX("ELK_000005","没有数据"),
    SCENIC_COLLECT("ELK_012001","您已经收藏过此景点，不能重复收藏"),
    GUIDE_COLLECT("ELK_012000","该向导已收藏"),
    GUIDE_LIKE("ELK_003010","该向导已收藏");



    private String code;


    private String message;

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

    OutputCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
