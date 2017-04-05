# DatePicker

###### 달력 날짜를 선택할 수 있는 위젯 생성해 보기



## 1. 개념

> DatePicker 라는 위젯을 통해 손쉽게 달력을 생성하고, 사용자가 선택한 날짜 정보(년,월,일) 을 가져 올 수 있다.

<br>

<br>

## 2. 구현

> 구현은 ***DatePicker.init()*** 메소드의 정의가 중요하다. ___init()___ 메소드에 필요한 파라미터 들은 다음과 같다.



- __getYear(), getYearOfMonth(), getDayOfMonth()__

  : 메소드 명에서도 알 수 있듯이, 각각 년, 월, 일 정보를 가져온다.

- __datePicker.OnDateChangedListener()__

  : datePicker 를 조작하는데 있어서 가장 중요한 인자이다. pick 을 한 날짜가 변경 되었음을 감지 할 수 있다.

<br>



```java
// DatePicker 의 기능을 정의한다
public void setDatePicker(){

	// 1. DatePicker 의 init 이 필요하다
	// 각 파라미터의 이름 그대로 각각 현재 선택된 지점의 년, 월, 일을 받아온다
	datePicker.init(datePicker.getYear(),
	datePicker.getMonth(),
	datePicker.getDayOfMonth(),

    // 2. 날짜 선택에 변경이 생겼을 때 이를 감지하는 리스너를 달아준다
	new DatePicker.OnDateChangedListener(){

		@Override
		public void onDateChanged(DatePicker view, 
                                  int year, 
                                  int monthOfYear, 
                                  int dayOfMonth) {
			// 2.1 바뀐 날짜의 정보를 저장 해 둔다
			mDate = year+"" + " / " + monthOfYear+1+"" + " / " + dayOfMonth+"";
		}
	});
}
```



