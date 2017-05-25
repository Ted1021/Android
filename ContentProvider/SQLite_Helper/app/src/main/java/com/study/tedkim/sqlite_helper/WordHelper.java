package com.study.tedkim.sqlite_helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tedkim on 2017. 5. 25..
 */

public class WordHelper extends SQLiteOpenHelper {

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
}
