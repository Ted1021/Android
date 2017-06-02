package com.study.tedkim.contentprovider_basic;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by tedkim on 2017. 6. 2..
 */

public class EWProvider extends ContentProvider {

    static final String AUTHORITY = "com.study.tedkim.contentprovider";
    static final Uri CONTENT_URI = Uri.parse("content://com.study.tedkim.contentprovider/word");
    static final int ALL_WORD = 1;
    static final int SINGLE_WORD = 2;

    SQLiteDatabase mDB;
    WordHelper mHelper;

    // TODO - UriMatcher 사용법 알아보기
    static final UriMatcher mMatcher;
    static {

        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI("com.study.tedkim.contentprovider", "word", ALL_WORD);
        mMatcher.addURI("com.study.tedkim.contentprovider", "word/*", SINGLE_WORD);

    }
    @Override
    public boolean onCreate() {

        mHelper = new WordHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        if(mMatcher.match(uri) == ALL_WORD)
            return "vnd."+AUTHORITY+".cursor.dir/word";

        else if(mMatcher.match(uri) == SINGLE_WORD)
            return "vnd."+AUTHORITY+".cursor.item/word";

        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        String sql = null;

        // 전체 Dir 에 대한 쿼리 명령
        if(mMatcher.match(uri) == ALL_WORD)
            sql = "SELECT eng, kor FROM mDictionary;";

        else if(mMatcher.match(uri) == SINGLE_WORD)
            sql += "WHERE eng ='"+ uri.getPathSegments().get(1) +"';";

        mDB = mHelper.getReadableDatabase();
        Cursor cursor = mDB.rawQuery(sql, null);

        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        mDB = mHelper.getWritableDatabase();
        long row = mDB.insert("mDictionary", null, values);
        if(row > 0){
            Uri notiUri = ContentUris.withAppendedId(CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(notiUri, null);
        }

        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int count = 0;

        switch (mMatcher.match(uri)) {

            case ALL_WORD:

                count = mDB.delete("mDictionary", selection, selectionArgs);

                break;

            case SINGLE_WORD:

                String where;
                where = "eng = '" + uri.getPathSegments().get(1) + "' ";
                if (TextUtils.isEmpty(selection) == false)
                    where += "AND " + selection;

                count = mDB.delete("mDictionary", where, selectionArgs);

                break;

        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        int count = 0;
        String where = null;
        if(mMatcher.match(uri) == SINGLE_WORD){

            where = "eng = '"+ uri.getPathSegments().get(1) +"'";
            if(TextUtils.isEmpty(selection) == false)
                where += "AND" + selection;

        }

        count = mDB.update("mDictionary", values, where, selectionArgs);

        return count;
    }
}
