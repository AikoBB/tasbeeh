package com.aigerimbb.android.tasbeeh.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.aigerimbb.android.tasbeeh.model.Tasbeeh;

import java.util.List;

/**
 * Created by Beishenbekova on 05.11.2016.
 */

public class ListTasbeehAdapter extends BaseAdapter {
    private Context context;
    private List<Tasbeeh> tasbeehList;

    public ListTasbeehAdapter(Context context, List<Tasbeeh> tasbeehList) {
        this.context = context;
        this.tasbeehList = tasbeehList;
    }

    @Override
    public int getCount() {
        return tasbeehList.size();
    }

    @Override
    public Object getItem(int position) {
        return tasbeehList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tasbeehList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
