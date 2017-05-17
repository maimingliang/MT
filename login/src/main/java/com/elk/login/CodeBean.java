package com.elk.login;

/**
 * 类 名: CodeBean
 * 说 明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright  2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2017/2/16
 * author lixuan
 */

public class CodeBean {

    /*-------------公用-------------*/
  public static String SUCCESS = "ELK_000000";// 处理成功
    public static   String ERROR = "ELK_000002";// 处理成功
    public static  String TOKEN_TIMEOUT = "ELK_000003";// 处理成功
    public static   String PHONE_NULL = "ELK_002005"; //手机号不能为空
    public static   String PHONE_ERROR = "ELK_002006"; //请输入11位数字
    public static   String PAW_NULL = "ELK_003007"; //密码不能为空
    public static   String PHONE_NO_RESG = "ELK_002000"; //手机号未注册
    public static   String PHONE_HAS_RESG = "ELK_002001"; //手机号已注册
    public static  String PAW_ERROR = "ELK_003008"; //请输入6~15位任意字符
    /*-------------注册-------------*/
    public static  String COED_ERROR = "ELK_001001"; //您输入的验证码有误，请重新输入

    /*-------------登录-------------*/
    public static   String GUIDE_NO_VERIFY = "ELK_003002"; //向导注册过但未认证
    public static   String PHONE_PAW_ERROR = "ELK_003003";//手机号码或密码错误
    /*-------------是否收藏-------------*/
    public static   String GUIDE_IS_FAROURITED = "ELK_012000";
    public static  String UPLOAD_FAIlED = "ELK_007001";// 上传失败
    public static  String UPLOAD_NULL = "ELK_007000";// 上传文件为空

    public static   String SCENIC_FAVOUR_AGAIN="ELK_012001";// 不能重复添加收藏景点
    public static   String HOTEL_FAVOUR_AGAIN ="ELK_012002";//不能重复添加收藏民宿
    public static  String FOOD_FAVOUR_AGAIN ="ELK_012003";//不能重复添加收藏美食
    public static   String TRAVELS_FAVOUR_AGAIN ="ELK_012004";//不能重复添加收藏游记
    //================================吐槽=============================

    public static   String SPITSLOT_NEED_PICTURE ="ELK_012004";//    ELK_039000 最少上传一张图片
    public static   String SPITSLOT_NINE_PICTURE_MOST ="ELK_012004";//    ELK_039001 只允许上传9张吐槽图片
    public static   String SPITSLOT_EMPTY_CONTENT_ALTER ="ELK_012004";//    ELK_039002 吐槽内容不能为空
    public static   String SPITSLOT_DELET_OTHERS_ALTER ="ELK_012004";//    ELK_039002 对不起，只允许删除自己的吐槽
    /**
     * code : ELK_000003
     */

    private String code;
    private String message;

    public String getMessage() {
        return message;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
