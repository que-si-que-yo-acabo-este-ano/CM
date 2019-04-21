package com.example.iniciojsonkot

import android.app.Application

class CharacterTemp : Application(){
    companion object{
        lateinit var name: String
        lateinit var race: String
        var speed: Int = 30
        var classes: Map<String,Int> = mapOf("Wizard" to 1, "Rogue" to 1)
        lateinit var background: String
        var savesProficiencies: MutableSet<String> = mutableSetOf("Strength","Constitution")
        var skillsProficiencies: MutableSet<String> = mutableSetOf("Perception")
        var skillsExpertise: MutableSet<String> = mutableSetOf()
        lateinit var spellsKnown: MutableList<MutableSet<String>>
        lateinit var spellsPrepared: MutableList<MutableSet<String>>
        lateinit var weaponsProficiencies: MutableSet<String>
        lateinit var armorsProficiencies: MutableSet<String>
        lateinit var toolsProficiencies: MutableSet<String>
        lateinit var languages: MutableSet<String>
        lateinit var equipment: MutableMap<String,Int>
        lateinit var alignment: String
        var proficiencyBonus: Int = 2
        var strength: Int = 17
        var dexterity: Int = 14
        var constitution: Int = 20
        var intelligence: Int = 11
        var wisdom: Int = 14
        var charisma: Int = 10

        // Quizás guardar aquí todo_ lo relativo a las habilidades y ventajas obtenidas por raza/clase
    }
}