package com.xtool.mvvmdtcquery.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.xtool.mvvmdtcquery.Base.MyBaseAdapter;
import com.xtool.mvvmdtcquery.R;
import com.xtool.mvvmdtcquery.bean.DtcCustom;
import com.xtool.mvvmdtcquery.databinding.ItemDtcBinding;

import java.util.List;

/**
 * Created by xtool on 2017/9/2.
 */

public class DtcListAdapter extends MyBaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<DtcCustom> dtcCustomList;
    private Context context;
    private ItemDtcBinding binding;

    public DtcListAdapter(Context context,List<DtcCustom> dtcCustomList) {
        super(dtcCustomList);
        this.context = context;
        this.dtcCustomList = dtcCustomList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            binding = DataBindingUtil.inflate(mLayoutInflater,R.layout.item_dtc,parent,false);
        }else {
            binding = DataBindingUtil.getBinding(convertView);

        }
        binding.setDcode(dtcCustomList.get(position).getDcode());
        binding.setDname(dtcCustomList.get(position).getDname());
        binding.setDinfo(dtcCustomList.get(position).getDinfo());
        binding.setDcause(dtcCustomList.get(position).getDcause());
        binding.setDfix(dtcCustomList.get(position).getDfix());

        return binding.getRoot();
    }


}
