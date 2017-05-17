package com.elk.base.utils;


import com.elk.base.LoginBeen;

/**
 * 类       名:
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   1.0.0
 * date   2017/2/20
 * author   tanxiang(monster)
 */
public class UserUtil {

    private static final String USER_DATA="USER_DATA";

    private static LoginBeen.DataBeen user;

    /**
     * 是否已存储
     *
     * @return 是否
     */
    public static boolean isStored() {
        return user != null;
    }

    /**
     * 存储
     *
     * @param user 用户
     */
    public static LoginBeen.DataBeen store(LoginBeen.DataBeen user) {
        UserUtil.user = user;
        SharedPreferencesUtil.set(UiUtils.getContext(),
                USER_DATA, user);
        return user;
    }

    /**
     * 获取
     *
     * @return 用户
     */
    public static LoginBeen.DataBeen get() {
        if(user == null) {
            user = SharedPreferencesUtil.get(UiUtils.getContext(),
                    USER_DATA);
        }
        return user;
    }

    /**
     * 获取 Id
     *
     * @return 用户
     */
    public static String getId() {
        get();

        if(user == null){
            return null;
        }
        return user.getId();
    }

    /**
     * 获取 手机号
     *
     * @return 用户
     */
    public static String getPhoneNo() {
        get();
        return user.getPhoneNo();
    }

    /**
     * 获取 令牌
     *
     * @return 用户
     */
    public static String getToken() {
        get();
        if(user == null){
            return  null;
        }
        return user.getToken();
    }

    /**
     * 清除
     */
    public static void clear() {
        user = null;
        SharedPreferencesUtil.remove(UiUtils.getContext(), USER_DATA);
    }
}
