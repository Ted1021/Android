package com.study.tedkim.editactionbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvAction;
    String mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAction = (TextView) findViewById(R.id.textView_action);
    }

    // 미리 만들어 둔 action_bar_example.xml 을 현재의 activity 에 inflate 하도록 하는 함수이다.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // 1. 메뉴를 생성하기 위한 inflater 정의
        MenuInflater inflater = getMenuInflater();
        // 2. inflater 에 미리 준비해 둔 action_bar_example.xml 을 menu 에 대입한다.
        inflater.inflate(R.menu.action_bar_example, menu);
        // 3. 초기화 된 menu 객체를 반환한다.
        return super.onCreateOptionsMenu(menu);
    }

    // 구체화 된 actionBar 의 item 들이 선택되었을 때의 동작을 정의한다.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // 1. 받은 item 의 id 를 받아 온다.
        switch(item.getItemId()){
            // 1.1 Home Icon 의 경우, - API Level 15 이상부터는 지원하지 않는다.
            case android.R.id.home:
                mText = "Application Icon";
                tvAction.setText("home");
                break;

            // 1.2 Add 버튼의 경우, showAsAction=ifRoom|withText 의 효과로
            // 공간에 여유가 있을 시, 아이콘과 텍스트를 한꺼번에 표시한다. (아닐땐 아이콘만!)
            case R.id.item1:
                mText = "Action item, with Text, displayed if room exists";
                tvAction.setText("Add");
                break;

            // 1.3 Always 버튼의 경우, showAsAction=always 의 효과로
            // 어떠한 화면 상태던지 간에 무조건 ActionBar 에 아이콘으로 표시된다.
            case R.id.item2:
                mText = "Action item, icon only, always displayed";
                tvAction.setText("Always");
                break;

            // 1.4 Normal 버튼의 경우, 아무런 옵션을 추가 하지않은 일반적인 상태로
            // ActionBar 에 공간이 모자라다면 '...' 의 형태로 오버플로형식으로 나타난다.
            case R.id.item3:
                mText = "Normal menu item";
                tvAction.setText("Normal");
                break;

            default:
                return false;
        }
        Toast.makeText(this, mText, Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }
}
