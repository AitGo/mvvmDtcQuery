package com.xtool.mvvmdtcquery.main;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.xtool.mvvmdtcquery.R;
import com.xtool.mvvmdtcquery.adapter.DtcListAdapter;
import com.xtool.mvvmdtcquery.bean.DtcCustom;
import com.xtool.mvvmdtcquery.databinding.ActivityMainBinding;
import com.xtool.mvvmdtcquery.databinding.ItemDtcBinding;
import com.xtool.mvvmdtcquery.utils.RxBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;


public class MainActivity extends Activity implements MainView {

    private MainPresenter presenter;
    private ActivityMainBinding binding;
    private final String TAG = this.getClass().getSimpleName();
    private ListView lv_dtc;
    private DtcListAdapter adapter;
    private List<DtcCustom> dtcCustomList = new ArrayList<DtcCustom>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        initView();
        presenter = new MainPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unSubscribe();
    }

    private void initView() {
        binding.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClick();
            }
        });
        lv_dtc = (ListView) findViewById(R.id.lv_dtc);
//        ItemDtcBinding binding1 = DataBindingUtil.setContentView(this,R.layout.item_dtc);
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
    public void showListMessage(List<DtcCustom> dtcCustoms) {
        dtcCustomList.clear();
        dtcCustomList.addAll(dtcCustoms);
        adapter.notifyDataSetChanged();
    }

    @Override
    public String getDcode() {
        return binding.etDcode.getText().toString();
    }
}
