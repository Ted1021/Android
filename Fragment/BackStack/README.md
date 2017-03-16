# 프래그먼트 백스택

### 1. 개념

> 프래그먼트를 Stack 에 저장해 두고 __Back Key__ 가 눌렸을 때, 이전 상태의 프래그먼트를 가져 올 수 있다.



### 2. 구현

> 프래그먼트 백스택을 구현 하기위해서는 __FragmentManager__ 와 __FragmentTransaction__ 이 필요하다.



```java
public void setFragment(Fragment fragment){

  		// 1. 프래그먼트 매니저 생성
        FragmentManager fm = getSupportFragmentManager();
  		// 2. 프래그먼트 트랜잭션 생성
        FragmentTransaction ft = fm.beginTransaction();
		// 3. 원하는 프래그먼트 컨테이너에 전달받은 프래그먼트를 삽입한다.
        ft.replace(R.id.fragment_container, fragment);
  		// 4. 핵심!! 해당 명령어를 통해 프래그먼트를 Stack 에 저장 할 수 있다.
  		// 이때, null 의 부분에는 현 stack 의 상태를 정의 하는 Tag 를 넣을 수 있다.
        ft.addToBackStack(null);	
		// 5. 모든 과정이 끝나면 트랜잭션을 커밋한다.
        ft.commit();
    }
```





### 3. 특징

> __replace(add)__ , __addToBackStack__ 과 같은 명령어 이외에도 프래그먼트의 전환 시에 애니메이션을 정의 할 수있는 명령어들이 있다. 각각 __시스템 애니메이션__ 과 __커스텀 애니메이션__ 을 삽입 할 수 있다.



```java
FragmentTransaction setTransition(int transit)
FragmentTransaction setCustomAnimations(int enter, int exit)
```





