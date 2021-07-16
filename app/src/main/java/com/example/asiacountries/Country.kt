package com.example.asiacountries

import org.json.JSONArray

data class Country(
        val cName : String,
        val capital:String,
        val flagurl:String,
        val region:String,
        val subregion:String,
        val population:String,
        val border:JSONArray,
        val lang:JSONArray
)
//name, capital, flag(display image in app), region,
//subregion, population, borders & languages.