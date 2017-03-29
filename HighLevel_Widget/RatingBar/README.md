# Rating Bar

### 1. 개념

> ___Rating Bar___ 는 주로 영화나 책을 평가할 때 쓰는 _별점_ 표현을 위해 만들어진 Widget 이다.
>
> Rating Bar 의 고유속성으로는 다음과 같은 것들이 있다.

- __numStars__

  : 별의 최대 개수를 지정하는 속성이다. *기본 값은 5* 이고 얼마든지 그 갯수를 늘릴 수 있다.

    하지만, ___RatingBar___ 의 __Layout_Width 속성__ 은 ___항상 wrap_content 상태___ 여야 한다. 아니면 동작하지 않음.

  ​

- __stepSize__

  : 별점이 한번에 늘어나는 정도를 설정 하는 속성이다. *기본 값은 0.5* 이다.

  ​

- **isIndicator**

  : **RatingBar** 가 *editable* 한 지를 정할 수 있는 속성이다. 

    활성화 시키면 해당 ratingBar 는 볼 수만 있을 뿐, 편집은 불가능 하다.

<br>

### 2. 구현

> 구현은 매우 간단하다. Seek Bar 와 마찬가지로 현재 RatingBar 의 상태를 인지 할 수 있는 ___OnRatingBarChangeListener()___ 를 정의 해 주면 된다.

<br>

```java
public void setRatingBar(){

	// 1. RatingBar 선언
	rbStars = (RatingBar) findViewById(R.id.ratingBar_stars);

	// 2. Listener 구현
	rbStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
		@Override
		public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

			// 2.1 사용자가 별점을 드래그하거나 클릭 한 직후의 점수 상태를 rating 변수로 전달받는다.
			Toast.makeText(getApplicationContext(),
                     "you've got "+rating+""+" points !!",
                     Toast.LENGTH_SHORT).show();
		}
	});
}
```

