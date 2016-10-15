package com.stevefat.novel.activitys.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stevefat.novel.R;
import com.stevefat.novel.bean.Catalog;
import com.stevefat.novel.interfaces.RecycleClick;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: ngh
 * date: 2016/10/12
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHodler> {
    List<Catalog> catalogs;
    private RecycleClick mClick;

    public void setClick(RecycleClick click) {
        mClick = click;
    }

    public void setDatas(List<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHodler(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyl_item, null));
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, final int position) {
        holder.txt.setText(catalogs.get(position).getName());
        holder.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick.onClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catalogs.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.txt)
        TextView txt;

        public ViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
