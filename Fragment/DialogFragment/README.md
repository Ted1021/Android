# Dialog Fragment

### 1. 개념

> Dialog Fragment 를 통해 Fragment 를 Window 처럼 사용 할 수 있다. 이를 통해 액티비티 위에 대화상자를 표시할 수 있으며, 이를 구현하는 방법은 크게 두가지가 있다.
<br>


- 기존의 대화상자를 그대로 흉내내는 방법
  - OnCreateDialog 에서 Dialog 객체나 그 서브클래스의 객체에 Fragment 의 레이아웃을 삽입한 뒤 리턴한다.
<br>



- 커스텀 레이아웃을 자유롭게 배치하는 방법
  - OnCreateView 에서 대화상자의 레이아웃을 생성 또는 전개햐여 루트뷰를 리턴한다.
<br>


> Fragment 에 포함 된 대화상자는 다음과 같은 메소드 들로 관리한다.

```java
int show(FragmentTransaction ft, String tag);
void show(FragmentManager fm, String tag);
```



### 2. 구현

##### 1) OnCreateDialog 으로 구현하기

```java
@NonNull
@Override
public Dialog onCreateDialog(Bundle savedInstanceState) {

  Dialog dialog = new Dialog(getActivity());
  dialog.setContentView(R.layout.fragment_dialog);

  return dialog;

}
```



##### 2) OnCreateView 로 구현하기

```java
public void OnCreateView(SavedInstance bundle);
```

