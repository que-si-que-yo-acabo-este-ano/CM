package com.example.iniciojsonkot

import android.app.Application

class Character : Application(){
    companion object{
        lateinit var name: String
        lateinit var race: String
        var classes: Map<String,Int> = mapOf("Wizard" to 1, "Rogue" to 1)
        lateinit var background: String
        lateinit var skillsProficiency: MutableSet<String>
        lateinit var skillsExpertise: MutableSet<String>
        lateinit var spellsKnown: Map<Int,String>
        lateinit var spellsPrepared: Map<Int,String>
        lateinit var weaponsProficiency: MutableSet<String>
        lateinit var armorsProficiency: MutableSet<String>
        lateinit var toolsProficiency: MutableSet<String>
        lateinit var languages: MutableSet<String>
        lateinit var equipment: Map<String,Int>
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