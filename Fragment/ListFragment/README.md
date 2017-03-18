# ListFragment

### 1. 개념

> Fragment 에서 ListView 를 구현해 본다. ListView 는 android 에서 제공하는 기본 템플릿을 사용한다.



### 2. 구현

> 이번 프로젝트에서 구현하고자 하는바는 크게 두가지이다.

- Fragment 에 ListView 를 구현
- Fragment 에 Custom Event Callback Interface 구현



##### 1) ListView 구현

```java
// 1. 전역변수로 필요한 구성요소들 선언
ListView listView;	// 리스트뷰 껍데기 선언
ArrayAdapter<String> adapter;	// 리스트뷰에 부착 할 어댑터 선언

ArrayList<String> WORDS; // MainActivity로 부터 받아 올 Data Set

... ... 
  
// 2. 어댑터 구현
adapter = new ArrayAdapter(getActivity(),	// fragment 가 부착 된 activity 정보
                           android.R.layout.simple_item_list_1,	// 시스템 레이아웃 템플릿
                           WORDS	// 리스트뷰에 보여 줄 Data Set
                          );

// 3. 어댑터를 리스트뷰에 부착
listView.setAdapter(adapter);
```



##### 2) Custom Event Callback Interface 구현

- WordListFragment

```java
// 1. 이벤트가 발생하는 Fragment 에서 Interface 를 구현
public interface OnWordListItemClickListener{
  void onItemClicked(int position);
}

... ...

// 2. 생성한 인터페이스를 자료형으로 하는 전역 변수 생성
OnWordListItemClickListener mEventListner;

// 3. 인터페이스를 이용해 Activity 와 해당 Fragment 를 연결
@Override
public void onAttach(Context context) {
	super.onAttach(context);
        
  	try{	// 전달받은 Activity 를 listener 에 연결
            mEventListener = (OnWordListItemClickListener) context;
        }catch(Exception e){
            Log.e("Interface Error","ItemClickListener is not implement on activity yet");
        }
}

// 4. 이벤트 호출
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, 
                                    View view, int position, long id) {
				// 리스트 뷰의 아이템이 클릭 되었을 때,
              	// 해당 아이템의 position 을 activity 로 전달
                mEventListener.onItemClicked(position);
            }
        });
```



- MainActivity

```java
// 1. MainActivity 에 Interface 를 부착한다.
public class MainActivity extends AppCompatActivity 
  implements WordListFragment.OnWordListItemClickListener
  
  ... ...
  
// 2. Interface 의 함수를 Override 하여 원하는 동작 구현
@Override
public void onItemClicked(int position) {

  // 2.1 이벤트 리스너로 부터 전달받은 position 을 이용해 WordDetailFragment 에 특정 정보 전달
  WordDetailFragment fragment = 
	WordDetailFragment.newInstance(WORDS.get(position), DESC.get(position));

  // 2.2 Fragment Replacement
  setFragment(fragment);
}
```

