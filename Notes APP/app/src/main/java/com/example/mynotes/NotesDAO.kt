package com.example.mynotes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note) // This is called annotating using @ this weay we make a fuction and @ term allocates it to the DAO

    @Delete
    suspend fun delete(note:Note)

    @Query(value = "Select * from notes_table order by id ASC" )
    fun getAllNotes(): LiveData<List<Note>> //live data actually observes all the changes in the data now Livedata is in turn observed by the activity so activity will automatically know


    @Update
    suspend fun update(note: Note)


}

//We make these insertion and deletion functions in background thread,Ni=ot in main thread or UI thread as these are heavy operations and if done in main thread
//Make the app laggy ,so to overcome this we perform them n backthread using coroutines