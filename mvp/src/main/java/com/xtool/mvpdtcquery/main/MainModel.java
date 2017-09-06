package com.xtool.mvpdtcquery.main;


import com.xtool.mvpdtcquery.bean.DtcCustom;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xtool on 2017/9/5.
 */

public interface MainModel {

    public Observable<List<DtcCustom>> getDtcCustom(String dcode);
}
