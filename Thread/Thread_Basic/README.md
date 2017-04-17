# Thread Basic

###### Thread 의 기초적인 생성 방법에 대해 알아본다

<br>

## 1. 개념

> Android 는 iOS 와는 달리 ___'멀티 스레드'___ 환경을 지원한다.
>
> 하나의 프로세스 안에 여러 갈래의 동작 흐름들을 정의 할 수 있는데 이를 ___'스레딩 (Threading)'___ 이라 한다.
>
> 한번에 여러 동작을 할 수 있는 이점도 있지만 그만큼 프로그램이 복잡해지고, 예기치못한 버그가 발생할 확률이 높다.
>
> Thread 를 __구현__ 하는 것보다 안전하게 __설계__ 하는 방법에 더 집중 해야한다.

<br>

## 2. 구현

> Thread 의 구현 방법은 매우 간단하다. 아래의 두가지 방법으로 구현 할 수 있다.

- ___Extends Thread___

  : JAVA 에서 기본적으로 제공하는 Thread Class 를 상속받아 구현 할 수 있다.

```java
// Custom Thread Class 를 구현합니다
// 1. Thread Class 를 상속받습니다
public class TedThread extends Thread {

    // 2. Run 메소드를 Override 합니다
    @Override
    public void run() {
        super.run();

        // 3. Thread 가 어떤 동작을 수행하게 될 지 정의 합니다
        // 무한 반복문으로 초당 1씩 변수가 증가하도록 만들어봅니다
        while (true) {
            // 3.1 Thread 가 MainActivity 의 mSubCount 를 1씩 증가 시키는 일을 수행합니다
            mSubCount++;

            // 3.2 Thread.sleep 을 이용해 1초에 한번씩 잠을 잡니다
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
```

<br>

- ___Implement Runnable___

  : 앞서 보았던 Thread 의 구현과 거의 흡사 하지만 조금 다른점이 있다. 바로 Runnable Interface 를 상속 받는것이다.

  : JAVA 에서는 다중 상속을 권장하지 않는다.

  : 따라서, 어떤 클래스에 Thread 이외에 여러 기능들을 상속 받고 싶다면 Runnable 객체를 사용하는 것이 좋다.

```java
// Custom Runnable Class 를 구현합니다
// 1. Runnable Interface 를 상속받습니다 (핵심!)
public class TedRunnable implements Runnable {

    // 2. Thread 와 마찬가지로 Run 메소드를 Override 합니다
    @Override
    public void run() {
        // 3. Thread 가 어떤 동작을 수행 할 지 정의 합니다
        while (true) {

            // 3.1 Thread 가 MainActivity 의 mSubCount 를 1씩 증가 시키는 일을 수행합니다
            mSubCount++;

            // 3.2 ThreadSleep 을 이용해 1초에 한번씩 잠을 잡니다
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```



