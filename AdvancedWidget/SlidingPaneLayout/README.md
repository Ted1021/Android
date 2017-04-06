# Sliding Pane Layout

###### Sliding View 를 이용해 좌측으로 쓸어 넘기거나 특정 버튼을 눌렀을 때, 원하는 뷰(레이아웃) 을 보여준다.

<br>

<br>

## 1. 개념

> 좁은 스마트폰의 화면 구성을 보다 효율적으로 사용 할 수 있는 방법 중 하나로써, API Level 21 이하에서는 SlidingDrawer 라는 개념으로 사용 되었다.
>
> SlidingDrawer 는 현재 Deprecate 되었으며, 요즘에는 ___SlidingPaneLayout___ 이라는 위젯을 사용한다.

<br>

- __Sliding Layout 참고 블로그__

  : http://blog.sqisland.com/2015/01/partial-slidingpanelayout.html?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=1429

  : 이 블로그에서는 __TextView__ 를 이용해 Sliding Layout 을 응용하는 방법을 크게 세가지로 보여주고 있다.

  <br>

  - __단순 Sliding Layout__

    => SubView 가 완전히 가려진 상태에서 왼쪽으로 화면을 스와이프 하면 숨겨진 View 가 드러난다.

  - __살짝 보이기__

    => SubView 의 존재를 보여주기 위해서 Main View 에 Margin 을 주는 방식을 사용한다.

  - __Cross-Fade__

    => SubView 의 형태를 Open 되기 전과 후로 나누어 서로 다른 View 를 보여준다.

    ###### _(현재 서비스중인 앱들에서 가장 많이 사용되는 형태이다.)_

<br>

## 2. 구현

###### _아직 Cross-Fade 형태의 SlidingPane 은 만들어보지 못했다…_

###### _단순 View 이외에 Layout 을 삽입해 유연하게 사용하는법을 학습해야한다._

<br>

> 단순 SlidingLayout 을 구현하는 것은 매우 간단하다.
>
> 특정 Activity (또는 Fragment) 의 Layout XML 파일에서 ___SlidingPaneLayout___ 을 삽입해 주면 된다.

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"

    tools:context="com.study.tedkim.slidingpanelayout.MainActivity">
  
  <!-- SlidingPaneLayout 을 구현하고자 하는 레이아웃에 아래의 View 를 삽입한다. -->
  <!-- 해당 프로젝트에서는 두개의 TextView를 삽입하고 각각의 Layout_width를 조절해 화면을 분할했다.-->
    <android.support.v4.widget.SlidingPaneLayout

        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/sliding_pane_layout">

  
        <TextView
            android:layout_width="240dp"
            android:layout_height="match_parent"
            android:text="pane_1"
            android:background="@color/colorPrimaryDark"
            android:textAppearance="@style/TextAppearance.AppCompat.Display1">

        </TextView>

        <TextView
            android:layout_width="400dp"
            android:layout_height="match_parent"
            android:text="pane_2"
            android:background="@android:color/holo_blue_light"
            android:layout_marginLeft="64dp"
            android:textAppearance="@style/TextAppearance.AppCompat.Display1">

        </TextView>



    </android.support.v4.widget.SlidingPaneLayout>

</RelativeLayout>
```

