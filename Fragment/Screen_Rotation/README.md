# 화면 회전 인터페이스

### 1. 개념

> 화면이 가로 또는 세로 상태로 회전 되었을때, Fragment 를 이용해 적합한 화면 구성을 할 수 있는 방법을 알아본다.
>
> 회면 회전에 대응하기 위해서는 다음 세가지를 이용해야 한다.

<br>

- manifests 에서 Activity 의 Configuration 설정
- onConfigurationChanged();
- onSavedInstanceState();

<br>

### 2. 구현

##### 1) Manifests 설정

> Manifests.xml 에서 직접 제어 할 Configuration Changes 요소들을 정의한다.

```java
<activity android:name=".MainActivity"
  // keyBoardHidden - 키보드가 사라졌을 때,
  // orientation - 화면이 회전 했을 때,
  // screenSize - 화면 사이즈의 변화가 생겼을 때,
  android:configChanges="keyboardHidden|orientation|screenSize">

    <intent-filter>
    	<action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />      
    </intent-filter>
            
</activity>
```

<br>

##### 2) onConfirgurationChanged 구현

> 화면 회전에 대응하기 위해 필요한 함수로써 현재 화면에 대한 상태(Config) 를 전달 받고 이에 따른 적절한 동작을 custom 할 수 있다.

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
  
  ... ...
    
    // 현재 액티비티의 config 를 획득하는 과정
	// 1. 시스템 리소스에 접근한다.
	Resources resource = Resources.getSystem();
	// 2. 리소스로부터 현재의 configuration 정보를 가져온다.
	config = resource.getConfiguration();
	// 3. onConfigurationChanged 로 현재 상태를 보내준다.
	onConfigurationChanged(config);  
}

// 화면 회전 처리에 있어서 가장 중요한 함수
// newConfig = 현재 화면의 상태 (회전, 사이즈의 변화, 키보드 출현 유무 등의 상태를 나타낸다)
@Override
public void onConfigurationChanged(Configuration newConfig) {
	super.onConfigurationChanged(newConfig);

	// 1. 전달받은 configuration 에서 orientation 정보를 가져온다.
	switch(newConfig.orientation){

		// 1.1 LANDSCAPE (가로 상태) 일 때,
		case Configuration.ORIENTATION_LANDSCAPE:
			// 1.1.1 가로 상태에 알맞는 레이아웃을 미리 설정한다.
			setContentView(R.layout.activity_main_landscape);

			// 1.1.2 가로 레이아웃에 맞는 Fragment 들의 초기상태를 설정한다.
			setFragment_landscape();
			break;

		// 1.2 PORTRAIT (세로 상태) 일 때,
		case Configuration.ORIENTATION_PORTRAIT:
			// 1.2.1 세로 상태에 알맞는 레이아웃을 미리 설정한다.
			setContentView(R.layout.activity_main);

			// 1.2.2 세로 레이아웃에 맞는 Fragment 들의 초기상태를 설정한다.
			setFragment_portrait();
			break;
		}
}
```

<br>

##### 3) onSavedInstanceState 구현

> 화면 상태를 인지해 적절한 레이아웃 구성을 할 수 있게 되더라도 이전 상태에 대한 정보를 저장하지 않는다면 매번 화면 회전 (또는 상태변화)가 있을 때 마다 레이아웃의 초기 상태로 돌아가게 된다.
>
> 이러한 상황을 방지하기 위해 onSavedInstanceState 에 이전 상태를 저장하고 이를 바탕으로 화면을 재구성 한다.

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
      
	// 1. 이전 상태에 대한 저장 정보가 존재 한다면,
	if(savedInstanceState != null){
		// 1.1 savedInstance(=Bundle) 에 저장 된 내용으로 액티비티 (또는 프래그먼트) 를 초기화 한다.
		mPosition = savedInstanceState.getInt("position");
	}    
  
	... ...       
}

// 액티비티가 초기화 될 때마다 이전 액티비티의 상태를 보존하기 위한 메소드
@Override
protected void onSaveInstanceState(Bundle outState) {
	super.onSaveInstanceState(outState);
	// 1. 현재 프로젝트에서는 ListFragment 의 position 값이 보존 되어야 하므로
	// Bundle 에 position 에 관한 정보를 저장 한다.
	outState.putInt("position", mPosition);
}
```

