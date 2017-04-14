# Intent Filter

###### 암시적 인텐트는 안드로이드의 System Activity (다이얼, 주소록 등) 만 호출 할 수 있는 것이 아니다. 내가 직접 만든 Activity 또한 Intent Filter 를 이용해 App 외부에서 호출되도록 만들 수 있다.

<br>

## 1. 개념

> ___Intent Filter___ 는 Activity 와 항상 붙어 있는 개념이다.
>
> ___Intent Filter___ 를 통해 해당 Activity 의 동작 명(Action) 과 추가 정보(Category) 를 정의 한다.

- Action

  : 해당 Activity 의 이름이 아닌 Activity 가 수행하게 될 동작에 대한 이름을 정의한다.

  : 외부 App 에서 해당 Activity 를 호출 할 때, Action 에 부여된 이름으로 호출하게 된다.

- Category

  : Activity 의 역할 boundary 를 정의 한다.

  : 여기서 정의한 내용에 따라 Launcher, Splash Activity 등 다양한 역할이 부여 된다.

<br>

## 2. 구현

> 구현은 생각보다 간단하다. Manifests 파일 에서 해당 Activity 에 Intent Filter 를 삽입하면 된다.

1) Manifests.xml

```xml
<activity android:name=".Adder">
	<!-- 외부에서 custom activity 가 사용되기 위해서는 Intent Filter 를 선언해 줘야 한다 -->
	<!-- Intent Filter 에 사용되는 요소들을 크게 Action, Category 가 필요하다 -->
	<!-- Action : 이 액티비티가 처리하는 동작에 대한 이름을 정의 해 준다 -->
	<!-- Category : 인텐트에 대한 추가정보를 지정 할 수 있는데 '암시적 인텐트 에는 항상
                	DEFAULT 를 선언해 주어야 한다 -->
		<intent-filter>
			<action android:name="com.study.tedkim.intent_filter.adder" />
			<category android:name="android.intent.category.DEFAULT" />
		</intent-filter>
</activity>
```

<br>

2) 외부 App 에서의 호출

```java
// 여기가 핵심 포인트이다.
// 기존에 Intent 를 초기화 할때 'ACTION_OOO' 부류를 많이 사용했었는데
// 이번에는 개발자가 직접 선언한 Activity의 Action 로 Intent 를 초기화 한다.
// 나의 경우에는 ACTION 명이 겹치지 않기 위해 패키지명을 함께 붙여주었다.
Intent intent = new Intent("com.study.tedkim.intent_filter.adder");

intent.putExtra("FIRST", 3);
intent.putExtra("SECOND", 7);

startActivity(intent);
```

