package com.gy.bringmoney.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gy.bringmoney.R;
import com.gy.bringmoney.bean.BSort;
import com.gy.bringmoney.common.Constants;

import java.util.List;

/**
 * Created by xsl on 2018/1/14.
 */
public class SortEditAdapter extends RecyclerView.Adapter<SortEditAdapter.MyItemViewHolder> {

    private Context mContext;
    private List<BSort> mData;

    public SortEditAdapter(Context mContext, List<BSort> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public SortEditAdapter.MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_note_edit, parent, false);
        return new MyItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SortEditAdapter.MyItemViewHolder holder, int position) {
        holder.item_name.setText(mData.get(position).getSortName());
        Glide.with(mContext).load(Constants.BASE_URL + Constants.IMAGE_SORT + mData.get(position).getSortImg())
                .into(holder.item_img);
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    public static class MyItemViewHolder extends RecyclerView.ViewHolder {
        TextView item_name;
        ImageView item_img;

        MyItemViewHolder(View view) {
            super(view);
            item_img = (ImageView) view.findViewById(R.id.item_note_edit_iv);
            item_name = (TextView) view.findViewById(R.id.item_note_edit_tv);
        }
    }
}
