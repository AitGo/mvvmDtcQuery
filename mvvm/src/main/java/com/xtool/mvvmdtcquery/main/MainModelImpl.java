package com.xtool.mvvmdtcquery.main;


import com.xtool.mvvmdtcquery.bean.DtcCustom;
import com.xtool.mvvmdtcquery.http.PostActivation;
import com.xtool.mvvmdtcquery.http.ServiceFactory;

import java.util.List;

import io.reactivex.Observable;


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
