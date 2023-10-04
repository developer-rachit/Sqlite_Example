package com.example.notesqliteexample

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotesDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Notes.db"

        private const val SQL_CREATE_ENTRIES = "CREATE TABLE ${NotesContract.NotesEntry.TABLE_NAME} (${NotesContract.NotesEntry._ID} INTEGER PRIMARY KEY, ${NotesContract.NotesEntry.COLUMN_NOTE} TEXT)"



        private const val SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS ${NotesContract.NotesEntry.TABLE_NAME}"
    }
}
