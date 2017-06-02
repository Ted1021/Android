# Shared Preference

##### 앱 종료 이후에도 유지되는 자료 형식 만들기

<br>

## 1. 개념

> ___Preference 란?___ App 이 종료 된 이후에도 사용 정보나 기록 등을 유지 할 수 있도록 만들어진 데이터 형식이다.
>
> 주로 App 의 환경설정, 로그인 데이터 등을 XML 형태로 저장해 둔다.

<br>

> __Shared Preference__ 를 이용하기 위해서는 크게 두가지 클래스가 필요하다.
>
> 첫째, __(Shared)Preference Class__ 이 클래스는 Preference 파일을 조작하기 위한 Handle 역할을 한다.
>
> 주로 Preference 로 지정된 XML 파일의 생성과 삭제 등을 제어한다.
>
> 둘째, __Preference.Editor__ 이 클래스는 Preference 파일 내의 Contents 를 조작하는데에 사용된다.

<br>

## 2. 구현

> 구현은 SharedPreference 의 Handle 과 Editor 를 차례대로 선언해 주면 된다.

```java
// Preference 구현
public void setPreference(){
  // 1. 정보를 저장할 XML 파일의 이름과 접근 권한을 설정해 준다.
  SharedPreference pref = getSharedPreference(FILENAME, MODE_PRIVATE);
  // 2. XML 파일 내의 Contents 를 작성 할 수 있는 Editor 를 생성한다.
  SharedPreference.Editor pEditor = pref.edit();
  // 3. 저장 될 데이터는 Key 와 Value 의 쌍으로 저장되게 된다.
  pEditor.putString(KEY, VALUE);
  // 4. 이후에는 Key 값 만으로 해당 정보를 가져와 쓸 수 있다.
  pEditor.getString(KEY, DEFAULT VALUE);
  
  // 5. Editing 이 이루어지는 과정은 일련의 Transaction 이다.
  // 따라서, 작업을 마치면 반드시 commit() 을 실행 시켜 주어야만 한다.
  pEditor.commit();
  
}
```

> 추가사항) Preference 의 파일 명은 해당 App 의 Package Name 을 포함해야 한다.
>
> 라고 Developer 사이트에 권고되어 있다.

