package com.xtool.mvpdtcquery.main;



import com.xtool.mvpdtcquery.bean.DtcCustom;
import com.xtool.mvpdtcquery.http.PostActivation;
import com.xtool.mvpdtcquery.http.ServiceFactory;

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
