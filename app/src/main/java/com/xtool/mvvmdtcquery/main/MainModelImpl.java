package com.xtool.mvvmdtcquery.main;

import android.util.Log;

import com.xtool.mvvmdtcquery.bean.DtcCustom;
import com.xtool.mvvmdtcquery.http.PostActivation;
import com.xtool.mvvmdtcquery.http.ServiceFactory;
import com.xtool.mvvmdtcquery.utils.RxBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xtool on 2017/9/5.
 */

public class MainModelImpl implements MainModel{


    @Override
    public Observable<List<DtcCustom>> getDtcCustom(String dcode) {

        final DtcCustom dtc = new DtcCustom();
        dtc.setDcode(dcode);
        return ServiceFactory.getInstance().createService(PostActivation.class)
                .postActivation(dtc, "queryDtcByDcodeJson.action");

    }
}
