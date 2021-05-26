package com.example.mynotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) :  AndroidViewModel(application) {

    val allNotes : LiveData<List<Note>>  /*We get all notes from repository which intern need Notes Dao which gets its feed from Notes database*/

    val repository:NoteRepo
    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepo(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }
    fun insertNote(note: Note)=viewModelScope.launch (Dispatchers.IO){
        repository.insert(note)
    }
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.update(note)
    }

}

