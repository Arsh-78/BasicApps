package com.example.a56book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.a56book.userDAO.PostDao

class CreatePostactivity : AppCompatActivity() {

    private lateinit var postDao: PostDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_postactivity)
        val postButton : Button = findViewById(R.id.postButtom)

        postDao= PostDao()
        postButton.setOnClickListener {
            val postInput : EditText = findViewById(R.id.postInput)
            val input = postInput.text.toString().trim()
            if(input.isNotEmpty()) {
                postDao.addPost(input)
                finish()//This makes it return to previous Activity

            }

        }
    }
}