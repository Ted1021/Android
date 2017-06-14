package com.study.tedkim.retrofit2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tedkim on 2017. 6. 14..
 */

public class ParkAdapter extends RecyclerView.Adapter<ParkAdapter.ViewHolder> {

    Context mContext;
    int mLayout;
    ArrayList<ParkData> mDataSet = new ArrayList<>();
    LayoutInflater mInflater;

    public ParkAdapter(Context context, int layout, ArrayList<ParkData> dataSet){

        mContext = context;
        mLayout = layout;
        mDataSet = dataSet;

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivParkImage;
        TextView tvParkName, tvParkAddress;
        LinearLayout lParkItem;

        public ViewHolder(View itemView) {
            super(itemView);

            tvParkName = (TextView) itemView.findViewById(R.id.textView_parkName);
            tvParkAddress = (TextView) itemView.findViewById(R.id.textView_parkAddress);

            ivParkImage = (ImageView) itemView.findViewById(R.id.imageView_parkImage);
            lParkItem = (LinearLayout) itemView.findViewById(R.id.liearLayout_parkItem);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(mLayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String imageUrl = mDataSet.get(0).getSearchParkInfoService().getRow()[position].getP_IMG();
        String parkName = mDataSet.get(0).getSearchParkInfoService().getRow()[position].getP_PARK();
        String parkAddress = mDataSet.get(0).getSearchParkInfoService().getRow()[position].getP_ADDR();

        holder.tvParkName.setText(parkName);
        holder.tvParkAddress.setText(parkAddress);

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
