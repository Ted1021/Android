package com.study.tedkim.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tedkim on 2017. 3. 26..
 */

public class CustomAdapter extends BaseAdapter{

    /** Custom Adapter 를 구현하기 위한 필수 구성요소 4가지 **/

    // 1. ListView 가 장착 될 Context - Inflater 를 불러오기 위함.
    Context mContext;
    // 2. ListView 의 Item 하나에 해당하는 CustomLayout - 이게 목적.
    int mLayout;
    // 3. ListView 에 들어갈 DataSet
    ArrayList<PlanetItem> mDataSet;
    // 4. "itemLayout" 을 "convertView" 로 만들어줄 LayoutInflater
    LayoutInflater mInflater;

    TextView tvName;
    String mName;

    // Custom Adatper 의 생성자를 이용해 초기 세팅을 진행한다.
    public CustomAdapter(Context context, int layout, ArrayList<PlanetItem> dataSet){

        // 1. Activity(또는 Fragment)로 부터 전달받은, context, itemLayout, dataSet 을 초기화 해 준다.
        mContext = context;
        mLayout = layout;
        mDataSet = dataSet;

        // 2. Inflater 의 경우, Activity 로 부터 전달받은 context 로 부터 Inflater 를 가져온다.
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /** BaseAdapter 를 상속받으면 구현 할 수 있는 Override 함수 4 가지 **/

    // getCount : ListView 의 Item 개수를 return 한다.
    @Override
    public int getCount() {
        return mDataSet.size();
    }
    // getItem : ListView 의 특정 Item 하나에 대한 정보를 return 한다.
    @Override
    public Object getItem(int position) {
        return mDataSet.get(position);
    }
    // getItemId : ListView 의 특정 Item 하나의 position 을 return 한다.
    @Override
    public long getItemId(int position) {
        return position;
    }

    // TODO - getView : 이게 완전 중요하다!!!!
    // Adapter 의 본래 역할인 "레이아웃과 데이터셋의 연결" 을 이 부분에서 수행한다.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. ConvertView 생성
        // convertView 의 역할 - itemLayout 이 inflate 되어 만들어진 하나의 view 이다.

        // 1.1 ConvertView 가 없다면, (convertView 는 최초에는 아무것도 연결되지 않은 null 상태이다.)
        if(convertView == null){
            // 1.2 각종 component 들을 연결해 준다.
            // 1) inflate 할 ItemLayout (mLayout)
            // 2) 각 Item 들이 삽입 될 ListView (parent)
            // 3) flag 값.... 이건 잘 모르겠다. - attachToRoot 라는데 ... 글쎄 ;;
            convertView = mInflater.inflate(mLayout, parent, false);
        }

        // 2. 해당 position 에 필요한 Data 세팅
        mName = mDataSet.get(position).name;
        // 3. convertView 에 있는 view component 선언
        tvName = (TextView) convertView.findViewById(R.id.textView_name);
        // 4. 선언 된 view 에 전달받은 Data 삽입.
        tvName.setText(mName);

        // 5. ListView 에 완성 된 convertView 전달
        return convertView;
    }


}
