# 인수 전달

### 1. 개념

- Fragment 를 생성 할 때 필요한 정보를 _Argument_ 를 이용해 전달 할 수 있다.
- _Argument_ 를 이용하는 방법은 다음과 같다.

```java
void setArguments(Bundle bundle);
Bundle getArguments();
```





### 2. 구현

> 이번 예제의 경우 _newInstance()_ 라는 정적 메소드를 이용해 액티비티에서 프래그먼트로 값을 전달한다.



#### 1) Argument 를 이용한 변수 세팅

```java
// 1. 액티비티에서 newInstance() 를 호출할 때, count 라는 int 형 파라미터를 전달한다.
public static MainFragment newInstance(int count){
		
        MainFragment fragment = new MainFragment();
		// 2. Bundle 을 새로이 생성
        Bundle bundle = new Bundle();
  		// 3. "Count" 라는 key값을 가진 int형 변수를 삽입
        bundle.putInt("Count", count);
		// 4. 해당 프래그먼트에 Bundle 을 부착
        fragment.setArguments(bundle);

        return fragment;
    }
```



#### 2) Argument 로 부터 변수 사용

```java
// 1. 프래그먼트 생성 시에 부착된 Arguments 로 Bundle 을 초기화
Bundle bundle = getArguments();
// 2. "Count" 라는 key 값으로 전달 받은 int 형 변수를 가져 옴
int mCount = bundle.getInt("Count");
// 3. 전달 받은 변수를 text view 에 적용
tvCounter.setText(mCount+"");
```





### 3. 특징

> 안드로이드가 프래그먼트에 _Argument_ 라는 별도의 저장장치를 제공하는 이유는 __초기화에 필요한 데이터__ 와 __실행에 필요한 데이터__ 를 명확하게 구분짓기 위함이다.

