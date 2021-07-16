package com.example.asiacountries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var madapter:CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView : RecyclerView = findViewById(R.id.recylerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
         fetchData()
        madapter = CountryListAdapter()
        recyclerView.adapter=madapter
    }
    private fun fetchData() {

// ...

// Instantiate the RequestQueue.

        val url = "https://restcountries.eu/rest/v2/region/asia"

// Request a string response from the provided URL.
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener {
                    val  userArrray :JSONArray =it
                    val countryArray = ArrayList<Country>()
                    for(i in 0 until userArrray.length()){
                        val countryObject= userArrray.getJSONObject(i)
                        val country= Country(
                        countryObject.getString("name"),countryObject.getString("capital"),
                        countryObject.getString("flag"),countryObject.getString("region"),
                        countryObject.getString("subregion"),countryObject.getInt("population").toString(),
                        countryObject.getJSONArray("borders"),countryObject.getJSONArray("languages")
                        )
                        countryArray.add(country)


                    }

                    madapter.updateCountry(countryArray)
                },
                Response.ErrorListener { error ->
                    val toast =Toast.makeText(this,"OOps Some Error Has occured Sorry",Toast.LENGTH_SHORT)
                    toast.show()
                }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest)
    }
}