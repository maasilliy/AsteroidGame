package com.example.firstonkotlin.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.core.text.parseAsHtml
import java.util.ArrayList

class MyDbManager(context: Context) {
    val MyDbHelper = com.example.firstonkotlin.db.MyDbHelper(context)
    var db:SQLiteDatabase? = null

    fun openDb(){
        db = MyDbHelper.writableDatabase
    }

    fun insertToDb(title:Int){
        val  values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_TITLE, title)
        }
        db?.insert(MyDbNameClass.TABLE_NAME, null, values)
    }

    fun readDbData() : Int{
        val dataList = ArrayList<Int>()
        val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, null,
            null, null, null, null)

        while (cursor?.moveToNext()!!){
            val dataText = cursor.getInt(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE))
            dataList.add(dataText)
        }
        cursor.close()

        return dataList.sum()
    }

    fun closeDb(){
        MyDbHelper.close()
    }
}