package com.xtool.mvpdtcquery.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.xtool.mvpdtcquery.R;
import com.xtool.mvpdtcquery.adapter.DtcListAdapter;
import com.xtool.mvpdtcquery.bean.DtcCustom;
import com.xtool.mvpdtcquery.utils.RxBus;
import com.xtool.mvpdtcquery.widget.ProgressDialog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;


public class MainActivity extends Activity implements MainView,View.OnClickListener {

    private MainPresenter presenter;

    private final String TAG = this.getClass().getSimpleName();
    private Button btn_query;
    private EditText et_dcode;
    private ListView lv_dtc;
    private ProgressDialog progressDialog;
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
        progressDialog = new ProgressDialog(this);

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
        dtcCustomList.clear();
        dtcCustomList.addAll(dtcCustoms);
        adapter.notifyDataSetChanged();

    }

    @Override
    public String getDcode() {
        return et_dcode.getText().toString();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }
}
