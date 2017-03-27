# Custom Adapter 만들기

## 1. 개념

> Android 에서 기본적으로 제공하는 Item Layout 만으로는 실제 서비스에서 필요한 다양한 콘텐츠 구성을 표현하기 어렵다. 따라서, 개발자가 직접 List 에 표현 될 Item Layout 을 정의 해야하는 경우가 생기게 되는데 이때 필요한 것이 _CustomAdapter_ 이다.

<br>

### 1) Adapter 란?

> Adapter 란, View 와 Data 를 잇는 징검다리 역할을 한다.
>
> 좀 더 구체적으로 보자면, List 의 특정 Item 하나의 View 를 정의하고, 여기에 필요한 데이터를 연결시켜 List 에 하나 하나 집어 넣어준다.

<br>

### 2) Custom Adapter 를 만드려면…?

> Custom Adapter 를 만들기 위해서는 다음의 ___네가지 요소___ 가 필요하다.

<br>

- __Context context__

  : ListView 를 가지고 있는 Activity (또는 Fragment) 의 정보가 필요하다.

- __int itemLayout__

  : Custom 하고자 하는 item 의 layout 을 정의 한다.

- __ArrayList<T> dataSet__

  : custom 한 item layout 에 집에넣을 DataSet 을 가져온다.

- __LayoutInflater inflater__

  : 전달 받은 context 를 이용해 item layout 을 View 화 시켜줄 system inflater 가 필요하다.

<br>

## 2. 구현

> Custom Adapter 를 구현하기 위해서는 Adapter 의 최상위 클래스인 ___BaseAdapter___ 를 상속받아야 한다.
>
> ___BaseAdapter___ 에 정의 된 다음의 _네가지 Override 함수_ 들을 불러와 구현하면 된다.

- __getCount__

  : ListView 에 들어가 있는 Item 의 개수. 즉, DataSet 의 길이 (size) 가 된다.

```java
// getCount : ListView 의 Item 개수를 return 한다.
@Override
public int getCount() {return mDataSet.size();}
```

- __getItem__

  : ListView 의 특정 Item 하나에 해당하는 Data 를 반환한다.

```java
// getItem : ListView 의 특정 Item 하나에 대한 정보를 return 한다.
@Override
public Object getItem(int position) {return mDataSet.get(position);}
```

- getItemId

  : 선택한 Listview Item 의 위치를 반환 한다.

```java
// getItemId : ListView 의 특정 Item 하나의 position 을 return 한다.
@Override
public long getItemId(int position) {return position;}
```

- getView

  : BaseAdapter 상속을 통해 구현해야 하는 함수 중 가장 중요한 함수로써, Adapter 의 본래 역할인 View 를 inflate 하여 Data 를 매칭 하는 작업을 여기에서 진행한다.

```java
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

	// 5. 완성된 convertView를 ListView에 전달
	return convertView;
}
```

