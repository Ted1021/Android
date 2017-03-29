# Seek Bar

### 1. 개념

> ___Seek Bar___ 는 mp3 플레이어의 _볼륨조정 다이얼_ 이나 동영상 플레이어의 _스트리밍 다이얼_ 등 에서 이용되는 widget 이다.
>
> Seek Bar 는 다음과 같은 고유 속성과 리스너를 가진다.

- __Thumb__

  : Seek Bar 의 현재 위치를 표시하는 아이콘을 정의 할 수 있다.

- __OnSeekBarChangeListener__

  : Seek Bar 의 현재 위치를 감지하는 리스너이다.

<br>



### 2. 구현

> 구현은 매우 간단하다. ___OnSeekBarChangeListener()___ 의 Override 함수를 구현해주면 된다.
>
> Override 함수는 세가지가 있는데 그 중 아래의 _onProgressChanged()_ 메소드만 구현하면 된다.

- __void onProgressChanged(SeekBar s, int progress, boolean fromUser)__

  : _fromUser_ 파라미터는 코드에 의한 위치 변화인지, 유저에 의한 위치 변화인지를 boolean 값으로 알려준다.

<br>

```java
// setOnSeekBarChangeListener() 구현
sbPercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
	// 1. Seek Bar 의 현재 상태를 파악하기 위해서는 onProgressChanged() 메소드만 구현하면 된다.
  	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		// 1.1 단순히 현재 위치(progress) 를 받아와 TextView 에 보여준다.
      	      tvPercent.setText(progress+""+" %");
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}
});
```

