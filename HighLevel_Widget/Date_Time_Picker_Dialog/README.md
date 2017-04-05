# Date & Time Picker Dialog

###### 위젯이 아닌 대화상자 형식으로 Picker 들을 불러와 데이터를 조작해 본다.

<br>

## 1. 개념

> 위젯과 동일한 기능을 수행하지만, 필요할 때에만 화면에 ___Dialog 형태___ 로 나타나게 함으로써
>
> 좁은 스마트폰 화면을 효율적으로 이용 할 수 있다.

<br>

<br>

## 2. 구현

> 다이얼로그를 구현하기 위해서는 크게 두가지가 필요하다.

- __DatePickerDialog__

  : 말 그대로 해당 클래스를 생성하면 그 자체로 DatePicker 가 장착 된 Dialog 를 불러 올 수 있다.

```java
// 1. Date & Time Dialog 생성하기
// 이전 예제의 방식과는 달리 레이아웃에 따로 해당 위젯을 생성 해 줄 필요가 없다.
// java 코드 내에서 다이얼로그를 생성해 사용한다
@Override
public void onClick(View v) {
	switch (v.getId()) {

		case R.id.button_date:
		// 1.1 DatePickerDialog() 생성
		new DatePickerDialog(MainActivity.this, 
                             mDateSetListener, 
                             mYear, mMonth, mDay).show();
		break;

		case R.id.button_time:
		// 1.2 TimePickerDialog() 생성
		new TimePickerDialog(MainActivity.this, mTimeSetListener, 
                             mHour, mMinute, false).show();
		break;
	}
}
```

<br>

- __DatePickerDialog.OnDateSetListener__

  : 다이얼로그 클래스의 내부 함수로써, 다이얼로그가 작업을 마치고 사라질 때, 현재 정보를 가져 온다.

```java
// 2. 각각의 다이얼로그가 사라진 직후의 작업들을 정의 한다.
// mDataSetListener, mTimeSetListener 에서 해당 작업을 수행한다.
public void setDialogs() {

	// 2.1 mDataSetListener.OnDateSetListner() 를 정의 한다.
	// 여기서 전달받는 각각의 파라미터를 이용해 원하는 작업을 진행 할 수 있다.
	mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

			mYear = year;
			mMonth = month+1;
			mDay = dayOfMonth;

			String temp = mYear+"" + " / " + mMonth+"" + " / " + mDay+"";

			Toast.makeText(MainActivity.this, temp, Toast.LENGTH_SHORT).show();
		}
	};

	// 2.2 mTimeSetListener.OnTimeSetListener() 를 정의 한다.
	// 위와 동일하다.
	mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

			mHour = hourOfDay;
			mMinute = minute;

			String temp = mHour+"" + " : " + mMinute+"";
			Toast.makeText(MainActivity.this, temp, Toast.LENGTH_SHORT).show();

		}
	};
}
```
