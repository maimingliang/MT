package com.elk.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SharedPreferencesUtil {

    private static final String NAME = "config";

    private static SharedPreferences sharedPreferences;

    /**
     * 设置 字符串
     *
     * @param key   键
     * @param value 值
     */
    public static void setString(Context context, String key, String value) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putString(key, value).apply();
    }

    /**
     * 获取 字符串
     *
     * @param key 键
     * @return 值
     */
    public static String getString(Context context, String key) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(key, null);
    }


    /**
     * 设置 布尔
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     */
    public static void setBoolean(Context context, String key, boolean value) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    /**
     * 获取 布尔
     *
     * @param context 上下文
     * @param key     键
     * @return 是否
     */
    public static boolean getBoolean(Context context, String key) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(key, false);
    }

    /**
     * 设置 整型
     *
     * @param key   键
     * @param value 值
     */
    public static void setInterger(Context context, String key, int value) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putInt(key, value).apply();
    }

    /**
     * 获取 整型
     *
     * @param key
     * @return
     */
    public static int getInterger(Context context, String key) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getInt(key, -1);
    }

    /**
     * 移除
     */
    public static void remove(Context context, String key) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().remove(key).apply();
    }

    /**
     * 清除
     */
    public static void clear(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().clear().apply();
    }

    /**
     * 设置
     *
     * @param key 键
     * @param t   泛型
     * @return 是否
     */
    public static <T> boolean set(Context context, String key, T t) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(t);
            String str = new String(Base64.encode(
                    baos.toByteArray(), Base64.DEFAULT));
            sharedPreferences.edit().putString(key, str).apply();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ignored) {
                //ignored
            }
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException ignored) {
                //ignored
            }
        }
    }

    /**
     * 获取
     *
     * @param key 键
     * @return 泛型
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(Context context, String key) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        T t = null;
        String str = sharedPreferences.getString(key, null);

        if (str == null) {
            return null;
        }
        byte[] base64 = Base64.decode(str.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais = null;
        ObjectInputStream bis = null;
        try {
            bais = new ByteArrayInputStream(base64);
            bis = new ObjectInputStream(bais);
            t = (T) bis.readObject();
        } catch (Exception ignored) {
            //ignored
        } finally {
            try {
                if (bais != null) {
                    bais.close();
                }
            } catch (IOException ignored) {
                //ignored
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException ignored) {
                //ignored
            }
        }
        return t;
    }
}
