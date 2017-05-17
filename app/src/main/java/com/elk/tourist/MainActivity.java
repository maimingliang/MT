package com.elk.tourist;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;

import com.elk.tourist.bean.TabEtity;
import com.elk.tourist.fragment.MineFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;

import butterknife.BindView;
import rx.functions.Action1;

public class MainActivity extends WEActivity {

    public static final String HOME_CURRENT_TAB_POSITION="HOME_CURRENT_TAB_POSITION";
    public static final String MENU_SHOW_HIDE="MENU_SHOW_HIDE";
    public static final String NEWS_LIST_TO_TOP = "NEWS_LIST_TO_TOP";

    @Nullable
    @BindView(R.id.tab_layout)
    CommonTabLayout mTabLayout;

    private String[] mTitles = {"首页", "发现", "我的"};
    private int[] mIconUnselectIds = {
            R.drawable.home_page_a, R.drawable.find_a, R.drawable.person_a};
    private int[] mIconSelectIds = {
            R.drawable.home_a,R.drawable.find_select_a, R.drawable.person_seclect_a};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    //    private HomeFragment mHomeFragment;
    private MineFragment mHomeFragment;
    private MineFragment mFindFragment;
    private MineFragment mMineFragment;
    private int mTabLayoutMeasuredHeight;
    RxPermissions mRxPermissions;
//    @Override
//    protected void setupActivityComponent(AppComponent appComponent) {
//
//    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initTab();
        //        return LayoutInflater.from(this).inflate(R.layout.activity_main, null, false);
        mRxPermissions = new RxPermissions(this);

    }

    @Override
    protected void initData() {

        mRxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {

                    }
                });
    }

    @Override
    protected void initListener() {

    }

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initFragment(savedInstanceState);

        mTabLayout.measure(0,0);
        mTabLayoutMeasuredHeight = mTabLayout.getMeasuredHeight();

    }

    /**
     * @author caojiaxu
     * @date 2016/12/26 0026 下午 3:45
     * @desc 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEtity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mTabLayout.setTabData(mTabEntities);

        //添加切换监听
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchTo(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int curTabPosition = 0;
        if(savedInstanceState!=null){
            //            mHomeFragment = (HomeFragment)getSupportFragmentManager().findFragmentByTag("homeFragment");
            mHomeFragment = (MineFragment)getSupportFragmentManager().findFragmentByTag("homeFragment");
            mFindFragment = (MineFragment)getSupportFragmentManager().findFragmentByTag("findFragment");
            mMineFragment = (MineFragment)getSupportFragmentManager().findFragmentByTag("mineFragment");

            curTabPosition = savedInstanceState.getInt(HOME_CURRENT_TAB_POSITION);
        }else {
            //            mHomeFragment = new HomeFragment();
            mHomeFragment = new MineFragment();
            mFindFragment = new MineFragment();
            mMineFragment = new MineFragment();

            transaction.add(R.id.fl_body,mHomeFragment,"homeFragment");
            transaction.add(R.id.fl_body,mFindFragment,"findFragment");
            transaction.add(R.id.fl_body,mMineFragment,"mineFragment");

        }

        transaction.commit();
        switchTo(curTabPosition);
        mTabLayout.setCurrentTab(curTabPosition);
    }

    /**
     * @author caojiaxu
     * @date 2016/12/26 0026 下午 4:33
     * @desc 碎片切换
     */
    private void switchTo(int position) {
         FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();

        switch (position){
            //首页
            case 0:
                transaction.hide(mFindFragment);
                transaction.hide(mMineFragment);
                transaction.show(mHomeFragment);
                transaction.commitAllowingStateLoss();
                break;
            //发现
            case 1:
                transaction.hide(mHomeFragment);
                transaction.hide(mMineFragment);
                transaction.show(mFindFragment);
                transaction.commitAllowingStateLoss();
                break;
            //我的
            case 2:
                transaction.hide(mHomeFragment);
                transaction.hide(mFindFragment);
                transaction.show(mMineFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
