package com.study.tedkim.expandablelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tedkim on 2017. 3. 27..
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    Context mContext;

    ArrayList<String> mGroupList = new ArrayList<>();
    ArrayList<ArrayList<String>> mChildList = new ArrayList<>();

    LayoutInflater mInflater;

    TextView tvGroupText, tvChildText;

    // groupList 와 ChildList 의 DataSet 을 각각 전달받아 Adapter 를 초기화 한다.
    public ExpandableListAdapter(Context context, ArrayList<String> groupList, ArrayList<ArrayList<String>> childList){

        // ListView 가 존재하는 Context
        mContext = context;
        // GroupList 와 ChildList DataSet 각각 초기화
        mGroupList = groupList;
        mChildList = childList;

        // 전달받은 Context 로 부터 System Inflater 연결
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    /******* 여기서부터 GroupView *******/
    @Override
    public int getGroupCount() {
        return mGroupList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupList;
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    // groupView 를 구현
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        // 1. GroupList 의 Item 에 담을 Data 를 가져온다.
        String groupName = (String) getGroup(groupPosition);

        // 2. convertView 가 존재하지 않는다면,
        if(convertView == null) {
            // 2.1 Group Item Layout, parent(listView in Activity) 를 Inflater 에 넣어 초기화 한다.
            convertView = mInflater.inflate(R.layout.group_list_layout, parent, false);
        }

        // 3. Group Item Layout 에 있는 TextView 를 선언하고,
        tvGroupText = (TextView) convertView.findViewById(R.id.textView_group_name);
        // 3.1 미리 준비해둔 Data 를 삽입한다.
        tvGroupText.setText(groupName);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /******* 여기서부터 ChildView *******/

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildList.get(groupPosition).size();
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildList.get(groupPosition).get(childPosition);
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        // 1. childList 의 Item 에 담을 Data 를 가져온다.
        String childDesc = (String) getChild(groupPosition, childPosition);

        // 2. convertView 가 존재하지 않는다면,
        if(convertView == null){
            // 2.1 child Item Layout 과 parent (listView in Activity) 를 Inflater 에 넣어 초기화 한다.
            convertView = mInflater.inflate(R.layout.child_list_layout, parent, false);
        }
        // 3. Child Item Layout 의 TextView 를 선언하고,
        tvChildText = (TextView) convertView.findViewById(R.id.textView_child_desc);
        // 3.1 준비해 둔 Data 를 삽입한다.
        tvChildText.setText(childDesc);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
