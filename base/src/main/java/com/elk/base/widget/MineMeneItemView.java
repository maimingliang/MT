package com.elk.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.elk.base.R;


/**
 * 类 名: MineMeneItemView
 * 说 明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright  2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2017/2/7
 * author lixuan
 */

public class MineMeneItemView extends RelativeLayout {
    private static final String TAG = "MineMeneItemView";
//    @BindView(R.id.iv_mine_menu_icon)
//    ImageView mIvMineMenuIcon;
//    @BindView(R.id.tv_mine_menu_name)
//    TextView mTvMineMenuName;
//    @BindView(R.id.iv_mine_menu_arrow)
//    ImageView mIvMineMenuArrow;
//    butterKniffe无法获得对象

    TextView mTvMineMenuName;
    ImageView mIvMineMenuIcon;;
    private String mMenuName;
    private int mIconRes;

    public MineMeneItemView(Context context) {
        super(context);
    }

    public MineMeneItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MineMenuAttrs);
        View view = View.inflate(context, R.layout.item_mine_menu_view, this);
        mTvMineMenuName= (TextView) view.findViewById(R.id.tv_mine_menu_name);
        mIvMineMenuIcon= (ImageView) view.findViewById(R.id.iv_mine_menu_icon);
        mMenuName = ta.getString(R.styleable.MineMenuAttrs_menutext);//定义menutext属性用于设置菜单名称
        Log.d(TAG, "MineMeneItemView: " + mMenuName);
        mIconRes = ta.getResourceId(R.styleable.MineMenuAttrs_menuIcon, R.drawable.icon_menu);//定义menuIcon属性用于设置菜单图标
        Log.d(TAG, "MineMeneItemView: getResourceId"+mIconRes);
        setViews();
    }

    private void setViews() {
      mIvMineMenuIcon.setImageResource(mIconRes);
        if (mTvMineMenuName != null) {
            mTvMineMenuName.setText(mMenuName);
        }
    }
}
