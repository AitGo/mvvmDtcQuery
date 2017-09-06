package com.xtool.mvvmdtcquery.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.xtool.mvvmdtcquery.R;
import com.xtool.mvvmdtcquery.adapter.DtcListAdapter;
import com.xtool.mvvmdtcquery.bean.DtcCustom;
import com.xtool.mvvmdtcquery.http.PostActivation;
import com.xtool.mvvmdtcquery.http.ServiceFactory;
import com.xtool.mvvmdtcquery.utils.RxBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends Activity implements MainView,View.OnClickListener {

    private MainPresenter presenter;

    private static final String TAG = "MainActivity";
    private Button btn_query;
    private EditText et_dcode;
    private ListView lv_dtc;
    private DtcListAdapter adapter;
    private List<DtcCustom> dtcCustomList = new ArrayList<DtcCustom>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        presenter = new MainPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unSubscribe();
    }

    private void initView() {
        btn_query = (Button) findViewById(R.id.btn_query);
        et_dcode = (EditText) findViewById(R.id.et_dcode);
        lv_dtc = (ListView) findViewById(R.id.lv_dtc);

        btn_query.setOnClickListener(this);
        adapter = new DtcListAdapter(this,dtcCustomList);
        lv_dtc.setAdapter(adapter);

        RxBus.getInstance().subscribe(String.class, new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                showListMessage((List)o);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                presenter.onClick();
                break;
        }
    }


    @Override
    public void showListMessage(List<DtcCustom> dtcCustoms) {
        adapter.setDtcCustomList(dtcCustoms);
        adapter.notifyDataSetChanged();
    }

    @Override
    public String getDcode() {
        return et_dcode.getText().toString();
    }
}
