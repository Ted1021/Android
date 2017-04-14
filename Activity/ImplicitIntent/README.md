

# 암시적 Intent

###### 암시적 인텐트를 통해 App 내에서 유용하게 사용가능한 System 지원 기능들에 대해 알아본다.

<br>

## 1. 개념

> ___암시적 Intent___ 는 명시적 Intent 와는 달리 현재 사용 중인 App 외부의 Activity (또는 기능) 을 가져오기 위한 Intent 이다.
>
> ___암시적 Intent___ 를 이용하면, _전화 걸기, 주소록 불러오기, 이미지 가져오기_ 등 여러가지 부가 기능들을 손쉽게 불러 올 수 있다.

<br>

## 2. 구현

> 기본적인 Intent 선언법은 매우 간단하지만, 몇몇 기능들에 대해서는 부가적으로 수행해야할 작업들이 있다.
>
> 책에 나오는 몇가지 예제를 실습해 본다. 
>
> _(각각의 기능들은 button 의 onClickListener 에서 구현 하였다.)_

<br>

- ___Dial Activity___

  : 기본적으로 스마트 폰에서 전화번호를 입력 하기 위한 숫자 키패드가 부착 된 Activity 이다.

```java
// 2. 다이얼 액티비티를 불러 온다
case R.id.button_dial:

	// 2.1 파라미터로 Intent.ACTION_DIAL 을 호출한다
	intent = new Intent(Intent.ACTION_DIAL);
	startActivity(intent);

	break;
```

<br>

- ___Contact Activity___

  : 스마트폰의 기본 주소록에 접근 할 수 있는 Activity 이다.

  : 주소록의 주소를 __read / write__하기 위해서는 __manifests 파일__에서 __permission 설정__을 해야한다.

  <br>

  _1) Manifests.xml_

```xml
    <uses-permission android:name="android.permission.READ_CONTACTS" />
    <uses-permission android:name="android.permission.WRITE_CONTACTS" />
```

<br>

  _2) MainActivity.java_

```java
// 3. 주소록을 호출 한다
case R.id.button_contact:

	// 3.1 ACTION_PICK 과  ACTION_GET_CONTENT 를 혼용해서 쓸 수 있다
	intent = new Intent(Intent.ACTION_PICK);
	// 3.2 주소록을 가져 올 경로를 설정 해 주어야 한다
	intent.setData(Uri.parse("content://contacts/phones"));
	// 3.3 결과 값으로 PICK 한 주소 값의 경로를 반환 한다
	startActivityForResult(intent, ACTION_CALL_CONTACTS);

	break;
```

<br>

- ___Web View___

  : webView widget 을 호출 하는 것이 아니라 _View Activity_ 를 호출 하고, 그 Activity 위에 원하는 형식의 데이터를 보여 준다.

  : 여기서 보여지는 데이터는 image, web 등 다양한 형태가 될 수 있다.

```java
// 4. Web View 를 불러온다
case R.id.button_web:

	// 4.1 특정 형식을 보여주기 위한 Activity 를 호출한다
	// ACTION_VIEW 는 이후에 setData 되는 데이터의 형식에 따라 
	// 그 View 의 형태를 달리한다
	intent = new Intent(ACTION_VIEW);

	// 4.2 Uri 로 http 주소를 보내고 결과적으로 Web View 를 불러온다
	// 여기서 어떤 데이터를 set 하느냐에 따라 보여지는 형태가 달라진다.
	intent.setData(Uri.parse("http://tedrepository.tistory.com"));
	startActivity(intent);

	break;
```

<br>

- ___Gallery___

  : 안드로이드의 _기본 Gallery_ 나 _Google photo_ 등 다양한 형태의 Gallery 를 불러 올 수 있다.

  : 이번 프로젝트에서는 특정 Gallery 에서 선택 된 이미지를 _ACTION_VIEW_ 를 이용해 확인 해 본다.

  <br>

  _1) View.OnClickListener()_

```java
// 5. 기본 갤러리 또는 특정 갤러리로부터 이미지를 불러온다
case R.id.button_gallery:

// 5.1 특정 이미지를 선택하기 위한 인텐트를 호출한다
intent = new Intent(Intent.ACTION_PICK);
// 5.2 이미지 데이터를 불러오기 위한 데이터의 형식과 컨테이너를 호출한다
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

// 5.3 PICK 의 결과 값으로 전달 받은 이미지를 새로운 View 에 불러온다
startActivityForResult(intent, ACTION_CALL_GALLERY);

break;
```

<br>

  _2) onActivityResult_

```java
if(requestCode == ACTION_CALL_GALLERY){
	// 2.1 갤러리에서 사진 선택이 잘 되었는지를 확인하고,
	if(resultCode == RESULT_OK){

		// 2.2 선택 한 이미지의 경로를 전달받아,
		Uri imagePath = data.getData();

		// 2.3 선택 한 이미지를 직접 보여 줄 새로운 Activity 를 호출한다
		Intent intent = new Intent(ACTION_VIEW);
		// 2.4 이미지 데이터를 불러오기 위해서는 
      	// 간단하게 Uri 와 데이터 형식을 String 형태로 전달 하면 된다.
		// 기존의 setData() 메소드를 호출하는 것이 아닌, 
      	// setDataAndType() 메소드를 호출 해야 한다.
		intent.setDataAndType(imagePath, "image/jpeg");
		startActivity(intent);

	}
}
```

