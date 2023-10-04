package com.example.notesqliteexample

import android.provider.BaseColumns

object NotesContract {
    class NotesEntry : BaseColumns {
        companion object {
            const val _ID = "id"
            const val TABLE_NAME = "notes"
            const val COLUMN_NOTE = "note"
        }
    }
}
