package com.example.wish_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class BirthdayGreetingActivity : AppCompatActivity() {

    companion object{
        const val Name_Extra = "name_extra"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday_greeting)
        val name = intent.getStringExtra(Name_Extra)
        val greet: TextView=findViewById(R.id.greetText)
        greet.text="Happy Birthday $name! " +
                "Hope ma Cake piece is ready"
    }
}