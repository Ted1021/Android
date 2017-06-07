package com.study.tedkim.loadingimage2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLoadIamge, btnDelete;
    ImageView ivImage;

    static final String TARGET_URL = "https://www.fingo.vn/cdn/images/logo.png";

    File mFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView(){

        btnLoadIamge = (Button) findViewById(R.id.button_loadImage);
        btnLoadIamge.setOnClickListener(this);

        btnDelete = (Button) findViewById(R.id.button_delete);
        btnDelete.setOnClickListener(this);

        ivImage = (ImageView) findViewById(R.id.imageView_image);

    }

    @Override
    public void onClick(View v) {

        int index;
        String fileName;

        switch(v.getId()){

            case R.id.button_loadImage:

                // check file from 'TARGET_URL'
                index = TARGET_URL.lastIndexOf("/")+1;
                // extract fileName from url (dir)
                fileName = TARGET_URL.substring(index);
                // obtain file descriptor based on fileName
                mFile = new File(getFilesDir(), fileName);

                if(mFile.exists()){

                    Toast.makeText(MainActivity.this, "File already exists!!", Toast.LENGTH_SHORT).show();
                    getFile(mFile);

                }
                else{

                    Toast.makeText(MainActivity.this, "File doesn't exists!! Now on downloading ...", Toast.LENGTH_SHORT).show();
                    setFile(mFile);
                }
                break;

            case R.id.button_delete:

                // check file from 'TARGET_URL'
                index = TARGET_URL.lastIndexOf("/")+1;
                // extract fileName from url (dir)
                fileName = TARGET_URL.substring(index);
                // obtain file descriptor based on fileName
                mFile = new File(getFilesDir(), fileName);

                if(mFile.exists()) {
                    mFile.delete();
                    Toast.makeText(MainActivity.this, "File is deleted", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "File doesn't exist", Toast.LENGTH_SHORT).show();


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
            Log.e("FILE_PATH", ">>>>>>>>"+file.getAbsolutePath());

            Bitmap bitmap = getUrl();

            // TODO - Bitmap 은 2차원 데이터이기 떄문에 추가적인 변환 작업이 필요하다

            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            // Convert Bitmap to Binary
            // TODO - NullPointerException HERE !!!
            int size = bitmap.getWidth() * bitmap.getHeight();
            ByteBuffer byteBuffer = ByteBuffer.allocate(size);
            bitmap.copyPixelsToBuffer(byteBuffer);

            bos.write(byteBuffer.array());
            ivImage.setImageBitmap(bitmap);

            bos.close();
            fos.close();

        }catch(Exception e){

            e.printStackTrace();
        }

    }

    public Bitmap getUrl(){

        new AsyncTask<String, Bitmap, Bitmap>(){

            @Override
            protected Bitmap doInBackground(String... params) {

                try {

                    URL url = new URL(TARGET_URL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){

                        InputStream is = connection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(is);

                        publishProgress(bitmap);

                        is.close();

                        return bitmap;
                    }

                }catch(Exception e){

                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onProgressUpdate(Bitmap... values) {
                super.onProgressUpdate(values);

//                ivImage.setImageBitmap(values[0]);

            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);

                Toast.makeText(MainActivity.this, "Image downloaded !!", Toast.LENGTH_SHORT).show();

            }
        }.execute(TARGET_URL);

        return null;
    }

    public byte[] bitmapToByte(Bitmap bitmap){

//        int width = bitmap.getWidth();
//        int height = bitmap.getHeight();



        return null;
    }

}
