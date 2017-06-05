package com.study.tedkim.loadingimage2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLoadIamge;
    ImageView ivImage;

    static final String TARGET_URL = "https://www.fingo.vn/cdn/images/logo.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView(){

        btnLoadIamge = (Button) findViewById(R.id.button_loadImage);
        btnLoadIamge.setOnClickListener(this);

        ivImage = (ImageView) findViewById(R.id.imageView_image);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button_loadImage:

                // check file from 'TARGET_URL'
                int index = TARGET_URL.lastIndexOf("/")+1;
                // extract fileName from url (dir)
                String fileName = TARGET_URL.substring(index);
                // obtain file descriptor based on fileName
                File file = new File(getFilesDir(), fileName);

                if(file.exists()){

                    Toast.makeText(MainActivity.this, "File already exists!!", Toast.LENGTH_SHORT).show();
                    getFile(file);

                }
                else{

                    Toast.makeText(MainActivity.this, "File doesn't exists!! Now on downloading ...", Toast.LENGTH_SHORT).show();
                    setFile(file);
                }
                break;

        }

    }

    public void getFile(File file){

        String path = file.getAbsolutePath();
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        ivImage.setImageBitmap(bitmap);

    }


    public void setFile(File file){

        try{

            file.createNewFile();
            Bitmap bitmap = getUrl();

            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            bos.write(bitmap.getRowBytes());
            ivImage.setImageBitmap(bitmap);

            bos.close();
            fos.close();

        }catch(Exception e){

            e.printStackTrace();
        }

    }

    public Bitmap getUrl(){

        new AsyncTask<String, Void, Bitmap>(){

            @Override
            protected Bitmap doInBackground(String... params) {

                try {

                    URL url = new URL(TARGET_URL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){

                        InputStream is = connection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(is);

                        is.close();

                        return bitmap;
                    }

                }catch(Exception e){

                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);

                Toast.makeText(MainActivity.this, "Image downloaded !!", Toast.LENGTH_SHORT).show();

            }
        }.execute(TARGET_URL);

        return null;
    }

}
