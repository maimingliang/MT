package com.elk.setting;

import com.elk.base.HttpResult;
import com.elk.base.mvp.BasePresenter;
import com.elk.base.rxError.core.RxErrorHandler;
import com.elk.base.rxError.handler.ErrorHandleSubscriber;
import com.elk.base.utils.RxUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import timber.log.Timber;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */


/**
 * /**
 * 类       名:
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2017
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2017/3/9
 * author   maimingliang
 */


public class SetUpPresenter extends BasePresenter<SetUpContract.Model, SetUpContract.View> {
    private RxErrorHandler mErrorHandler;


    public SetUpPresenter(SetUpContract.Model model, SetUpContract.View rootView
            , RxErrorHandler handler) {
        super(model, rootView);
        mModel = ((SetUpModel) model);
        this.mErrorHandler = handler;

    }

    public void check(){

        mModel.check(mRootView.getAppType(),mRootView.getVersionNum())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {

                    }
                })
                .compose(RxUtils.<AppUpdateBeen>bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<AppUpdateBeen>(mErrorHandler) {
                    @Override
                    public void onNext(AppUpdateBeen datas) {

                        Timber.tag(TAG).d(" thread name = " + Thread.currentThread().getName());

                        mRootView.returnVersionCheck(datas);
                    }


                });
    }

    public void logout(){
        mModel.logout("")
                .subscribeOn(Schedulers.io())  /*http请求线程*/
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //                        mRootView.showLoading();
                    }
                })
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        //                        mRootView.hideLoading();
                    }
                })
                .subscribe(new ErrorHandleSubscriber<HttpResult<String>>(mErrorHandler) {
                    @Override
                    public void onNext(HttpResult<String> data) {
                        mRootView.returnLogout(data);
                    }


                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }

}