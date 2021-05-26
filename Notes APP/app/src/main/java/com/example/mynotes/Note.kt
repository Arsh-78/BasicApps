package com.example.mynotes

import androidx.room.ColumnInfo
import androidx.room.Entity //necessary import for making the table or database
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class Note(@ColumnInfo(name="text") val text: String) {
    @PrimaryKey(autoGenerate = true) var id =0
}