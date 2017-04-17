package com.study.tedkim.thread_basic;

import static com.study.tedkim.thread_basic.MainActivity.mSubCount;

/**
 * Created by tedkim on 2017. 4. 17..
 */

// Custom Runnable Class 를 구현합니다
// 1. Runnable Interface 를 상속받습니다 (핵심!)
public class TedRunnable implements Runnable {

    // 2. Thread 와 마찬가지로 Run 메소드를 Override 합니다
    @Override
    public void run() {
        // 3. Thread 가 어떤 동작을 수행 할 지 정의 합니다
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