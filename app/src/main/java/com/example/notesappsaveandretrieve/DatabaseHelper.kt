package com.example.notesappsaveandretrieve

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context): SQLiteOpenHelper(context,"details.db", null, 2) {
    private val sqLiteDatabase: SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("create table notes (pk INTEGER PRIMARY KEY AUTOINCREMENT, Note text)")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS notes")
        onCreate(p0)
    }

    fun saveData(note: String) {
        val contentValues = ContentValues()
        contentValues.put("Note", note)
        sqLiteDatabase.insert("notes", null, contentValues)
    }

    fun readData(): ArrayList<Notes>{
        val notes = arrayListOf<Notes>()

        // Read all data using cursor
        val cursor: Cursor = sqLiteDatabase.rawQuery("SELECT * FROM notes", null)

        if(cursor.count < 1){  // Handle empty table
            println("No Data Found")
        }else{
            while(cursor.moveToNext()){  // Iterate through table and populate people Array List
                val pk = cursor.getInt(0)  // The integer value refers to the column
                val note = cursor.getString(1)
                notes.add(Notes(pk, note))
            }
        }
        return notes
    }
}
