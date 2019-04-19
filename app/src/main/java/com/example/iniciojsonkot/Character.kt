package com.example.iniciojsonkot

import android.app.Application

class Character : Application(){
    companion object{
        lateinit var name: String
        lateinit var race: String
        lateinit var classes: Map<String,Int>
        lateinit var background: String
        lateinit var skillsProficiency: List<String>
        lateinit var skillsExpertise: List<String>
        lateinit var spellsKnown: List<String>
        lateinit var spellsPrepared: List<String>
        lateinit var weaponsProficiency: List<String>
        lateinit var armorsProficiency: List<String>
        lateinit var toolsProficiency: List<String>
        lateinit var languages: List<String>
        lateinit var equipment: List<String>
        lateinit var alignment: String
        var proficiencyBonus: Int = 0
        var strength: Int = 0
        var dexterity: Int = 0
        var constitution: Int = 0
        var intelligence: Int = 0
        var wisdom: Int = 0
        var charisma: Int = 0

        // Quizás guardar aquí todo_ lo relativo a las habilidades y ventajas obtenidas por raza/clase


    }
}