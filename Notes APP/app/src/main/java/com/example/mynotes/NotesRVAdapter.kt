package com.example.mynotes

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class NotesRVAdapter(private val context:Context,private val listener: INotesRVAdapter) : RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    private val allNotes = ArrayList<Note>()
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textView : TextView = itemView.findViewById(R.id.Text)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
        val updateButton : ImageView=itemView.findViewById(R.id.updateButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val  viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        viewHolder.updateButton.setOnClickListener{
            listener.onItemClicked2(allNotes[viewHolder.adapterPosition])

        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text=currentNote.text


    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList :List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}
interface INotesRVAdapter{
    fun onItemClicked(note: Note)
    fun onItemClicked2(note: Note)
} //We use this interface to handle clicks of view holder here itself,Interface is basically used for data abstraction in class to directly implement methods and functions