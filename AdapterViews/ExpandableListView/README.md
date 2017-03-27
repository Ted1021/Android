# Expandable ListView

## 1. 개념

> Expandable ListView 는 List Item (Group Item) 을 클릭했을 때, Sub List (Child Item) 가 바로 밑으로 확장되는 리스트를 말한다. 전반적인 구현 개념은 일반적인 CustomAdapter 와 동일하다.
>
> 차이점은 ___BaseExpandableListAdapter___ 를 상속 받으면서 groupView 와 ChildView 를 각각 따로 정의 해 주어야 한다는 것이다.

<br>

## 2. 구현

> 기본적인 구현은 ___BaseExpandableListAdapter___ 에서 제공하는 Override 함수들을 채워나가면 되는데
>
> 이때, _GroupList_ 와 _ChildList_ 를 위한 각각의 _ItemLayout_ 과 _dataSet_ 을 정의해 주어야 한다.

- GroupList DataSet

  : 1차원 배열인 ArrayList<T> 로 나타낸다.

- ChildList DataSet

  : 2차원 배열 또는 Map 의 형태로 만들고 GroupList 의 _Position_ 또는 _Value_ 를 Key 값으로 갖는다.

  _(이번 프로젝트에서는 Group Item 과 Child Item 의 index 가 동일 하다는 가정하에 단순히 2차원 배열로 구현 하였다.)_

<br>

> 기본적으로 _getCount(), getItem(), getItemId(), getView()_ 의 구성은 동일하나 _Group_ 과 _Child_ 로 나누어 각각 구현한다.

<br>

- Expandable Adapter 의 초기화

```java
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    Context mContext;

    ArrayList<String> mGroupList = new ArrayList<>();
    ArrayList<ArrayList<String>> mChildList = new ArrayList<>();

    LayoutInflater mInflater;

    TextView tvGroupText, tvChildText;

    // groupList 와 ChildList 의 DataSet 을 각각 전달받아 Adapter 를 초기화 한다.
    public ExpandableListAdapter(Context context, 
                                 ArrayList<String> groupList, 
                                 ArrayList<ArrayList<String>> childList)
    {

        // ListView 가 존재하는 Context
        mContext = context;
        // GroupList 와 ChildList DataSet 각각 초기화
        mGroupList = groupList;
        mChildList = childList;

        // 전달받은 Context 로 부터 System Inflater 연결
        mInflater = (LayoutInflater)	context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      
      
      ... ...
       
```

<br>

- GroupList View 구현

```java
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
    public View getGroupView(int groupPosition, 
                             boolean isExpanded, 
                             View convertView, 
                             ViewGroup parent) {

        // 1. GroupList 의 Item 에 담을 Data 를 가져온다.
        String groupName = (String) getGroup(groupPosition);

        // 2. convertView 가 존재하지 않는다면,
        if(convertView == null) {
            // 2.1 Group Item Layout, parent(listView in Activity) 를 Inflater 에 넣어 초기화
            convertView = mInflater.inflate(R.layout.group_list_layout, parent, false);
        }
        
        // 3. Group Item Layout 에 있는 TextView 를 선언하고,
        tvGroupText = (TextView) convertView.findViewById(R.id.textView_group_name);
        // 3.1 미리 준비해둔 Data 를 삽입한다.
        tvGroupText.setText(groupName);

        return convertView;
    }

... ...
  
```

<br>

- ChildList View 구현

```java
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
    public View getChildView(int groupPosition, 
                             int childPosition, 
                             boolean isLastChild, 
                             View convertView, 
                             ViewGroup parent) {

        // 1. childList 의 Item 에 담을 Data 를 가져온다.
        String childDesc = (String) getChild(groupPosition, childPosition);

        // 2. convertView 가 존재하지 않는다면,
        if(convertView == null){
            // 2.1 child Item Layout과 parent (listView in Activity) 를 Inflater 에 넣어 초기화 
            convertView = mInflater.inflate(R.layout.child_list_layout, parent, false);
        }
        // 3. Child Item Layout 의 TextView 를 선언하고,
        tvChildText = (TextView) convertView.findViewById(R.id.textView_child_desc);
        // 3.1 준비해 둔 Data 를 삽입한다.
        tvChildText.setText(childDesc);

        return convertView;
    }
}
```
