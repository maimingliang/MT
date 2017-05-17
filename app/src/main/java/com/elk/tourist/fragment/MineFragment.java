package com.elk.tourist.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.elk.base.BaseApplication;
import com.elk.base.utils.UserUtil;
import com.elk.base.widget.CircleImageView;
import com.elk.base.widget.MineMeneItemView;
import com.elk.tourist.R;
import com.elk.tourist.WEFragment;
import com.elk.tourist.bean.MineBean;
import com.elk.tourist.mvp.contract.MineContract;
import com.elk.tourist.mvp.model.MineModel;
import com.elk.tourist.mvp.presenter.MinePresenter;

import butterknife.BindView;

/**
 * 类       名:
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2017/01/16 上午 11:44
 * author   caojiaxu
 */
public class MineFragment extends WEFragment<MinePresenter> implements View.OnClickListener, MineContract.View {
    @BindView(R.id.iv_user_head_icon)
    CircleImageView mIvUserHeadIcon;
    @BindView(R.id.tv_mine_user_name)
    TextView mTvMineUserName;
    @BindView(R.id.tv_mine_user_label)
    TextView mTvMineUserLabel;
    @BindView(R.id.rl_mine_user_info)
    RelativeLayout mRlMineUserInfo;
    @BindView(R.id.mine_income)
    MineMeneItemView mMineIncome;
    @BindView(R.id.tv_mine_user_unsettledMoney)
    TextView mTvMineUserUnsettledMoney;
    @BindView(R.id.mine_guide_orders)
    MineMeneItemView mMineGuideOrders;
    @BindView(R.id.mine_travel_note)
    MineMeneItemView mMineTravelNote;
    @BindView(R.id.mine_spitslot)
    MineMeneItemView mMineSpitslot;
    @BindView(R.id.mine_diary)
    MineMeneItemView mMineDiary;
    @BindView(R.id.mine_collection)
    MineMeneItemView mMineCollection;
    @BindView(R.id.img_mine_setup)
    ImageView imgMineSetUp;
    @BindView(R.id.rl_user_info_not_login)
    RelativeLayout mRlUserInfoNotLogin;
    @BindView(R.id.rl_user_info_has_login)
    RelativeLayout mRlUserInfoHasLogin;
    @BindView(R.id.mine_wen_wen)
    MineMeneItemView mMineWenWen;
    private MineBean.DataEntity mData;

//    @Subscriber(tag = LoginActivity.EVENT_KEY_ISLOGIN)
//    public void onLoginSuccussEvent(boolean event) {
//        //登录成功后通知刷新
//        if (event) {
//            if (UserUtil.isStored()) {
//                mPresenter.my_1_0_0();
//            }
//        }
//    }

//    @Subscriber(tag = "modify_user_succuss")
//    public void onModifyUserEvent(boolean event) {
////        个人主页通知
//        if (!event) {
//            return;
//        }
//        if (UserUtil.isStored()) {
//            mPresenter.my_1_0_0();
//        }
//    }

//    @Subscriber(tag = "modify_user_name_succuss")
//    public void onModifyUserNameEvent(String patName) {
////        个人主页修改昵称后通知刷新
//        if (patName == null) {
//            return;
//        }
//        if (UserUtil.isStored()) {
//            mTvMineUserName.setText(patName + "");
//        }
//    }

    @Override
    protected void setupFragmentComponent(Object s) {
         mPresenter = new MinePresenter(new MineModel(null,null),this, BaseApplication.getRxErrorHandler());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        //       return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_mine, null, false);
    }

    @Override
    protected void initData() {
        if (UserUtil.isStored()) {
            mPresenter.my_1_0_0();
        } else {
//            查看是否有缓存在本地的登录信息
            UserUtil.get();
            if (UserUtil.isStored()) {
                mPresenter.my_1_0_0();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (UserUtil.isStored()) {
            mRlUserInfoHasLogin.setVisibility(View.VISIBLE);
            mRlUserInfoNotLogin.setVisibility(View.GONE);
        } else {
            mRlUserInfoHasLogin.setVisibility(View.GONE);
            mRlUserInfoNotLogin.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void initListener() {
        mRlMineUserInfo.setOnClickListener(this);
        mMineIncome.setOnClickListener(this);//我的收益
        mMineGuideOrders.setOnClickListener(this);//我的向导订单
        mMineTravelNote.setOnClickListener(this);//旅行志
        mMineSpitslot.setOnClickListener(this);//我的吐槽
        mMineWenWen.setOnClickListener(this);//我的问问
        mMineDiary.setOnClickListener(this);//我的游记
        mMineCollection.setOnClickListener(this);//我的收藏
        imgMineSetUp.setOnClickListener(this); //设置
    }


    @Override
    public void onClick(View v) {
        if (UserUtil.isStored()) {
            switch (v.getId()) {
                case R.id.rl_mine_user_info://个人资料
                    ARouter.getInstance().build("/touristUser/mineUserInfo").navigation();
                    break;
                case R.id.mine_income://我的收益
                    ARouter.getInstance().build("/touristAcc/mineIncome").navigation();
                    break;
                case R.id.mine_guide_orders://向导订单
                    ARouter.getInstance().build("/touristGuide/WizardOrderActivity").navigation();
                    break;
                case R.id.mine_travel_note://个人旅行志
                    ARouter.getInstance().build("/travelNote/MineTrourisNoteActivity").navigation();
                    break;
                case R.id.mine_wen_wen://我的问问
                    ARouter.getInstance().build("/mineWenwen/mineWenwenActivity").navigation();
                  break;
                case R.id.mine_spitslot://我的吐槽
                    ARouter.getInstance().build("/mine/MineSpitslotActivity").navigation();
                    break;
                case R.id.mine_diary://我的游记
                    ARouter.getInstance().build("/mine/MineTravelsActivity").navigation();
//                    ARouter.getInstance().build("/wenwen/QuestionDetailActivity").navigation();

                    break;
                case R.id.mine_collection://我的收藏
                    ARouter.getInstance().build("/touristCollection/mineCollection").navigation();
                    break;
                case R.id.img_mine_setup:
                    ARouter.getInstance().build("/user/SetUpActivity").navigation();
                    break;
            }
        } else {
            ARouter.getInstance().build("/touristUser/login").navigation();
        }
    }



    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }

    /**
     * 个人信息
     *
     * @param data
     */
    @Override
    public void setData(MineBean.DataEntity data) {
        if (data != null) {
            mData = data;
            String url = "";
            MineBean.DataEntity.FileExtEntity fileInfo = data.getFileExt();
            if (fileInfo != null) {
//                url = UrlFileInfo.PREVIEW + fileInfo.getPath();
            }
//            GlideHelper.getInstance().displayHeaderPic(url, mIvUserHeadIcon);
            mTvMineUserUnsettledMoney.setText("¥" + data.getUnsettledMoney());
            mTvMineUserName.setText(data.getPetName() + "");
            if (data.getTguStatus() != null) {
                if (data.getTguStatus() == 5) {
                    mTvMineUserLabel.setVisibility(View.VISIBLE);
                    mTvMineUserLabel.setText("实名向导");
                    mTvMineUserLabel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Intent intent = new Intent(UiUtils.getContext(), GuideDetailsActivity.class);
////                intent.putExtra("touristUserId",mUserData.getId());
//                            intent.putExtra("touristGuideId", mData.getTguId());
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            UiUtils.getContext().startActivity(intent);
                        }
                    });
                } else {
                    mTvMineUserLabel.setText("普通用户");
                    mTvMineUserLabel.setVisibility(View.VISIBLE);
                }
            }
        }
    }


}
