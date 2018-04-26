package com.gy.bringmoney.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.gy.bringmoney.R;
import com.gy.bringmoney.activity.BillAddActivity;
import com.gy.bringmoney.activity.BillEditActivity;
import com.gy.bringmoney.bean.BSort;
import com.gy.bringmoney.common.Constants;

import java.util.List;

/**
 * 账单分类Adapter（AddBillAdapter)
 */
public class BookNoteAdapter extends RecyclerView.Adapter<BookNoteAdapter.ViewHolder> {

    private BillAddActivity mContext;
    private BillEditActivity eContext;
    private LayoutInflater mInflater;
    private List<BSort> mDatas;

    private OnBookNoteClickListener onBookNoteClickListener;

    public void setmDatas(List<BSort> mDatas) {
        this.mDatas = mDatas;
    }

    public BookNoteAdapter(BillAddActivity context, List<BSort> datas) {
        this.mContext = context;
        this.eContext = null;
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = datas;

    }

    public BookNoteAdapter(BillEditActivity context, List<BSort> datas) {
        this.mContext = null;
        this.eContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = datas;

    }

    public void setOnBookNoteClickListener(OnBookNoteClickListener listener) {
        if (onBookNoteClickListener == null)
            this.onBookNoteClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return (mDatas == null) ? 0 : mDatas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_tb_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(mDatas.get(position).getSortName());
        if (eContext == null) {
            Glide.with(mContext).load(Constants.BASE_URL + Constants.IMAGE_SORT + mDatas.get(position).getSortImg())
                    .into(holder.img);
        } else {
            Glide.with(eContext).load(Constants.BASE_URL + Constants.IMAGE_SORT + mDatas.get(position).getSortImg())
                    .into(holder.img);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView title;
        private ImageView img;

        public ViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.item_tb_type_tv);
            img = (ImageView) view.findViewById(R.id.item_tb_type_img);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onBookNoteClickListener.OnClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            onBookNoteClickListener.OnLongClick(getAdapterPosition());
            return false;
        }
    }

    /**
     * 自定义分类选择接口
     */
    public interface OnBookNoteClickListener {
        void OnClick(int index);

        void OnLongClick(int index);
    }

}
