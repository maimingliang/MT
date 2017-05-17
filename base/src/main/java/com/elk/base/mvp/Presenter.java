package com.elk.base.mvp;

import rx.Subscription;

public interface Presenter {

    void onStart();

    void onDestroy();

    void unSubscribe(Subscription subscription);
}
