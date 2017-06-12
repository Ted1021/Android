# HttpURLConnection

##### HttpURLConnection 을 이용해 특정 Url 의 xml 데이터를 TEXT 형태로 전달 받아 본다.



## 1. 개념

> HttpURLConnection 에서 제공하는 메소드들을 이용해
>
> Binary, Text, XML, JSON 등 다양한 형태의 데이터들을 읽어 올 수 있다.
>
> 제공되는 메소드는 다음과 같다.

<br>

- __URL.openConnection()__

  : 특정 URL 에 대한 연결을 요청하는 메소드이다.

  : 연결이 되게 되면 GET, POST 등의 요청을 전달 할 수 있게 된다.

- __Connection.setRequestMethod()__

  : 이 메소드의 매개변수로 _GET, POST_ 등의 요청을 서버로 전달하게 된다.

- __Connection.getResponseCode()__

  : 앞서 보낸 _GET, POST_ 요청에 대한 서버의 응답을 체크하는 메소드이다.

  : _HTTP_OK, HTTP_BAD_REQUEST_ 등 다양한 응답 결과를 정의 하는 상수 들이 있으며,

  : 결과에 알맞는 기능을 구현 할 수 있다.

- __Connection.getInputStream()__

  : 성공적으로 서버로부터 응답을 전달 받으면, Connection 객체로부터 요청한 Data 를 이용 할 수 있다.

<br>

## 2. 구현

> 구현에 앞서 몇가지 설정이 필요하다.

- __Permission 설정 (Manifests 파일)__

  ```xml
  <uses-permission android:name="android.permission.INTERNET"/>
  ```

- __AsyncTask 구현__

  > Network 나 DB 에 접근하는 것과 같이 ANR 을 야기할 수 있는 작업들은 Main Thread 상에서 구현 할 수 없다.
  >
  > 따라서, _Thread / Handler_ 나 _AsyncTask_ 를 이용해 작업해야 한다.

<br>

> AsyncTask 내의 doInBackground 에서 URL 에 접근한다.

```java
@override
protected Void doInBackground(String... params) {
  
  try{
    
    // 1. String 형태의 주소를 URL 로 변환 시켜 준다.
    URL url = new URL(params[0]);
    // 2. 변환 된 URL 에 대해 연결을 활성화 시킨다.
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    // 3. 연결 된 서버로 GET 요청을 보낸다.
    connection.setRequestMethod("GET");
    // 4. 서버로 부터 응답을 정상적으로 받았으면,
    if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
      // 데이터 읽기를 시작한다. 
      
      // 4.1 getInputStream 을 이용해 raw 데이터를 읽어온다.
      InputStreamReader isr = new InputStreamReader(connection.getInputStream());
      BufferedReader br = new BufferedReader(isr);
      
      // 4.2 String 형태의 데이터로 받아 올 준비를 한다.
      // Raw 데이터를 한줄씩 받아와 String Builder 에 붙여준다.
      StringBuilder xml = new StringBuilder();
      String singleLine="";
      
      while( (singleLine = br.readLine()) != null){
        xml.append(singleLine);
      };
      
      // 5. UI 작업을 위해 완성된 String 데이터를 OnProgressUpdate 메소드에 전달 한다.
      publishProgress(xml.toString());
    }
    
  }catch(Exception e){
    e.printStackTrace();
  }
}
```

