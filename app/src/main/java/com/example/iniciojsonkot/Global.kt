package com.example.iniciojsonkot

import android.app.Application
import com.beust.klaxon.JsonObject

class Global : Application(){
    companion object{
        lateinit var spells: JsonObject
        lateinit var basicitems: JsonObject
        lateinit var items: JsonObject
    }
}