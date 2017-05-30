# File Managing

##### 파일 탐색기 만들어 보기

<br>

## 1. 개념

> File Class 에서 제공하는 __list() 메소드__ 를 이용해 _하위 디렉토리_ 에 관한 정보를 가져 올 수 있다.

<br>

## 2. 구현

> __List() 메소드__ 는 하위 디렉토리의 정보를 String 배열에 담는다.

```java
// 현재 디렉토리와 그 하위디렉토리의 정보를 Refresh 한다.
public void refreshFiles(){
  
  // 1. 현재 디렉토리에 관한 정보를 표시한다.
  tvCurrentPath.setText(mCurrentPath);
  // 2. 새로운 하위 디렉토리 정보를 담기 위해 기존의 정보는 모두 삭제 한다.
  mDataSet.clear();
  
  // 3. 현재 디렉토리를 기준으로 하위 디렉토리의 리스트를 받아온다.
  // 3.1 현재 디렉토리에 관한 File Descriptor 를 획득한다.
  File currentState = new File(mCurrentPath);
  
  // 3.2 File.list() 메소드를 이용해 하위 디렉토리의 정보를 받아온다.
  String[] files = currentState.list();
  
  // 3.3 받아 온 하위 디렉토리 정보를 DataSet 에 저장한다.
  // 3.3.1 하위 디렉토리가 존재하면,
  if(files != null){
    // 3.3.2 하위 디렉토리의 수 만큼 입력을 진행한다.
    for(int i=0; i<files.length(); i++){
      
      // 3.3.3 하위 디렉토리 하나에 대한 절대경로 (AbsolutePath) 를 가져온다.
      String path = mCurrentPath + "/" + files[i];
      String name;
      
      // 3.3.4 하위 디렉토리의 디렉토리 여부를 판단 하기위해 File Descriptor 획득한다.
      File file = new File(path);
      // 3.3.5 하위 경로의 디렉토리 여부에 따라 표시해 줄 내용을 달리한다.
      if(file.isDirectory()){
        name = "[" + files[i] + "]";
      }
      else{
        name = files[i];
      }
      
      // 3.3.6 dataSet 에 새로 정의된 하위 디렉토리를 저장한다.
      mDataSet.add(name);
    }    
    mAdapter.notifyDataSetChanged();
  }  
}
```

