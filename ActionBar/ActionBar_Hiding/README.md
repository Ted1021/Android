# ActionBar Hiding

### 1. 개념

> 리스트 뷰의 스크롤 상태에 따라 액션바를 사라지게 하거나 다시 생기게 함으로써 좁은 스마트 폰의 화면을 조금이나마 효율적으로 사용 할 수 있다.
>
> Action Bar 의 현재 상태를 판단하고 이를 사라지게 만드는데 이용되는 메소드 들은 다음과 같다.

<br>

- _void show()_

  : 액션바를 나타나게 한다.

- _void hide()_

  : 액션바를 사라지게 한다.

- boolean isShowing()

  : 현재 액션바가 나타나 있는지 없는지를 알려주는 메소드 이다.

<br>

### 2. 구현

> 이번 프로젝트에서는 ListView 의 Scroll 을 이용해 ActionBar 에서 제공하는 다양한 함수를 응용해 본다.
>
> (사실 ListView 의 ScrollState 를 정의 하는게 더 복잡한게 함정 …)

<br>

```java
// 리스트 뷰의 스크롤 리스너를 이용해 현재 어느방향으로 스크롤링이 되고 있는지를 판단하고,
// 스크롤이 위로 올라 간다면 액션바를 보여주고,
// 스크롤이 아래로 내려 간다면 액션바를 숨긴다.
lvList.setOnScrollListener(new AbsListView.OnScrollListener() {

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

	// 현재 화면에 보여지는 아이템들의 포지션을 이용해 화면이 어느 방향으로 스크롤링 되고 있는지 판단 할 수 있다.
	@Override
	public void onScroll(AbsListView view, 
                     	int firstVisibleItem, 
                     	int visibleItemCount, 
                     	int totalItemCount) {

		// 1. ActionBar 를 가져 온다. 
		// 단, android.support.v7.app 의 ActionBar 를 가져와야만 한다.
		// 그렇지 않고 단순히 android.app 에서 제공하는 ActionBar 를 가져오게 되면
		// 항상 null 값을 리턴하기 때문에 제대로 기능을 구현 할 수 없다.
		final ActionBar mActionBar = getSupportActionBar();

		// 2. 현재 보여지는 리스트 뷰 상으로 가장 첫번쨰에 있는 position 을 가져온다.
		int currentFirstVisibleItem = lvList.getFirstVisiblePosition();
  
		// 2.1 스크롤이 이동하기 이전에 가장 마지막에 있던 position 보다 현재 position 이 더 크다면 
		//     (= 스크롤을 내리고 있다면)
		if(currentFirstVisibleItem > mLastFirstVisibleItem){
			// 2.1.1 현재 ActionBar 가 있는지 체크하고
			if(mActionBar.isShowing()){
				// 2.1.2 ActionBar 를 숨긴다.
				mActionBar.hide();
			}
		}
                
		// 2.2 스크롤이 이동하기 이전에 가장 마지막에 있던 position 보다 현재 position 이 더 작다면 
		//     (= 스크롤을 올리고 있다면)
		if(currentFirstVisibleItem < mLastFirstVisibleItem){
			// 2.2.1 현재 ActionBar 가 없는지를 체크하고
			if(!mActionBar.isShowing()){
				// 2.2.2 ActionBar 를 보여준다.
				mActionBar.show();
			}
		}
                
		// 3. 스크롤을 움직이는 행위를 마치면 현재의 position 을 가장 마지막 position 으로 변경해 준다.
		mLastFirstVisibleItem = currentFirstVisibleItem;
	}
});
```

