package com.example.wish_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val createButton: Button=findViewById(R.id.createCard)
        createButton.setOnClickListener {
            val etext:EditText=findViewById(R.id.userName)
            val name = etext.text.toString()
            Toast.makeText(this,"Name is $name",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, BirthdayGreetingActivity::class.java)
            intent.putExtra(BirthdayGreetingActivity.Name_Extra, name)
            startActivity(intent)

        }
    }
}