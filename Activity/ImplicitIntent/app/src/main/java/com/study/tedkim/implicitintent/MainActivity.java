package com.study.tedkim.implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.content.Intent.ACTION_CALL;
import static android.content.Intent.ACTION_VIEW;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static int ACTION_CALL_CONTACTS = 112;
    public static int ACTION_CALL_GALLERY = 113;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    public void initView() {

        Button btnCall = (Button) findViewById(R.id.button_call);
        btnCall.setOnClickListener(this);

        Button btnDial = (Button) findViewById(R.id.button_dial);
        btnDial.setOnClickListener(this);

        Button btnContact = (Button) findViewById(R.id.button_contact);
        btnContact.setOnClickListener(this);

        Button btnWeb = (Button) findViewById(R.id.button_web);
        btnWeb.setOnClickListener(this);

        Button btnGallery = (Button) findViewById(R.id.button_gallery);
        btnGallery.setOnClickListener(this);
    }

    // 각각의 버튼을 클릭하면 특정 ACTIVITY 를 불러오는 onClick 리스너 작성
    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {

            // 1. 전화를 위한 액티비티를 불러 온다
            case R.id.button_call:

                // TODO - 특정 permission 문제로 실행이 되지 않음
                // 1.1 암시적 인텐트의 파라미터로 Intent.ACTION_CALL 을 전달한다
                intent = new Intent(ACTION_CALL);

                // 1.2 CALL 기능을 불러오기 위해서는 두가지의 전처리를 해줘야한다.
                // 1.2.1 Manifests 파일에서 ACTION_CALL 을 위한 Permission 설정을 해줘야 한다
                // 1.2.2 새로운 액티비티를 불러 올 때, 예외처리를 해주어야 한다.
                try {
                    startActivity(intent);
                }catch(Exception e){
                    e.printStackTrace();
                }

                break;

            // 2. 다이얼 액티비티를 불러 온다
            case R.id.button_dial:

                // 2.1 파라미터로 Intent.ACTION_DIAL 을 호출한다
                intent = new Intent(Intent.ACTION_DIAL);
                startActivity(intent);

                break;

            // 3. 주소록을 호출 한다
            case R.id.button_contact:

                // 3.1 ACTION_PICK 과  ACTION_GET_CONTENT 를 혼용해서 쓸 수 있다
                intent = new Intent(Intent.ACTION_PICK);
                // 3.2 주소록을 가져 올 경로를 설정 해 주어야 한다
                intent.setData(Uri.parse("content://contacts/phones"));
                // 3.3 결과 값으로 PICK 한 주소 값의 경로를 반환 한다
                startActivityForResult(intent, ACTION_CALL_CONTACTS);

                break;

            // 4. Web View 를 불러온다
            case R.id.button_web:

                // 4.1 특정 형식을 보여주기 위한 Activity 를 호출한다
                // ACTION_VIEW 는 이후에 setData 되는 데이터의 형식에 따라 그 View 의 형태를 달리한다
                intent = new Intent(ACTION_VIEW);
                // 4.2 Uri 로 http 주소를 보내고 결과적으로 Web View 를 불러온다
                intent.setData(Uri.parse("http://tedrepository.tistory.com"));
                startActivity(intent);

                break;

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

        }
    }

    // Contact 와 Gallery 에 한해 ACTION_PICK 을 호출 하였으므로, 그에 대한 결과 값을 확인 한다
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){

            // 1. Contact 에서 특정 주소록을 클릭 하였을 때,
            case 112:

                // 1.1 주소록 액티비티에서 기능 수행이 잘 되었는지 확인하고,
                if(resultCode == RESULT_OK){

                    // 1.2 선택한 주소록의 경로를 받아,
                    Uri temp = data.getData();
                    // 1.3 Toast 형태로 출력한다
                    Toast.makeText(MainActivity.this, temp.toString(), Toast.LENGTH_SHORT).show();

                }
                break;

            // 2. Gallery 에서 특정 이미지를 클릭 하였을 때,
            case 113:

                // 2.1 갤러리에서 사진 선택이 잘 되었는지를 확인하고,
                if(resultCode == RESULT_OK){

                    // 2.2 선택 한 이미지의 경로를 전달받아,
                    Uri imagePath = data.getData();

                    // 2.3 선택 한 이미지를 직접 보여 줄 새로운 Activity 를 호출한다
                    Intent intent = new Intent(ACTION_VIEW);
                    // 2.4 이미지 데이터를 불러오기 위해서는 간단하게 Uri 와 데이터 형식을 String 형태로 전달 하면 된다.
                    // 기존의 setData() 메소드를 호출하는 것이 아닌, setDataAndType() 메소드를 호출 해야 한다.
                    intent.setDataAndType(imagePath, "image/jpeg");

                    startActivity(intent);

                }
                break;
        }
    }
}
