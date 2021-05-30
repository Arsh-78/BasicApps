package com.example.a56book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a56book.models.IPostAdapter
import com.example.a56book.models.Post
import com.example.a56book.models.PostAdapter
import com.example.a56book.userDAO.PostDao
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.common.SignInButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase

class MainActivity() : AppCompatActivity(), IPostAdapter,NoticeDialogFragment.NoticeDialogListener{
    private lateinit var adapter: PostAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var postDao : PostDao



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.item, menu)
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab: FloatingActionButton =findViewById(R.id.fab)

        recyclerView = findViewById(R.id.recyclerView)
        fab.setOnClickListener{
            val intent = Intent(this, CreatePostactivity::class.java)
            startActivity(intent)


        }
        setUpRecyclerView()
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.logOutButton -> {



                // Create an instance of the dialog fragment and show it
                val dialog = NoticeDialogFragment()
                dialog.show(supportFragmentManager, "NoticeDialogFragment")

            true
        }




        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }



    private fun setUpRecyclerView() {
        postDao = PostDao()
        val postCollections = postDao.postCollection
        val query  = postCollections.orderBy("createdAt", Query.Direction.DESCENDING)
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<Post>().setQuery(query,Post::class.java).build()

        adapter = PostAdapter(recyclerViewOptions,this)
        recyclerView.adapter=adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }



    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }


    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onLikeClicked(postId: String) {
        postDao.updateLikes(postId)
    }

    override  fun onDialogPositiveClick(dialog: DialogFragment) {

        val auth = Firebase.auth
        auth.signOut()
        val signActitvity= Intent(this,SignINActivity::class.java)
        startActivity(signActitvity)
        auth.signOut()

    }


    override fun onDialogNegativeClick(dialog: DialogFragment) {
        val text = "Cool Enjoy your posts"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
        // User touched the dialog's negative button
    }

}