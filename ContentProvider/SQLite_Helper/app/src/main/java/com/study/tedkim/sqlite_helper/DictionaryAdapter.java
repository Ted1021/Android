package com.study.tedkim.sqlite_helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tedkim on 2017. 5. 25..
 */

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder> {

    Context mContext;
    int mItemLayout;
    ArrayList<WordItem> mDataSet = new ArrayList<>();
    LayoutInflater mInflater;

    public DictionaryAdapter(Context context, int itemLayout, ArrayList<WordItem> dataSet){

        mContext = context;
        mItemLayout = itemLayout;
        mDataSet = dataSet;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class DictionaryViewHolder extends RecyclerView.ViewHolder{

        TextView tvEng, tvKor;

        public DictionaryViewHolder(View itemView) {
            super(itemView);

            tvEng = (TextView) itemView.findViewById(R.id.textView_eng);
            tvKor = (TextView) itemView.findViewById(R.id.textView_kor);

        }
    }

    @Override
    public DictionaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(mItemLayout, parent, false);
        DictionaryViewHolder viewHolder = new DictionaryViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DictionaryViewHolder holder, int position) {

        String eng = mDataSet.get(position).eng;
        String kor = mDataSet.get(position).kor;

        holder.tvEng.setText(eng);
        holder.tvKor.setText(kor);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
