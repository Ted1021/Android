# ListView Basic

### 1. 개념

> listView 는 일반적인 Application 에서 가장 많이 쓰이는 List 중 하나이며, Android 에서는 listView 를 위한 여러 편의 기능을 제공하고 있다. 이번 프로젝트에서 적용 할 기능은 다음과 같다.

<br>

- __ArrayAdapter 를 이용한 ListView Inflate__

  : BaseAdapter 로 부터 상속 된 세 가지 Adapter (ArrayAdapter, CursorAdapter, SimpleAdapter) 중 특정 ArrayList 나 Resource 들을 위한 Adapter 인 _ArrayAdapter_ 를 사용하였다.

- __System ListView Layout Templet__

  : BaseAdapter 에서 기본적으로 제공하는 ListView 의 Layout 에는 여러 종류들이 있다.

  *ex) android.R.layout.simple_list_item_1, android.R.layout.simple_list_item_checked 등*

- __NotifyDataSetChanged__()

  : Adapter 에 들어가는 DataSet 이 항상 정적 일 수는 없다. Application 의 동작 중에 DataSet 에 변화가 생긴다면 이를 인지해 즉각적으로 UI 에 적용 시키도록 도와주는 메소드이다.

<br>

### 2. 구현

> 기능을 추가 할 수 록 ListView 와 Adapter 사이에 구현 해야 할 코드가 복잡해지는데, 둘의 역할을 다음과 같이 명확하게 정하고 코딩을 하면 헷깔릴 일은 없는 것 같다.

- ListView

  : View 를 기준으로! - UI 변화가 생기거나, View 에 대한 이벤트가 발생 할 때에는 항상 listView (Adapter Views) 에 관련 옵션이 존재 한다.

- Adapter

  : Data 를 기준으로! - View 에 표시될 DataSet 에 관련된 옵션들은 모두 Adapter 에 존재한다.

<br>

##### 1) ListView 의 선언과 ArrayAdapter 의 부착

```java
// ListView 를 구현하고 다양한 옵션들을 적용하기 위한 메소드
public void setListView(){

	// 1. ListView 를 선언한다
	listView = (ListView) findViewById(R.id.listView_list);
	// 2. ListView 와 미리 준비 된 DataSet 을 연결 해 줄 Adapter 선언하고 초기화 한다
	mAdapter = new ArrayAdapter<>(this, // 현재 Context 정보
			  // 리스트 뷰를 표현 할 Layout 형식 (item_checked)
                                  android.R.layout.simple_list_item_checked, 
                  // 리스트 뷰에 적용할 데이터 셋
                                  listItem.cntName
	);
  
	// 3. 초기화 한 Adapter 를 ListView 에 부착
	listView.setAdapter(mAdapter);
  
  	... ...
}
```

<br>

##### 2) ListView 에 이벤트 리스너와 List Item 옵션 설정

```java
// ListView 를 구현하고 다양한 옵션들을 적용하기 위한 메소드
public void setListView(){
  	... ...
      
	// 4. 현재 ListView 에 적용시킬 Layout Option 지정 - 이는 Xml 상으로도 가능하다
	listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
	// 5. ListView 에서 발생하는 Item Click 이벤트를 처리하기위한 Listener 부착
	listView.setOnItemClickListener(this);
}
```

<br>

##### 3) Adapter 에 NotifyDataSetChanged() 부착

```java
// ListItem Click 이벤트 정의
@Override
public void onClick(View v) {
	switch(v.getId()){

	// 1. DataSet 을 추가 할 때,
	case R.id.button_Add:
		// 1.1 EditText 에 입력된 문자열을 저장
		String str = etChoose.getText().toString();

		// 1.2 입력할 문자열의 공백 여부를 체크 한 뒤,
		if(str.equals(""))
			// 1.2.1 공백이라면 아예 DataSet 에 입력하지 않음
			Toast.makeText(this, 
                           "please Insert any Word that you wanna add ;)", 											Toast.LENGTH_SHORT).show();
		else
			// 1.2.2 입력 받은 문자열을 DataSet 에 추가
			listItem.cntName.add(str);
			break;

	// 2. DataSet 의 일부 Component 를 삭제 할 때,
	case R.id.button_Remove:
		// 2.1 준비해 둔 Item Check List 를 순차적으로 확인한다
		for(int i=0; i<listItem.cntName.size(); i++){
			// 2.1.1 체크가 되어 있다면,
			if(listView.getCheckedItemPositions().get(i))
				// 2.1.2 해당 아이템의 position 을 DataSet 에서 삭제 한다
				listItem.cntName.remove(i);
			}
		break;
}
	// TODO - notifyDataSetChanged() 의 위치가 매우 중요!!
	// 주로 DataSet 의 변화가 발생하는 이벤트 직후 이 메소드를 호출하도록 한다
	mAdapter.notifyDataSetChanged();
}
```

