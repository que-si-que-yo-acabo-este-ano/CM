package com.example.iniciojsonkot

import android.app.Application
import com.beust.klaxon.JsonObject

class Global : Application(){
    companion object{
        lateinit var spells: JsonObject

        var personaje = Character("Orpheus",5,"Gnome",25,30,20,18,mutableMapOf("Wizard" to 5, "Rogue" to 2),mutableSetOf("Intelligence","Wisdom"),mutableSetOf("Arcana","Investigation","Perception"),mutableSetOf(),
            mutableListOf(mutableSetOf("Firebolt"),mutableSetOf("Alarm"),mutableSetOf(),mutableSetOf("Blink","Fireball"),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf()),
            mutableListOf(mutableSetOf("Firebolt"),mutableSetOf("Alarm"),mutableSetOf(),mutableSetOf("Fireball"),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf()),
            8,14,16,18,15,11)
        //lateinit var personaje: Character
        var loadedCharacter: Character = Character("",0,"",0,0,0,0,mutableMapOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),
            mutableListOf(mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf()),
            mutableListOf(mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf()),
            10,10,10,10,10,10)
        /*var loadedCharacter: Character = Character("Orpheus",5,"Gnome",25,30,20,18,mutableMapOf("Wizard" to 5, "Rogue" to 2),mutableSetOf("Intelligence","Wisdom"),mutableSetOf("Arcana","Investigation","Perception"),mutableSetOf(),
            mutableListOf(mutableSetOf("Firebolt"),mutableSetOf("Alarm"),mutableSetOf(),mutableSetOf("Blink","Fireball"),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf()),
            mutableListOf(mutableSetOf("Firebolt"),mutableSetOf("Alarm"),mutableSetOf(),mutableSetOf("Fireball"),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf()),
            8,14,16,18,15,11)*/
        var characters: MutableList<Character> = mutableListOf()
    }
}