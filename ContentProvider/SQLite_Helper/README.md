# SQLite Helper

##### Android 에서 SQLite 를 다루는 법을 알아본다.

<br>

## 1. 개념

> Android 는 여러가지의 DBMS 중에 __SQLite__ 를 지원한다.
>
> __SQLiteOpenHelper__ 라고 하는 클래스를 이용하면 Android 내에서
>
> JAVA 코드 뿐만아니라 Query 문을 이용해 Database 를 관리 할 수 있다.
>
> __SQLiteOpenHelper__ 클래스는 다음과 같은 메소드들을 Abstract 하고 있다.

<br>

- __OnCreate()__

  : App 내에 최초의 Database 를 생성한다.

- __OnUpgrade()__

  : App 내에 있는 데이터베이스의 _Version_ 을 체크 할 수 있고,

    데이터베이스나 특정 테이블의 수정작업을 진행 할 수 있다.

- __OnOpen()__

  : 데이터베이스에 접근하려 할 때 자동적으로 실행이 되는 메서드이다.

<br>

> __추가 사항) 왜 Helper 를 사용해야 하는가??__
>
> _SQLiteOpenHelper_ 가 없어도 _SQLiteDatabse_ 를 이용해 DB 를 제어 할 수 있다.
>
> 하지만 _SQLiteDatabase_ 의 경우, DB 를 생성하거나 관리하기 위한 _openOrCreateDatabase()_ 메소드가
>
> 오직 Activity 에서만 사용해야한다는 단점이 있다. 
>
> _SQLiteOpenHelper_ 는 이러한 DB 의 생성과 관리를 외부 클래스에서 간편하게 정리 할 수 있도록 도와준다.

<br>

## 2. 구현

> _SQLite Database_ 에 접근하기 위해서는 크게 두 가지를 구현해야 한다.

- __SQLiteOpenHelper 클래스 생성__

  : 새롭게 생성할 Database 의 _이름, 테이블, 버전정보 등_ 을 _OnCreate()_ 와 _OnUpgrade()_ 를 이용해 정의 해야 한다.

```java
public class WordHelper extends SQLiteOpenHelper {
  
  // 'EngWord.db' 라는 데이터베이스 생성
  public WordHelper(Context context){
    super(context, "EngWord.db", null, 1);
  }
  
  // 'mDictionary' 테이블 추가
  @Override
  public void onCreate(SQLiteDatabase db){
    
    db.execSQL("CREATE TABLE mDictionary ("+
               "word_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
               "eng TEXT, "+
               "kor TEXT);");
  }
  
  // DB Version control
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    // 일단 삭제하고,
    db.execSQL("DROP TABLE IF EXIST mDictionary");
    // 새로이 만든다.
    onCreate(db);
    // TODO - Version 관리를 좀 더 디테일하게....
  }
}
```

<br>

- __getReadableDatabase(), getWritableDatabase()__

  : _SQLiteOpenHelper_ 에서 제공하는 _getReadableDatabase(), getWritableDatabase()_ 를 이용해 Query 를

    Android 내에서 직접 제어 할 수 있다.

  : 위의 두 메소드를 이용하는 대표적인 로직인 _Insert 구문_ 과 _Select 구문_ 을 구현해 본다.

```java
// Insert Query 작성
public void sqlInsert(){
  
  // 1. DB 핸들 획득
  SQLiteDabase db;
  // 2. helper 생성
  SQLiteOpenHelper helper;
  
  // 3. DB 읽기 권힌을 가진 helper 부착
  db = helper.getReadableDatabase();
  // 4. execSQL() 메소드를 이용한 Query 작성
  db.execSQL("INSERT INTO mDictionary VALUES(null, '태원', 'Ted');");
  db.execSQL("INSERT INTO mDictionary VALUES(null, '윤지', 'Aclie');");
  
  						...
  // 5. DB 액세스 종료                        
  helper.close();
}
```

<br>

```java
// Select Query 작성
public void sqlSelectAll(){
  
  // 1. DB 핸들 획득
  SQLiteDatabase db;
  // 2. Helper 생성
  SQLiteOpenHelper helper;
  
  // 3. DB 쓰기권한을 가진 Helper 부착
  db = helper.getWritableDatabase();
  
  // 4. rawQuery() 메소드를 이용한 Query 작성
  // Select 구문과 같이 다수의 Tuple 에 접근 해야 하는 경우에는
  // Iterator 역할을 하는 Cursor 를 이용해야한다.
  Cursor dbCursor = db.rawQuery("SELECT * FROM mDictionary");
  
  // 4.1 테이블의 속성명을 Index 화 한다.
  // 하나의 Tuple 에 저장 된 데이터들은 배열의 Index 처럼 접근 할 수 있다.
  // 따라서, 테이블의 속성명(String)을 Index 화 하는 작업을 Cursor 에서 지원한다.
  int pWord_id = dbCursor.getColumnIndex("word_id");
  int pEng = dbCursor.getColumnIndex("eng");
  int pKor = dbCursor.getColumnIndex("kor");
  
  // 4.2 Cursor 의 첫 Tuple부터 마지막 Tuple까지 순차적으로 접근한다.
  while(dbCursor.moveToNext()){
    
    WordItem item = new WordItem();
    
    // 4.2.1 Column Index 를 바탕으로 원하는 자료형을 전달받을 수 있다.
    item.index = dbCursor.getInt(pWord_id);	// getInt(index) => INTEGER 형 data
    item.eng = dbCursor.getString(pEng);	// getString(index) => TEXT 형 data
    item.kor = dbCurosr.getString(pKor);
    
    mDataSet.add(item);
    
  }
  
  // 5. DB 액세스를 마무리 한다.
  // 5.1 커서를 닫는다.
  dbCursor.close();
  // 5.2 Helper 를 닫는다.
  helper.close();
  
}
```

