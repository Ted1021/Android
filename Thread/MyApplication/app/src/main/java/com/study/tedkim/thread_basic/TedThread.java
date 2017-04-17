package com.study.tedkim.thread_basic;

import static com.study.tedkim.thread_basic.MainActivity.mSubCount;

/**
 * Created by tedkim on 2017. 4. 17..
 */

// Custom Thread Class 를 구현합니다
// 1. Thread Class 를 상속받습니다
public class TedThread extends Thread {

    // 2. Run 메소드를 Override 합니다
    @Override
    public void run() {
        super.run();

        // 3. Thread 가 어떤 동작을 수행하게 될 지 정의 합니다
        // 무한 반복문으로 초당 1씩 변수가 증가하도록 만들어봅니다
        while (true) {
            // 3.1 Thread 가 MainActivity 의 mSubCount 를 1씩 증가 시키는 일을 수행합니다
            mSubCount++;

            // 3.2 ThreadSleep 을 이용해 1초에 한번씩 잠을 잡니다
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
