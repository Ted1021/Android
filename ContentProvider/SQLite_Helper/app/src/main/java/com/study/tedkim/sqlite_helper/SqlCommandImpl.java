package com.study.tedkim.sqlite_helper;

/**
 * Created by tedkim on 2017. 5. 29..
 */

public interface SqlCommandImpl {

    void sqlInsert();

    void sqlDelete();
    void sqlDelete(String targetWord);

    void sqlUpdate(String targetWord);

    void sqlSelect();
    void sqlSelect(String targetWord);

}
