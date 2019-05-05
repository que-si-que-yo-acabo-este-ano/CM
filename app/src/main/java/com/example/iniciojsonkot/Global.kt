package com.example.iniciojsonkot

import android.app.Application
import com.beust.klaxon.JsonObject

class Global : Application(){
    companion object{
        lateinit var spells: JsonObject
        lateinit var basicitems: JsonObject
        lateinit var items: JsonObject
        var personaje = Character("Reloth",5,"Gnome",25,mutableMapOf("Wizard" to 5),mutableSetOf("Intelligence","Wisdom"),mutableSetOf("Arcana","Investigation","Perception"),mutableSetOf(),
            mutableListOf(mutableSetOf("Firebolt"),mutableSetOf("Alarm"),mutableSetOf(),mutableSetOf("Blink","Fireball"),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf()),
            mutableListOf(mutableSetOf("Firebolt"),mutableSetOf("Alarm"),mutableSetOf(),mutableSetOf("Fireball"),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf()),
            8,14,16,18,15,11)
        //lateinit var personaje: Character
    }
}