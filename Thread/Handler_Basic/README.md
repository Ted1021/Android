# Handler Basic

###### Handler 를 이용해 Thread 가 직접 UI Components 에 접근 할 수 있는 방법과 스레드간 통신 방법을 알아 본다

<br>

## 1. 개념

> Handler 는 크게 두가지 이유로 사용된다.
>
> 첫째, Thread 가 UI Component 들을 제어 할 수 있도록 만들어 준다.
>
> 둘째, 외부에 존재하는 Thread 와 통신 할 수 있도록 만들어 준다.

##### 1) Message 객체

> Handler 는 기본적으로 ___Message 객체___ 를 주고 받는다.
>
> 이 ___Message 객체___ 에 필요한 정보를 담아 주고 받으면서 스레드간의 동작을 정의 할 수 있다.
>
> ___Message 객체___ 는 다음의 구성요소를 가지고 있다.

- ___int what___

  : Hash 에 빗대어 보자면 _Key 값_ 에 해당된다. 수신자는 _what_ 값으로 어떤 동작을 수행해야 하는지를 판단한다.

- ___int arg1, arg2___ 

  : 마찬가지로 Hash 에 빗대어 보자면 _Value_ 에 해당된다. 수신자는 전달받은 _arg_ 값을 이용해 특정 동작을 수행한다.

- ___Object obj___

  : _Obj 객체_ 또한 _Value_ 에 해당 되는데, 전달하려는 데이터가 _Integer_ 형태로 표현 될 수 없는 복잡한 형식일 때 사용된다.

<br>

##### 2) __발신자와 수신자__

> ___Message___ 라는 단어로 부터도 알 수 있듯이, 
>
> Handler 를 이용하기 위해서는 ___발신자와 수신자___ 개념을 동시에 구현해 주어야 한다.

- ___발신자 (Sender)___

  : 특정 연산을 수행하는 Thread 로, 연산의 결과 값을 _Message_ 에 담아 _수신자_ 에게 전달한다.

- ___수신자 (Receiver)___

  : _View (Widget) Component_ 를 가지고 있거나, 외부 Thread 로 부터 전달받은 연산의 결과 값을 이용해 특정 동작을 수행하게 되는 Thread 이다.

<br>

## 2. 구현

> Handler 의 구현은 __발신자와 수신자의 정의__ 에서 부터 시작한다.
>
> ___발신자와 수신자___ 가 정의 되면 각각 구현해야 할 내용은 다음과 같다.

##### 1) __수신자 (Receiver)__

- ___Handler 부착___

  : 수신자 Thread 에서 __Handler 객체를 선언__ 해 줌으로써 _Message_ 를 주고 받을 준비를 한다.

  _(이때, Handler 를 발신자 Thread 보다 먼저 선언되어야 한다.)_

  _(안그럼 Handler 에 대해 NullPointerException 을 확인할 수 있다 …)_

```java
Handler mHandler = new Handler(){
  
  ...
    
}
```

- ___handleMessage() 구현___ 

  : 부착한 Handler 내에 전달 받은 Message 를 처리 할 수 있는 __handleMessage()__ 메소드를 Override 한다.

```java
Handler mHandler = new Handler(){
  // HandleMessage() 메소드로 발신자로 부터 전달받은 Message 를 관리한다.
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
      		// 1. Message.what 을 이용해 수행 해야할 동작을 판단한다.
			switch(msg.what){
                // 1.1 값을 일정하게 증가시키는 연산에 대해서는...
				case MESSAGE:
               		// 1.1.1 해당 연산 결과를 TextView 에 적용시킨다.
					tvMsgThread.setText(mMsgVal+"");
					break;
			}
		}
}
```

<br>

##### 2) __발신자 (Sender)__

- ___Handler.sendEmptyMessage(int what)___ 또는 ___Handler.sendMessage(Message message)___ 구현

  : 여기에서 특정 연산을 수행 한 결과 값을 _수신자 Thread_ 로 전달한다.

  : 수신자에 부착 된 Handler 로 부터 발신자 Thread 내에서 __sendEmptyMessage()__ 메소드를 호출 할 수 있다.

  : __sendEmptyMessage__ 와 __sendMessage__ 의 차이점은 단순히 _what_ 값만 보내는지, Message 객체를 보내는지에 따라 다르다.

```java
// Handler 를 이용해 Message 를 수신자 (MainActivity) 에 보낸다.
public class MessageThread extends Thread{

	// Thread 내에서 sendEmptyMessage() 를 이용해 수신자에게 메세지를 실시간으로 전달한다
	@Override
	public void run() {
		super.run();

		// 1. 원하는 값을 증가시키고, UI Thread 를 직접 Control 하기위해 Message 를 발신한다
		while(true){

			// 1.1 mMsgVal 를 1씩 증가 시킨다
			mMsgVal++;
			// 1.2 MainActivity 에 부착된 mMsgHandler 에 특정 Message 를 전달한다
			// 전달하는 메세지의 종류에 따라 MainActivtiy 에서 서로 다르게 동작하도록 만들 수 있다
			mMsgHandler.sendEmptyMessage(MESSAGE);

			// 2. 1초 간격으로 위의 작업들을 반복적으로 수행한다
			try {
			Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
```
