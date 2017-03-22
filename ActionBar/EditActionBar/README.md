# ActionBar Basic

### 1. 개념

> 기존의 Title Bar 의 남는 공간을 활용해 효율적인 UI 를 제공하기 위한 방법이다. API Level 11 이후부터 지원 된다.
>
> 주로 Title Bar 오른편에 __아이콘__ 또는 __텍스트__ 형식의 버튼을 생성하고 이들을 위한 기능을 정의 할 수 있다.
>
> 화면의 사이즈 (또는 상태) 에 따라 속성도 덧붙여 줄 수 있는데 이는 다음과 같다.

- __ifRoom__

   : 활용 할 공간이 존재 한다면, 아이콘을 배치한다. 공간이 없다면 오버레이 버튼의 리스트로 삽입된다.

- __always__

  : 화면의 상태와는 상관 없이 무조건 아이콘을 배치한다.

- __withText__

  : '|'(or) 연산과 함께 사용하며, 기본적으로 표시되는 아이콘이외에 지정해준 텍스트(Title 속성) 와 함께 표시되도록 한다.

<br>



### 2. 구현

> ActionBar 를 구성하고 이에 대한 제어를 하기 위해서는 크게 다음 세가지가 필요하다.
<br>

##### 1) res/menu/ 에서 actionBar.xml 생성

> 리소스 폴더에서 menu 폴더를 새로 생성 해 준 뒤,  actionBar 를 위한 레이아웃을 제작한다.

```xml
<?xml version="1.0" encoding="utf-8"?>

<!-- API Level 15 이후로 ActionBar 에 속성을 부여하기 위해서는 'xmlns:app' 을 가져와야 한다. -->
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

  <!-- 임의의 서로다른 세 아이템 정의 -->
  <!-- 액션바 화면에 여유가 있다면(ifRoom), 아이콘과 텍스트를 함께 표기하도록 한다(|withText). --> 
  <item
        android:id="@+id/item1"
        android:icon="@android:drawable/ic_menu_add"
        android:title="Add"
           
        app:showAsAction="ifRoom|withText"
        />
 
  <!-- 해당 아이콘은 항상 표시(always)하도록 한다. -->
    <item
        android:id="@+id/item2"
        android:title="Action item with icon"
        android:icon="@android:drawable/ic_menu_search"

        app:showAsAction="always"
        />
  
  <!-- 아무 속성도 부여하지 않은 Default 상태의 아이템이다. 화면 공간이 모자라면 오버레이 버튼으로 들어간다.-->
    <item
        android:title="Normal item"
        android:id="@+id/item3"
        />

</menu>
```

<br>

##### 2) onCreateOptionMenu(Menu menu)

> 이 함수를 이용해 미리 만들어 둔 메뉴 레이아웃을 현재의 액티비티에 삽입한다.

```java
// 미리 만들어 둔 action_bar_example.xml 을 현재의 activity 에 inflate 하도록 하는 함수이다.
@Override
public boolean onCreateOptionsMenu(Menu menu) {

	// 1. 메뉴를 생성하기 위한 inflater 정의
	MenuInflater inflater = getMenuInflater();
	// 2. inflater 에 미리 준비해 둔 action_bar_example.xml 을 menu 에 대입한다.
	inflater.inflate(R.menu.action_bar_example, menu);
	// 3. 초기화 된 menu 객체를 반환한다.
	return super.onCreateOptionsMenu(menu);
}
```

<br>

##### 3) onOptionItemSelected(MenuItem item)

> inflate 된 menu 의 item_id 를 가져오고 각각의 item 이 선택 되었을때의 동작을 정의 한다.

```java
// 구체화 된 actionBar 의 item 들이 선택되었을 때의 동작을 정의한다.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // 1. 받은 item 의 id 를 받아 온다.
        switch(item.getItemId()){
            // 1.1 Home Icon 의 경우, - API Level 15 이상부터는 지원하지 않는다.
            case android.R.id.home:
                mText = "Application Icon";
                tvAction.setText("home");
                break;

            // 1.2 Add 버튼의 경우, showAsAction=ifRoom|withText 의 효과로
            // 공간에 여유가 있을 시, 아이콘과 텍스트를 한꺼번에 표시한다. (아닐땐 아이콘만!)
            case R.id.item1:
                mText = "Action item, with Text, displayed if room exists";
                tvAction.setText("Add");
                break;

            // 1.3 Always 버튼의 경우, showAsAction=always 의 효과로
            // 어떠한 화면 상태던지 간에 무조건 ActionBar 에 아이콘으로 표시된다.
            case R.id.item2:
                mText = "Action item, icon only, always displayed";
                tvAction.setText("Always");
                break;

            // 1.4 Normal 버튼의 경우, 아무런 옵션을 추가 하지않은 일반적인 상태로
            // ActionBar 에 공간이 모자라다면 '...' 의 형태로 오버플로형식으로 나타난다.
            case R.id.item3:
                mText = "Normal menu item";
                tvAction.setText("Normal");
                break;

            default:
                return false;
        }
        Toast.makeText(this, mText, Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }

```

