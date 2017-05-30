# File Managing

##### java.io.File  을 이용한 Binary File 입출력

<br>

## 1. 개념

- __1) File Class__

> 파일 내부의 컨텐츠를 조작하기에 앞서 우선 파일 자체의 _생성, 삭제 등_ 의 관리를 위해 JAVA 에서 제공하는 FILE 클래스를 다뤄야 한다.
>
> __파일 클래스__ 는 커널구조 상에서 _어플리케이션_ 과 _파일시스템_ 의 중간 다리 역할을 맡는 __System Call__ 역할을 맡게 된다.
>
> __파일 클래스__ 는 파일을 핸들링 할 수 있는 __File Descriptor__ 역할을 맡아 파일에 대한 _제어권_ 을 획득하게 된다.
>
> 파일 클래스는 작업이 끝난 뒤에는 __File.close()__ 를 이용해 반드시 파일에 대한 제어권을 반환해야 한다.

<br>

- __2) File Stream Class__

> 파일 클래스를 통해 조작에 대한 제어권을 획득하게 되면, 이제는 파일 내부 컨턴츠를 읽고, 쓸 수 있게 된다.

<br>

## 2. 구현

> 구현부는 크게 두가지로 나뉜다.
>
> 첫째, File 클래스를 생성해 File Descriptor 를 획득한다.
>
> 둘째, FileInputStream / FileOutputStream 클래스를 통해 File 내부 Contents 를 읽고 쓴다.

<br>

- __1) FileInputStream__

> __FileStreamOutput__ 은 File 내부의 Binary contents 를 읽기 위한 Stream class 이다.

```java
// binary 파일 읽기
    public void fileRead(){
        try{

            // 1. File Write 와 마찬가지로 "getFilesDir()" 을 이용해 앱의 저장 경로를 가져온다
            File testFile = new File(getFilesDir(), FILENAME);

            // 2. File 이 존재 하는지 알아보고 예외 처리를 해 준다
            if(!testFile.exists()){
                Toast.makeText(MainActivity.this, 
                               "File doesn't exist", 
                               Toast.LENGTH_SHORT).show();
                return;
            }

            // 3. 존재하는 파일을 불러오는 File Stream 을 생성한다
            // way_1) "OpenFileInput()" Method 를 불러온다 
          	// ("getFilesDir()" 로 desc 를 생성하지 않은 경우,)
//            FileInputStream fis = openFileInput(FILENAME, MODE_PRIVATE);

            // way_2) FileInputStream 을 새로이 생성한다
            FileInputStream fis = new FileInputStream(testFile);
            // 4. 읽기 속도 향상을 위해 File Buffer 를 생성한다
            BufferedInputStream bis = new BufferedInputStream(fis);

            // 5. binary File 을 읽어온다
            // 5.1 File Stream 의 크기만큼 byte 배열을 동적 생성 한다
            byte[] buf = new byte[fis.available()];
            // 5.2 File Stream 의 끝까지 순차적으로 읽어온다
            while(fis.read(buf) != -1);

            // 6. File Buffer 와 File Stream 을 순차적으로 닫아준다
            bis.close();
            fis.close();

            Log.i("TEST",">>>>>>>"+new String(buf));

        }catch(Exception e){
            e.printStackTrace();
        }

    }

```

<br>

- __2) FileOutputStream__

> FileOutputStream 을 이용해 File 내에 String 을 Binary Contents 로 변환해  입력한다.

```java
public void fileWrite(){
  
  String contents = "this is test of binary file I/O";
  
  try{

	// 1. File Descriptor 를 생성 할 때에는 꼭 "getFilesFir()" 을 매개 변수로 전달 해야 한다.
    File testFile = new File(getFilesDir(), FILENAME);
    
    // 2. 파일이 존재하는지 체크하고, 없다면 해당 Directory 에 새 파일을 생성한다.
    if(!testFile.exists()){
      testFile.createNewFile();
    }
    
    // 3. 획득한 Descriptor 를 이용해 binary data 를 File 에 입력한다.
    // 3.1 Binary File 을 쓰기 위해 Stream class 를 생성한다.
    FileOutputStream fos = new FileOutputStream(testFile);
    // 3.2 효율적인 파일쓰기를 위해 File Buffer Stream 을 생성한다.
    BufferedOutputStream bos = new BufferedOutputStream(fis);
    // 3.3 준비된 String 을 Binary data 로 변환에 Write() 메소드로 입력한다.
    bos.write(contents.getBytes());
    
    // 4. Buffer Stream 과 File Stream 을 순차적으로 닫아준다.
    bos.close();
    fos.close();
    
  }catch(Exception e){
    e.printStackTrace();
  }
}
```

