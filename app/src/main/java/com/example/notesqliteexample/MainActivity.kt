package com.example.notesqliteexample

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: NotesDBHelper
    private lateinit var db: SQLiteDatabase
    private lateinit var editTextNote: EditText
    private lateinit var textViewLoadedNote: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = NotesDBHelper(this)
        db = dbHelper.writableDatabase

        editTextNote = findViewById(R.id.editTextNote)
        textViewLoadedNote = findViewById(R.id.textViewLoadedNote)

        val buttonSave = findViewById<Button>(R.id.buttonSave)
        val buttonLoad = findViewById<Button>(R.id.buttonLoad)

        buttonSave.setOnClickListener {
            val note = editTextNote.text.toString()
            if (note.isNotEmpty()) {
                insertNote(note)
                editTextNote.text.clear()
            }
        }

        buttonLoad.setOnClickListener {
            val loadedNote = loadNote()
            textViewLoadedNote.text = "Loaded Note: $loadedNote"
        }
    }

    private fun insertNote(note: String) {
        val values = ContentValues()
        values.put(NotesContract.NotesEntry.COLUMN_NOTE, note)
        db.insert(NotesContract.NotesEntry.TABLE_NAME, null, values)
    }

    private fun loadNote(): String {
        val projection = arrayOf(NotesContract.NotesEntry.COLUMN_NOTE)
        val cursor: Cursor = db.query(
            NotesContract.NotesEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val note: String

        if (cursor.moveToFirst()) {
            note = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_NOTE))
        } else {
            note = "No notes found."
        }

        cursor.close()
        return note
    }
}
