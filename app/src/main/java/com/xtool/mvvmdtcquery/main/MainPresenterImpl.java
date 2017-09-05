package com.xtool.mvvmdtcquery.main;

import android.content.Context;
import android.util.Log;

import com.xtool.mvvmdtcquery.bean.DtcCustom;
import com.xtool.mvvmdtcquery.http.PostActivation;
import com.xtool.mvvmdtcquery.http.ServiceFactory;
import com.xtool.mvvmdtcquery.utils.RxBus;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xtool on 2017/9/5.
 */

public class MainPresenterImpl implements MainPresenter {

    private Context context;
    private String TAG = "MainPresenterImpl";
    private MainView view;

    public MainPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void onClick() {
        RxBus.getInstance().send("发送事件");
        String dcode = view.getDcode();
        DtcCustom dtc = new DtcCustom();
        dtc.setDcode(dcode);
        ServiceFactory.getInstance().createService(PostActivation.class)
                .postActivation(dtc,"queryDtcByDcodeJson.action")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<DtcCustom>>() {
                    @Override
                    public void onNext(@NonNull List<DtcCustom> dtcCustoms) {
                        Log.e(TAG,"onNext");
                        for(DtcCustom dtcCustom : dtcCustoms) {
                            Log.e(TAG,dtcCustom.getDname());
                        }
                        view.showListMessage(dtcCustoms);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG,"onError");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG,"onComplete");
                    }
                });
    }
}
