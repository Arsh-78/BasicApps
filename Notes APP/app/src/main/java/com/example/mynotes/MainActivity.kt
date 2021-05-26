 package com.example.mynotes

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.InvalidationTracker
import java.util.*

 class MainActivity : AppCompatActivity(), INotesRVAdapter {

    lateinit var viewModel :NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.Recycle)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this,this)
        recyclerView.adapter=adapter

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java) // On inline codelabs thjs is enough  but it might throw error so to rectify it we use ViewModelProvider.AndroidViewModeFactory.getInstance(application))
        viewModel.allNotes.observe(this, Observer{list-> list?.let { adapter.updateList(it) }


        })//to get data

    }

     override fun onItemClicked(note: Note) {
         viewModel.deleteNote(note)
         Toast.makeText(this,"${note.text}Deleted",Toast.LENGTH_LONG).show()
     }

     override fun onItemClicked2(note: Note) {
         val input :EditText = findViewById(R.id.input)
         val noteText =  input.text.toString()
         if(noteText.isNotEmpty()){
             viewModel.updateNote(Note(noteText))
             Toast.makeText(this,"${noteText} Updated",Toast.LENGTH_SHORT).show()
         }

     }

     fun submitNote(view: View) {
         val input :EditText = findViewById(R.id.input)
         val noteText =  input.text.toString()
         if(noteText.isNotEmpty()){
             viewModel.insertNote(Note(noteText))
             Toast.makeText(this,"${noteText} Inserted",Toast.LENGTH_LONG).show()
         }
     }
 }
 //You also use another way of implementing on click as you declare a function in onclick attribute of submit button and then define the function here