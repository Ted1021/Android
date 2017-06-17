package com.study.tedkim.retrofit2;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by tedkim on 2017. 6. 14..
 */

public class ParkAdapter extends RecyclerView.Adapter<ParkAdapter.ViewHolder> {

    Context mContext;
    int mLayout;
    ArrayList<ParkData.Row> mDataSet = new ArrayList<>();
    LayoutInflater mInflater;

    ArrayList<String> mImageSet = new ArrayList<>(Arrays.asList("http://www.yamanashi-kankou.jp/foreign/korean/spot/images/4062_01.jpg",
            "https://us.123rf.com/450wm/samarttiw/samarttiw1510/samarttiw151000021/46755280-큰-도시-공원의-녹색-잔디-필드.jpg?ver=6",
            "http://img.etoday.co.kr/pto_db/2015/11/20151113093448_752102_600_400.jpg",
            "http://cfile24.uf.tistory.com/image/1855DD4250B4A47C184964",
            "http://heraldk.com/wp-content/uploads/2016/06/20160630000014_0.jpg"));

    public ParkAdapter(Context context, int layout, ArrayList<ParkData.Row> dataSet) {

        mContext = context;
        mLayout = layout;
        mDataSet = dataSet;

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivParkImage;
        TextView tvParkName, tvParkAddress;
        RelativeLayout lParkItem;

        public ViewHolder(View itemView) {
            super(itemView);

            tvParkName = (TextView) itemView.findViewById(R.id.textView_parkName);
            tvParkAddress = (TextView) itemView.findViewById(R.id.textView_parkAddress);

            ivParkImage = (ImageView) itemView.findViewById(R.id.imageView_parkImage);
            lParkItem = (RelativeLayout) itemView.findViewById(R.id.layout_parkItem);
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

        String imageUrl = mDataSet.get(position).getP_IMG();
        String parkName = mDataSet.get(position).getP_PARK();
        String parkAddress = mDataSet.get(position).getP_ADDR();

        Log.e("CHECK_VIEW", "-------------------" + imageUrl);

        holder.tvParkName.setText(parkName);
        holder.tvParkAddress.setText(parkAddress);
        Glide.with(mContext).load(mImageSet.get(position%5)).into(holder.ivParkImage);
        holder.ivParkImage.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);

}

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
