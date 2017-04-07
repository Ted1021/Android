# Intent

###### 명시적 인텐트의 여러 기능들을 학습해 본다.

<br>

## 1. 개념

> ___Intent___ 란, 액티비티간 화면 전환이나 데이터 이동 또는 기능 호출 등에 사용되는 객체이다.
>
> Intent 의 종류에는 크게 ___명시적 Intent___ 와 ___암시적 Intent___ 가 있다.
>
> 이번 프로젝트에서는 ___명시적 Intent___ 의 여러 기능에 대해 알아보도록 한다.

<br>

- __명시적 Intent 란?__

  : 인텐트는 크게 명시적 인텐트와 묵시적 인텐트로 나뉘게 된다.

    두 인텐트 모두 액티비티와 통신을 위해 존재 한다는 공통점이 있지만, 어떤 액티비티와 통신을 하느냐에 따라 달라진다.

  - _명시적 인텐트_ : __앱 내부의 Activity__ 간 통신에 사용된다.
  - _묵시적 인텐트_ : __앱 외부의 Activity__ 와 통신 할 수 있다.


- __Activity 의 이동__

  : 인텐트를 생성할 때, __출발지 Activity__ 와 __목적지 Activity__ 를 파라미터로 가진다. 

    출발지와 목적지가 기입된 인텐트를 ___startActivity(Intent intent)___ 메소드에 삽입하게 되면, 원하는 Activity 로 이동 할 수 있다.

- __Activity 간 통신__

  : 인텐트에 특정 데이터들을 실어 _목적지 Activity_ 에 보냄으로써 액티비티간 통신 또한 구현 할 수 있다.

    이는 ___startActivityForResult(Intent intent, int RequestCode)___ 메소드를 통해 정보가 담긴 인텐트를 실어 보내고,

    ___onActivityResult(int requestCode, int resultCode, Intent data)___ 를 통해 _출발지 Activity_ 에서 

    결과 값을 받아 볼 수 있다.

<br>

## 2. 구현

> 단순한 __Activity 의 이동__ 과 __Activity 간 통신__ 을 구현해 본다.
>
> Activity 의 통신에 있어서 주목해야 할 점은 ___Intent___ 에 __'데이터를 저장' 하고 이를 ''꺼내 쓰는방법'__ 이다.

<br>

- __Activity 의 이동__

```java
// Main Activity 에서 Sub Activity 로 이동하기
// 여기서 this 는 Main Activity 이다.
Intent intent = new Intent(this, SubActivity.class);
startActivity(intent);
```

<br>

- __Activity 간 통신__

  > MainActivity 는 List 를 보여주는 역할을 하고, CreateArticleActivity 는 새로운 글을 작성하는 Activity 이다.

  ​

  - MainActivity.java
  
  ​ 1) startActivityForResult()

  ```java
  // Edit Button 을 눌렀을때, CreateArticleActivity 로 이동하고
  // CreateArticleActivity 로 부터 결과값을 전달 받는다.

  @Override
  public void onClick(View v) {
  	switch(v.getId()){
  	// 1. Edit Button 을 눌렀을때,
  	case R.id.button_edit:
  		// 2. CreateArticleActivity 로 이동하되,
  		Intent editIntent = new Intent(this, CreateArticleActivity.class);
          // 3. 단순 이동이 아닌 결과 값을 전달 받도록 한다.
          // 3.1 결과 값을 전달 받기 위해서는 Intent 와 함께 RequestCode 또한 첨부한다.
  		startActivityForResult(editIntent, REQ_EDIT);
  		break;
  	}
  }
  ```

  ​	

  ​	2) onActivityResult()

  ```java
  // 목적지 Activity 로부터 실행 결과 값을 전달 받는다.
  // RequestCode	: 여러 Activity 로부터 결과 값을 requestCode 로 구분 할 수 있다.
  // resultCode	: 목적지 Activity 로 부터 수행결과를 전달받는다.
  // data			: 목적지 Activity 로 부터 전달받은 Intent 로, 수행 결과 값을 담고 있다.
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  	super.onActivityResult(requestCode, resultCode, data);
  	
    	// 1. requestCode 를 이용해 특정 Activity 로 부터의 수행결과 값을 관리 할 수 있다.
  	switch(requestCode){
          // 2. 목적지 Activity 가 CreateArticleActivity 로 부터 전송된 Intent 일 경우,
  		case REQ_EDIT:
          	// 2.1 resultCode 를 통해 성공적으로 작업이 수행 되었는지를 확인하고,
  			if(resultCode == RESULT_OK){
  			// 2.2 전달받은 data(Intent) 로 부터 Key 를 이용해 원하는 데이터를 꺼내쓴다.
  			Article article = new Article();

  			String title = data.getStringExtra("TITLE");
  			article.setTitle(title);

  			String name = data.getStringExtra("NAME");
  			article.setName(name);

  			String contents = data.getStringExtra("CONTENTS");
  			article.setContents(contents);

  			// 3. 새로운 Article 객체를 기존의 DataSet 에 추가 함으로써 생성작업을 마친다.
  			mDataSet.add(article);
  		}
  		break;
  	}
  }
  ```

  <br>

  - CreateArticleActivity.java

  > 결과값 전송에 있어서 가장 중요한 함수는 ___setResult()___ 함수이다.

  ```java
  // 수행결과를 Intent 에 담고, setResult() 메소드를 호출해 Intent 를 보내주면 된다.
  public void sendData(){
  		// 1. 수행결과를 담을 Intent 를 생성한다. 이때, 파라미터는 필요하지 않다.
          Intent intent = new Intent();
  		
    		// 1.1 원하는 정보를 Key 값과 함께 저장한다.
          intent.putExtra("TITLE", mTitle);
          intent.putExtra("NAME", mName);
          intent.putExtra("CONTENTS", mContents);
  		
    		// 2. 다음의 두 파라미터와 함께 setResult() 메소드를 호출한다.
    		// 첫번째, 수행여부
    		// 두번째, 결과값을 담은 intent
          setResult(RESULT_OK, intent);
    	
    		// 3. CreateArticleActivity 를 종료한다.
          finish();
      }
  ```

  ​
