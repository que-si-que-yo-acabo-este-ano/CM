package com.example.iniciojsonkot

import android.app.Application
import com.beust.klaxon.JsonObject

class Global : Application(){
    companion object{
        lateinit var spells: JsonObject
        lateinit var basicitems: JsonObject
        lateinit var items: JsonObject
        var personaje = Character("Reloth",5,"Gnome",25,mutableMapOf("Wizard" to 5),mutableSetOf("Intelligence","Wisdom"),mutableSetOf("Arcana","Investigation","Perception"),mutableSetOf("Perception"),
            mutableListOf(mutableSetOf("Firebolt"),mutableSetOf("Alarm"),mutableSetOf("Blink","Fireball")),
            mutableListOf(mutableSetOf("Firebolt"),mutableSetOf("Alarm"),mutableSetOf("Fireball")),
            8,14,16,18,15,11)
        //lateinit var personaje: Character
    }
}