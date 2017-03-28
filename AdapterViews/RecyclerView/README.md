# RecyclerView

### 1. 개념

> 기존의 ListView 는 Item orientation, animation 등 개발자가 Custom 할 수 있는 요소가 제한적이었다.
>
> 이러한 문제를 개선해서 API Level 21 이후에 나오게 된 것이 바로 ___RecyclerView___ 이다.
>
> ___RecyclerView___ 를 구현하기 위해서는 기존의 ListView 가 단순히 adapter 만을 요구했던 것 과는 달리
>
> 추가적으로 몇가지 요소를 더 구현해야 하는데 이는 다음과 같다.

<br>

- __LayoutManager__

  : List Layout 의 형태 (Linear, Grid, StaggeredGrid), Orientation, Animation 등을 개발자의 입맛에 맞게 custom 할 수 있도록 해준다.

- __ViewHolder__

  : ViewHolder 를 사용하게 되면 Item 을 Inflate 할 때 마다 _findViewById()_ 를 수행 해야만 했는데 이는 삽입해야 할 Item 의 개수가 많아지면 상당한 리소스를 잡아먹게 된다. 따라서 Adapter 를 생성할 때, 단 한번만 _findViewById()_ 를 ViewHolder 에서 수행하고 이후에는 ViewHolder 에 저장 된 내용을 가져와 재활용한다.

  ​

  : ListView 에서도 사용 할 수 있지만 RecyclerView 에서 부터는 ___ViewHolder 의 사용___ 을 ___의무화___ 하고 있다.

<br>

<br>

### 2. 구현

> ___LayoutManager___ 의 구현은 어렵지 않지만, ___RecyclerViewAdapter___ 의 구현은 기존의 adapter 에 비해 상당히 복잡해 지게 된다. ___RecyclerViewAdapter___ 와 ___ViewHolder___ 사이의 유기적인 관계를 잘 이해 해야 한다.

<br>

##### 1) LayoutManager 구현

```java
// LayoutManager 를 전역변수로 선언
// 이때 LayoutManger 의 최상위 클래스를 선언하였다.
// 이후, 초기화 단계에서 Linear, Grid, Staggerd Grid 셋 중 어느 형태로도 초기화가 가능하다.
RecyclerView.LayoutManager mLayoutManager;

	... ... 

// RecyclerView 를 선언하고, 
//RecyclerViewAdapter 와 LayoutManager 를 정의해 RecyclerView 에 부착한다.
public void setRecyclerView(){

	// 1. RecyclerView 선언
	rvList = (RecyclerView) findViewById(R.id.recyclerView_list);

	// 2. RecyclerViewAdapter 생성
	RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, 
                                                     	 R.layout.item_layout, 
                                                     	 mDataSet);
	// 3. LinearLayoutManager 로 LayoutManager 초기화
	mLayoutManager = new LinearLayoutManager(this);

	// 4. 생성한 Adapter 를 RecyclerView 에 부착
	rvList.setAdapter(adapter);

	// 5. 생성한 LayoutManager 를 RecyclerView 에 부착
	rvList.setLayoutManager(mLayoutManager);

}
```

<br>

##### 2) RecyclerViewAdapter 구현

> ___RecyclerViewAdapter___ 의 구현은 _RecyclerView.Adapter<ViewHolder>_ 를 상속받은 뒤, 크게 세가지 요소를 구현한다.
>
> _첫째, Adapter 에 기본적으로 필요한 4가지 구성요소 초기화_
>
> _둘째, convertView 의 역할을 해 줄 ViewHolder 클래스 선언_
>
> _셋째, onCreateViewHolder(), onBindViewHolder() method 구현_

<br>

- __Adapter 생성자 구현__

```java
// RecyclerView 를 위한 Custom Adapter 생성 
// RecyclerView.Adapter 를 상속 받으며, generic 으로는 직접 Custom 한 ViewHolder 를 끼워 넣는다.
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    /** Adapter 선언을 위한 4가지 구성요소 **/
    Context mContext;   // RecyclerView 가 있는 Context
    int mItemLayout;    // RecyclerView 에 끼워 넣을 Item Layout
    ArrayList<ListItem> mDataSet = new ArrayList<>();   // Item Layout 에 bind 될 DataSet
    LayoutInflater mInflater;   // Item Layout 을 구체화 시켜 줄 Inflater

    // RecyclerViewAdapter 의 초기화는 기존의 CustomAdapter 를 초기화하는 방법과 완전히 동일하다.
    public RecyclerViewAdapter(Context context, 
                               int itemLayout, 
                               ArrayList<ListItem> dataSet){

        // 1. RecyclerView 가 위치한 Activity (또는 Fragment) 의 Context 를 가져온다.
        mContext = context;
        // 2. 준비 된 Item Layout 을 가져온다.
        mItemLayout = itemLayout;
        // 3. Activity (또는 Fragment) 로 부터 전달받은 DataSet 을 세팅한다.
        mDataSet = dataSet;
        // 4. 전달받은 Context 를 이용해 system Inflater 를 가져온다.
        mInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }
  
  ... ... ...
    
```

<br>

- __ViewHolder 클래스의 선언__

```java
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

	... ... ...
      
```

<br>

- __onCreateViewHolder() 와 onBindViewHolder() 의 구현__

```java
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
```

