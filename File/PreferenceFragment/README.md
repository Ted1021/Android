# Preference Fragment

##### 간편하게 App 설정 정보를 Preference 로 저장해주는 Fragment (또는 Activity)

<br>

## 1. 개념

> App 내에서 설정 기능을 구현하기 위해서는 XML Layout 설정과 preference 를 각각 따로 구현해 주어야 하는데,
>
> __SharedPreference Fragment(Activity)__ 를 이용하면 이러한 수고를 조금이나마 덜 수 있다.
>
> __PreferenceScreen__ 이라는 일종의 Layout 에 EditText, Check Box, Switch 등의 여러 아이템을 삽입 할 수 있다.
>
> 각각의 아이템에 Key 와 Default Value 를 설정함으로써, Actvity 나 Fragment 에서 손쉽게 접근 할 수 있다.

<br>

## 2. 구현

> SharedPreference Fragment 를 구현하기 위해서는 크게 두가지 요소가 필요하다.

- __1) PreferenceScreen (XML File)__

> Linear Layout (vertical) 의 형태를 가진 특수한 Layout 이다.
>
> 이 Layout 에는 CheckBox, EditText, Switch 등의 아이템만 한정적으로 배치 할 수 있다.

<br>

> 구현하기 위한 순서는 다음과 같다.
>
> 첫째, Resource 폴더에 XML 폴더를 추가한다.
>
> 둘째, 폴더 내에 XML 파일을 새로 생성한다. 이때의 정하는 파일명으로 외부에서 접근 할 수 있다.
>
> 셋째, 갖가지 아이템을 Layout 에 배치하고, 속성값으로 key 와 default value 를 설정해 준다.

```xml
<PreferenceScreen xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:android="http://schemas.android.com/apk/res/android">

    <PreferenceCategory android:title="Personal Info">
      
      <!-- EditText 와 CheckBox 를 배치하고 각각의 View 에 Key 와 Value 를 설정해 준다. -->
      
        <EditTextPreference
            android:defaultValue="Default value"
            android:selectAllOnFocus="true"
            android:singleLine="true"
            android:title="Edit text preference"
            android:key="edit_text_preference_1"
            app:defaultValue=" - "
            app:key="userName"
            app:title="User Name"
            app:summary="input your name"
            app:icon="@android:drawable/ic_menu_edit" />
        <CheckBoxPreference
            android:defaultValue="false"
            android:title="Check box preference"
            android:key="check_box_preference_1"
            app:defaultValue="false"
            app:key="userGender"
            app:title="Gender"
            app:summary="are you male?"
            app:icon="@android:drawable/ic_menu_myplaces" />


    </PreferenceCategory>

</PreferenceScreen>
```

<br>

- __2) SettingFragment.java__

> 완성 된 XML 파일을 Fragment 에서 이용하기 위해서는 다음과 같은 작업이 필요하다.
>
> 첫째, ___PreferenceFragmentCompat___ 을 상속 받는다.
>
> 둘째, onCreate() 대신에 ___onCreatePreference()___ 에서 View 작업을 진행한다.
>
> 셋째, ___addPreferenceFromResource() 메소드___ 를 이용해 완성된 XML 파일을 가져온다.

```java
// Preference Fragment 를 사용하기 위해서는 'PreferenceFragmentCompat' 을 상속 받아야 한다
public class SettingFragment extends PreferenceFragmentCompat {


    public SettingFragment() {
        // Required empty public constructor
    }

    // 단순히 'onCreate' 생명주기에서
    // res 폴더에 미리 준비해 둔 XML 파일을 preference 로 등록해 준다
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.setting);
    }

}
```

<br>

- __3) MainActivity.java__

> Preference Fragment 가 완성이 되면 이후에는 일반적인 Preference 접근 방법과 동일하게 데이터를 이용하면 된다.
>
> Preference 핸들을 가져 올때에는 XML 파일명으로 접근하면 된다.

```java
//            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences pref = getSharedPreferences("setting", MODE_PRIVATE);
            String userName = pref.getString("userName", " - ");
            Boolean gender = pref.getBoolean("userGender", false);
```

