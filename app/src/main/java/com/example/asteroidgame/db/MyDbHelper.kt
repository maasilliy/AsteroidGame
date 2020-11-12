package com.example.firstonkotlin.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(contex:Context):SQLiteOpenHelper(contex, MyDbNameClass.DATABASE_NAME,
null, MyDbNameClass.DATABASE_VERSION) {
    //Создание таблицы
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(MyDbNameClass.CREAT_TABLE)
    }
    //Обновление таблицы
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(MyDbNameClass.SQL_DELETE_TABLE)
        onCreate(db)
    }

}