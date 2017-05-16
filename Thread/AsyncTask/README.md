# AsyncTask

##### 복잡하게 설계 해야 했던 Thread 와 Handler 를 간편하게!!

<br>

## 1. 개념

> ___AsyncTask___ 는 쉽게 말해 _Thread + Handler_ 의 역할을 해주는 것으로 생각 할 수 있다.
>
> 그간의 작업들은 Thread 를 생성하고 이에 대한 결과 수행을 Handler 에서 처리하는 구조를 가졌다.
>
> 하지만, 이러한 구조를 위해서는 Thread 를 일일히 생성하고, Receiver / Sender 를 정의해 Handler 를 부착해야 했다.
>
> ___AsyncTask___ 는 _초반 UI준비_ 부터 _비동기 작업_ 과 _마무리 결과 작업_ 까지 한번에 정의 할 수 있도록 만들어진 Class 이다.

<br>

## 2. 구현

> AsyncTask 를 상속 받기위해서는 우선 __세가지 제네릭__ 을 지정해 주어야 한다. 

- __extends AsyncTask <Parms, Progress, Result >__
  - Prams : 실행하기에 앞서 필요한 매개변수의 자료형
  - Progress : Background 작업에 사용되는 변수의 자료형
  - Result : 결과 값을 담을 자료형

<br>

> 세가지 제네릭을 지정해 준 다음에는 아래의 네가지 메소드 들을 Override 해야 한다.

- __preExecute()__

  : AsyncTask 가 실행되기 전에 준비되어야 하는 UI 들을 선언한다.

- __doInBackground__()

  : _Thread_ 의 역할을 하는 메소드로, background 에서 비동기적으로 작업해야 할 것들을 구현 한다.

- __onProgressUpdate()__

  : _Handler_ 의 역할을 하는 메소드로, doInBackground 에서 연산 된 내용을 전달 하거나 UI 에 적용한다.

- __onPostExecute()__

  : 비동기 작업을 마친 뒤의 동작을 정의 하는 메소드 이다.

<br>

```java
	public class AccumulateTask extends AsyncTask<Integer, Integer, Integer>{

        // Initiate values & UI Components
      	// 1. AsyncTask 실행 전에 Progress Dial 을 정의
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mValue = 0;

          	// 1.1 0 부터 100 까지 점진적으로 움직이는 ProgressDial 을 생성
            mDialog = new ProgressDialog(MainActivity.this);
            mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mDialog.setTitle("Updating ... ");
            mDialog.setMessage("Wait... ");
            mDialog.setCancelable(false);
            mDialog.setProgress(0);
            mDialog.setButton("Cancel", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    cancel(true);
                }
            });
            mDialog.show();

        }

        // Background Thread
      	// 2. Background 작업 정의
        @Override
        protected Integer doInBackground(Integer... params) {
          	// 2.1 mValue 를 0~100 까지 1씩 증가시키는 작업 구현
            while(mValue < 100) {
                // 2.1.1 mValue 증가
              	mValue++;             
              	// 2.1.2 Progress Update 호출
              	// onProgressUpdate 를 동작시키기 위해서는 무조건 호출 해야 함
                publishProgress(mValue);
                
              	try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return mValue;
        }

        // Handler
      	// 3. doInBackground 로 부터 지속적으로 업데이트 되는 값에 대한 동작 정의
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
          	// 3.1 ProgressBar 를 점진적으로 증가 시킴
            mDialog.setProgress(values[0]);
          	// 3.2 Progress Dial 내의 Text 값 또한 증가 시킴
            tvAdder.setText(mValue+"");

        }

        // Result
      	// 4. Background 작업이 종료 된 이후의 작업 정의
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
          	// 4.1 Progress Dialog Hide
            mDialog.dismiss();
          	// 4.2 작업 완료 Toast 생성
            Toast.makeText(MainActivity.this, 
                           "Uploading is completed !! ", 
                           Toast.LENGTH_SHORT).show();
        }
    }
```





