package com.saarthak.a7minuteworkout.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "History_DB"
        private val TABLE_NAME = "history"
        private val COLUMN_ID = "_id"
        private val COMPLETION_DATE = "date_completed"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "create table $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY, $COMPLETION_DATE TEXT)"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists $TABLE_NAME")

        onCreate(db)
    }

    fun addDate(date : String){

        val value = ContentValues()
        value.put(COMPLETION_DATE, date)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, value)
        db.close()
    }

    fun getItems() : ArrayList<String>{

        val items = ArrayList<String>()
        val db = this.readableDatabase

        val cursor = db.rawQuery("select * from $TABLE_NAME", null)
//        val cursor = db.query(TABLE_NAME, arrayOf(COLUMN_ID, COMPLETION_DATE),
//            null, null, null, null, COMPLETION_DATE, " desc")

        while (cursor.moveToNext()){
            val item = cursor.getString(cursor.getColumnIndex(COMPLETION_DATE))
            items.add(item)
        }

        cursor.close()
        db.close()
        return items
    }

}