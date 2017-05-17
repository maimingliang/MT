package com.elk.login;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

import com.elk.base.utils.UiUtils;


/**
 * 类 名: MaxTextLengthFilter
 * 说 明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright  2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2017/5/16
 * author lixuan
 */

public class MaxTextLengthFilter implements InputFilter {
    private Toast toast = null;
    private int mMaxLength;

    public MaxTextLengthFilter(int max) {
        mMaxLength = max;
        toast = Toast.makeText(UiUtils.getContext(),"输入字符不能超过"+ mMaxLength + "位",Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.TOP, 0, 235);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        int keep = mMaxLength - (dest.length() - (dend - dstart));
        if(keep < (end - start)){
            toast.show();
        }
        if(keep <= 0){
            return "";
        }else if(keep >= end - start){
            return null;
        }else{
            return source.subSequence(start,start + keep);
        }
    }
}
