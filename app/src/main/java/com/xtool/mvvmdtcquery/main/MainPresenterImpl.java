package com.xtool.mvvmdtcquery.main;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.xtool.mvvmdtcquery.bean.DtcCustom;
import com.xtool.mvvmdtcquery.http.PostActivation;
import com.xtool.mvvmdtcquery.http.ServiceFactory;
import com.xtool.mvvmdtcquery.utils.RxBus;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xtool on 2017/9/5.
 */

public class MainPresenterImpl implements MainPresenter {

    private String TAG = "MainPresenterImpl";
    private MainView view;
    private MainModel model;

    public MainPresenterImpl(MainView mainView) {
        this.view = mainView;
        model = new MainModelImpl();
    }

    @Override
    public void onClick() {
        String dcode = view.getDcode();
        model.getDtcCustom(dcode).subscribeOn(Schedulers.io())
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
