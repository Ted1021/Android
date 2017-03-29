# Progress Bar

### 1. 개념

> ___ProgressBar___ 는 일상 생활에서 흔히 볼 수 있는 Widget 이다.
>
> 주로 로딩바와 같이 어떤 프로세스의 진행도를 보기 좋게 표현 해 준다.
>
> ProgressBar 의 종류는 크게 두가지가 있다.

<br>

- __Horizontal ProgressBar__

  : 가로로 길게 뻗어있는 형태를 가진다. 주로 완료시점이 정해진 경우 사용하는 widget 이다.

- __Circle ProgressBar__

  : 원형으로 끊임 없이 회전하는 형태를 가진다. 주로 완료시점이 계속 동적으로 변하거나, 알 수 없는 경우 사용하는 widget 이다.

<br>

### 2. 구현

> ___ProgressBar___ 는 주로 다음 _세가지 메소드_ 를 이용해 편집할 수 있다.


- __setProgress(int start)__

  : _ProgressBar_ 의 _현재 위치 _를 직접 세팅 할 수 있다.

- __setMax(int end)__

  : _Horizontal ProgressBar_ 의 경우 _최대값 (완료 시점)_ 을 정할 수 있다.

- __setVisibility(int state)__

  : _progressBar_ 의 _Visibility_ 를 정의 할 수 있다.

<br>

> 이번 프로젝트에서는 Thread 를 이용해 ProgressaBar 가 점진적으로 늘어나는 것을 구현하였다.

```java
// 0.01 초 단위로 Percentage 가 1씩 증가하는 Thread 구현
public class ProgThread extends Thread{
	@Override
	public void run() {
	super.run();

	// 1. 100 % 가 될 때까지 아래의 작업을 반복한다.
	while(mPercentage < 100){

		// 1.1 1% 씩 percent 를 증가시킨다.
		mPercentage++;

		// 1.2 UI Control 을 위해 Handler 를 선언한다.
		mHandler.post(new Runnable() {
			@Override
			public void run() {

				// 1.2.1 Circle ProgressBar 는 Thread 가 실행중인 동안 VISIBLE 상태를 유지한다.
				pbCircle.setVisibility(VISIBLE);
				// 1.2.2 증가시킨 mPercentage 를 Horizontal 형 ProgressBar 에 적용시킨다.
				pbHorizontal.setProgress(mPercentage);
				// 1.2.3 Percentage 를 보여주는 TextView 또한 Update 시켜준다.
				tvPercentage.setText(mPercentage+"" + " %");

				// 1.2.4 Percentage 가 꽉 찼다면,
				if(mPercentage == 100) {
					// 1.2.4.1 Circle ProgressBar 를 없앤다.
					pbCircle.setVisibility(GONE);
					// 1.2.4.2 완료메세지 출력
					Toast.makeText(getApplicationContext(), 
                                    "Download Complete!!", 
                                    Toast.LENGTH_SHORT).show();
				}
			}
		});

		// 1.3 Thread.sleep() 메소드를 통해 0.01초 마다 한번씩 위의 작업들을 수행한다.
		try {
			Thread.sleep(10);
		}catch(Exception e){
			e.printStackTrace(); }
		}
	}
}
```



