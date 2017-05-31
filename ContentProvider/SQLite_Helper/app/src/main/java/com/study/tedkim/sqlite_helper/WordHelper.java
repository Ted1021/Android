package com.study.tedkim.sqlite_helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.study.tedkim.sqlite_helper.MainFragment.mDataSet;

/**
 * Created by tedkim on 2017. 5. 25..
 */

public class WordHelper extends SQLiteOpenHelper implements SqlCommandImpl {

    public WordHelper(Context context) {
        super(context, "EngWord.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE myDictionary (" +
                "word_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "eng TEXT, " +
                "kor TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS myDictionary");
        onCreate(db);
    }


    @Override
    public void sqlInsert(SQLiteDatabase db) {

        db = getWritableDatabase();
        String kor="a", eng="A";

        for(int i=0; i<20; i++)
            db.execSQL("INSERT INTO myDictionary VALUES (null,'"+(kor+i)+"', '"+(eng+i)+"')");
    }

    @Override
    public void sqlDelete(SQLiteDatabase db) {

        db = getWritableDatabase();
        db.execSQL("DELETE FROM myDictionary;");

    }

    @Override
    public void sqlDelete(SQLiteDatabase db, String targetWord) {

        db = getWritableDatabase();
        db.execSQL("DELETE FROM myDictionary WHERE eng='"+targetWord+"';");

    }

    @Override
    public void sqlUpdate(SQLiteDatabase db, String targetWord) {

        db = getWritableDatabase();
        db.execSQL("UPDATE myDictionary SET eng='"+targetWord+"' WHERE kor='태원';");
    }

    @Override
    public void sqlSelect(SQLiteDatabase db) {

        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM myDictionary", null);

        int pIndex = cursor.getColumnIndex("word_id");
        int pEng = cursor.getColumnIndex("eng");
        int pKor = cursor.getColumnIndex("kor");

        while(cursor.moveToNext()){

            WordItem item = new WordItem();

            item.index = cursor.getInt(pIndex);
            item.eng = cursor.getString(pEng);
            item.kor = cursor.getString(pKor);

            mDataSet.add(item);

        }

        cursor.close();
    }

    @Override
    public void sqlSelect(SQLiteDatabase db, String targetWord) {

        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM myDictionary WHERE eng='+"+targetWord+"';", null);

        int pIndex = cursor.getColumnIndex("word_id");
        int pEng = cursor.getColumnIndex("eng");
        int pKor = cursor.getColumnIndex("kor");

        while(cursor.moveToNext()){

            WordItem item = new WordItem();

            item.index = cursor.getInt(pIndex);
            item.eng = cursor.getString(pEng);
            item.kor = cursor.getString(pKor);

            mDataSet.add(item);
        }

        cursor.close();

    }
}
