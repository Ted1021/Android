# Strict Mode

##### Tread Policy

<br>

## 1. 개념

> 앞서 ANR 을 회피하기 위해서 ___Thread___ 와 ___Handler___ 를 사용해 보았다.
>
> Thread 는 ANR 에 영리하게 대처 할 수 있게 해주지만, 
>
> 남용하게 되면 프로그램의 복잡도가 올라가고 결과적으로 성능저하를 야기 할 수 있다.
>
> 따라서, Thread 는 딱 필요한 만큼만 사용 할 필요가 있다. 
>
> 문제는 모든 ANR 상황을 개발자가 예측 할 수 없다는 점인데
>
> 이를 위해 Android 에서는 ___Strict Mode___ 를 통해 어떤 작업이 ANR 을 유발 할 가능성이 있는지를 디버깅 할 수 있다.

<br>

## 2. 구현

> ___StrictMode___ 는 _StrictMode.ThreadPolicy.Build()_ 메소드를 통해 구현 할 수 있다.
>
> 이 메소드에서는 다양한 옵션을 설정 해야 하는데 이는 다음과 같다.

- Detect()

  : ANR 을 체크 할 작업 범위를 설정하게 된다.

- Permit()

  : ANR 의 여지가 있는 작업이라도 수행 할 수 있도록 White List 를 정할 수 있다.

- Panalty()

  : ANR 을 야기하는 작업에 대해 어떤 Panalty 를 부여할지 정할 수 있다.

<br>

> 1. App 내의 모든 작업에 대해 ANR 여부를 체크
> 2. ANR 을 야기하는 작업을 발견 했을때, 해당 작업을 Dialog 형태로 띄워 개발자에게 알려 주도록 한다.

```java
/* Thread Policy 를 정의 한다.
** DEBUG_MODE 인지를 판단하는 이유 : 사용자를 위한것이 아닌 개발자를 위한것이기 때문!
** ANR 여부를 체크하고 Releasing 시엔 해당 로직을 삭제 해 주어야 한다.
*/

// 1. ThreadPolicy 를 정의 한다.
if (DEBUG_MODE) {
	// 1.1 ThreadPolicy 를 생성한다.
	StrictMode.ThreadPolicy mThreadPolicy = new StrictMode.ThreadPolicy.Builder()
          .detectAll()		// 모든 작업 체크
          .penaltyDialog()	// 발견 시, Dialog 호출
          .build();			// 실행

  	// 1.2 ThreadPolicy 를 set 한다.
  	StrictMode.setThreadPolicy(mThreadPolicy);
}
```

