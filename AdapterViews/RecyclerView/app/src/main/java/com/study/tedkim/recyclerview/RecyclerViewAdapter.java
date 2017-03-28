package com.study.tedkim.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by tedkim on 2017. 3. 28..
 */

// RecyclerView 를 위한 Custom Adapter 생성 - RecyclerView.Adapter 를 상속 받으며, generic 으로는 직접 Custom 한 ViewHolder 를 끼워 넣는다.
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    /** Adapter 선언을 위한 4가지 구성요소 **/
    Context mContext;   // RecyclerView 가 있는 Context
    int mItemLayout;    // RecyclerView 에 끼워 넣을 Item Layout
    ArrayList<ListItem> mDataSet = new ArrayList<>();   // Item Layout 에 bind 될 DataSet

    LayoutInflater mInflater;   // Item Layout 을 구체화 시켜 줄 Inflater

    // RecyclerViewAdapter 의 초기화는 기존의 CustomAdapter 를 초기화하는 방법과 완전히 동일하다.
    public RecyclerViewAdapter(Context context, int itemLayout, ArrayList<ListItem> dataSet){

        // 1. RecyclerView 가 위치한 Activity (또는 Fragment) 의 Context 를 가져온다.
        mContext = context;
        // 2. 준비 된 Item Layout 을 가져온다.
        mItemLayout = itemLayout;
        // 3. Activity (또는 Fragment) 로 부터 전달받은 DataSet 을 세팅한다.
        mDataSet = dataSet;
        // 4. 전달받은 Context 를 이용해 system Inflater 를 가져온다.
        mInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    // 재활용 할 Item Layout 을 불러와 저장한다.
    // OnCreateViewHolder 에서는 ViewHolder 를 생성하기만 할 뿐,
    // 실제 Layout 의 Matching 은 ViewHolder 의 생성자에서 처리해 주어야 한다.
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgIcon;
        TextView tvName, tvDesc;

        // 1. 생성자에서 Inflate 된 itemView 를 전달받아 저장해 둔다.
        public ViewHolder(View itemView) {
            super(itemView);

            // Adapter 가 생성 될 때, 단 한번만 아래의 작업을 진행 함으로써
            // List 의 Item 들을 inflate 할 때의 비용을 획기적으로 줄일 수 있다.
            imgIcon = (ImageView) itemView.findViewById(R.id.imageView_icon);
            tvName = (TextView) itemView.findViewById(R.id.textView_name);
            tvDesc = (TextView) itemView.findViewById(R.id.textView_desc);
        }
    }

    // 기존의 ListView 에서 getView() 메소드의 역할을 두가지로 분할 해 두었다.
    // 첫째가 onCreateViewHolder() - Item Layout Components 들을 Inflate 한다.
    // 둘째가 onmBindViewHolder() - Inflate 된 Item 들에 준비된 DataSet 들을 Binding 한다.

    // 기존의 convertView 에서 하던 방식과 같이 일단 준비 된 layout 을 inflate 한다.
    // 이후에 이 작업을 반복적으로 수행하지 않기 위해, ViewHolder 를 생성해 inflate 한 View 로 초기화 한다.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 1. Item Layout 을 inflate
        View itemView = mInflater.inflate(mItemLayout, parent, false);
        // 2. inflate 된 View 를 ViewHolder 에 넣어 초기화
        ViewHolder viewHolder = new ViewHolder(itemView);

        // 3. 완성된 ViewHolder 를 기존의 convertView 처럼 사용하면 된다!
        return viewHolder;
    }

    // onCreateViewHolder() 에서 반환 된 viewHolder 를 이용해 준비 된 DataSet 을 Binding 한다.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // 1. DataSet 불러오기
        String planetName = mDataSet.get(position).getName();
        String planetDesc = mDataSet.get(position).getDesc();


        // 2. 가져온 DataSet 을 전달받은 viewHolder 에 binding
        holder.tvName.setText(planetName);

        // 2.1 준비된 DESC 데이터가 너무 길어 50자로 잘라냈다.
        if(planetDesc.length() >= 50){
            planetDesc = planetDesc.substring(0,50);
            planetDesc +=  " ... ";
        }
        holder.tvDesc.setText(planetDesc);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
