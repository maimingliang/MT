package com.elk.tourist.mvp.presenter;


import com.elk.base.mvp.BasePresenter;
import com.elk.base.rxError.core.RxErrorHandler;
import com.elk.base.rxError.handler.ErrorHandleSubscriber;
import com.elk.tourist.bean.MineBean;
import com.elk.tourist.mvp.contract.MineContract;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */


/**
 * 类 名: 我的收益Presenter
 * 说 明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright  2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 *date   2017/2/8
 *author lixuan
 */


public class MinePresenter extends BasePresenter<MineContract.Model, MineContract.View> {
    private RxErrorHandler mErrorHandler;

    public MinePresenter(MineContract.Model mModel, MineContract.View rootView
            , RxErrorHandler handler) {
        super(mModel, rootView);
        this.mErrorHandler = handler;
    }

    public void my_1_0_0() {
        mModel.my_1_0_0()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(RxUtils.<MineBean>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(new ErrorHandleSubscriber<MineBean>(mErrorHandler) {
                    @Override
                    public void onNext(MineBean bean) {
                        mRootView.setData(bean.getData());
                    }
                });
    }

}