package com.study.tedkim.sqlite_helper;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by tedkim on 2017. 5. 29..
 */

public interface SqlCommandImpl {

    void sqlInsert(SQLiteDatabase db);

    void sqlDelete(SQLiteDatabase db);
    void sqlDelete(SQLiteDatabase db, String targetWord);

    void sqlUpdate(SQLiteDatabase db, String targetWord);

    void sqlSelect(SQLiteDatabase db);
    void sqlSelect(SQLiteDatabase db, String targetWord);

}
