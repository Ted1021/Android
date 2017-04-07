package com.study.tedkim.intent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by tedkim on 2017. 4. 6..
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ArticleViewHolder> {

    Context mContext;
    int mLayout;
    ArrayList<Article> mDataSet = new ArrayList<>();
    LayoutInflater mInflater;

    OnRecyclerViewItemClickListener mListener;

    public RecyclerViewAdapter(Context context, int layout, ArrayList<Article> dataSet) {

        mContext = context;
        mLayout = layout;
        mDataSet = dataSet;

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mListener = (OnRecyclerViewItemClickListener) mContext;

    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvName;

        public ArticleViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.textVIew_title);
            tvName = (TextView) itemView.findViewById(R.id.textView_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    mListener.onItemSelected(position);
                }
            });

            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int mPosition = getLayoutPosition();

                    Toast.makeText(mContext, mDataSet.get(mPosition).getTitle(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = mInflater.inflate(mLayout, parent, false);
        ArticleViewHolder viewHolder = new ArticleViewHolder(convertView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {

        String title = mDataSet.get(position).getTitle();
        String name = mDataSet.get(position).getName();

        holder.tvTitle.setText(title);
        holder.tvName.setText(name);
    }

    @Override
    public int getItemCount() {

        return mDataSet.size();
    }

    public interface OnRecyclerViewItemClickListener {

        void onItemSelected(int position);
    }

}
