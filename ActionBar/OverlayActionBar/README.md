# ActionBar Overlay

### 1. 개념

> actionBar.show() 또는 actionBar.hide() 에 의해 ActionBar 가 동적으로 움직이게 되면 기존에 있던 레이아웃이 깨지는 문제가 발생 할 수도 있다. 액션바를 투명하게 만들어 액션바 뒤에 가려진 레이아웃을 보이게 함으로써 좁은 화면을 더욱 효율적으로 사용 할 수 있을뿐 아니라 시각적 효과 또한 도모 할 수 있다.

<br>



### 2. 구현

> ActionBar Overlay 를 구현하기 위해서는 두 가지가 필요하다.

- res/style.xml

  : App 의 Theme 를 관리하는 style.xml 에서 actionBar 에 관한 속성을 추가해야 한다.

```xml
<resources>

    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
        
      	<!-- app 전체에 적용 될 테마에 actionBar Overlay Style 을 삽입해 준다. -->
        <item name="android:actionBarStyle">@style/actionBarOverlay</item>
        
    </style>

  	<!-- actionBar Overlay Style -->
  	<!-- 주의사항:API level 11 이상 부터는 actionBar Style 의 부모로 Theme.Holo 를 상속 받아야한다.-->
    <style name="actionBarOverlay"
        parent="@android:style/Theme.Holo">

      <!-- 이부분이 핵심! -->
      <!-- 다음 두 설정을 통해 actionBar 를 투명하게 만드는 option 을 활성화 한다. -->
        <item name="android:windowActionBarOverlay">true</item>
        <item name="android:windowActionModeOverlay">true</item>

    </style>

</resources>
```



- Layout padding 설정

  : 투명한 테마를 새로이 올려 두는 것으로 기존에 있던 액션바의 자리를 채우기 위해 Padding 값을 설정 해야한다.

  (사실 이부분은 잘 모르겠다...)

```xml
<RelativeLAyout
                ...
      android:paddingTop="?attr/actionBarSize">
  				...
</RelativeLAyout>
```

