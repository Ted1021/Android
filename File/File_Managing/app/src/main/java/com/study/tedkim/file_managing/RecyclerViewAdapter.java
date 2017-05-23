package com.study.tedkim.file_managing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.study.tedkim.file_managing.MainActivity.EVENT;
import static com.study.tedkim.file_managing.MainActivity.mCurrentPath;
import static com.study.tedkim.file_managing.MainActivity.mHandler;

/**
 * Created by tedkim on 2017. 5. 23..
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context mContext;
    int mLayout;
    ArrayList<String> mDataSet = new ArrayList<>();
    LayoutInflater mInflater;

    public RecyclerViewAdapter(Context context, int layout, ArrayList<String> dataSet){

        mContext = context;
        mLayout = layout;
        mDataSet = dataSet;

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvIndex, tvDir;
        LinearLayout item;

        public ViewHolder(View itemView) {
            super(itemView);

            tvIndex = (TextView) itemView.findViewById(R.id.textView_index);
            tvDir = (TextView) itemView.findViewById(R.id.textView_dir);

            item = (LinearLayout) itemView.findViewById(R.id.linearLayout_item);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(mLayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvIndex.setText(position+"");
        holder.tvDir.setText(mDataSet.get(position));
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO - item click listener interface 구현 해 볼 것.
                // TODO - 이걸 Handler 로 하다니 ㅋㅋㅋㅋㅋㅋㅋ

                String path = mDataSet.get(position);
                if(path.startsWith("[") && path.endsWith("]")){

                    path = path.substring(1, path.length()-1);
                    mCurrentPath = mCurrentPath + "/" + path;
                    mHandler.sendEmptyMessage(EVENT);
                }
                else
                    Toast.makeText(mContext, "this is not DIRECTORY..."  , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
