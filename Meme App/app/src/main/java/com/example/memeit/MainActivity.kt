package com.example.memeit

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity() {
    var currentImageuUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()
        val nextButton: Button=findViewById(R.id.nextButton)
        val shareButton: Button=findViewById(R.id.shareButton)
        nextButton.setOnClickListener { loadMeme()  }
        shareButton.setOnClickListener {
            val intent=Intent(Intent.ACTION_SEND)
            intent.type="text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,"Hey ,Checkout this awesome reddit meme $currentImageuUrl")
            val chooser = Intent.createChooser(intent,"Share Via...")
            startActivity(chooser)
        }
    }
    private fun loadMeme()
    {

// ...

// Instantiate the RequestQueue.
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        progressBar.visibility= View.VISIBLE

        val url = "https://meme-api.herokuapp.com/gimme"

// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url,null,
                Response.Listener{ response ->
                    currentImageuUrl = response.getString("url")
                    val imageView : ImageView=findViewById(R.id.memeImageView)
                    Glide.with(this).load(currentImageuUrl).listener(object : RequestListener<Drawable>{
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            progressBar.visibility=View.GONE
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            progressBar.visibility=View.GONE
                            return false
                        }
                    }).into(imageView)
                    //Note:You have used a variable for imagevie for glide into to work where as for some reason anuj bhaiya could directly use reference
                },
                Response.ErrorListener {  })

// Add the request to the RequestQueue.
       MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}