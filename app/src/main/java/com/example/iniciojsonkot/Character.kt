package com.example.iniciojsonkot

class Character(_name: String, _race: String, _classChosen: String, _background: String,
                _skillsProficiencies: MutableSet<String>, _toolsProficiencies: MutableSet<String>,
                _languages: MutableSet<String>, _equipment: MutableMap<String,Int>,
                _alignment: String, _strength: Int, _dexterity: Int, _constitution: Int,
                _intelligence: Int, _wisdom: Int, _charisma: Int) {

    var name: String = _name
    var level: Int = 1
    var race: String = _race
    var speed: Int = 30 // TODO Sustituir por un getSpeed a la raza elegida
    var classes: MutableMap<String,Int> = mutableMapOf(_classChosen to 1)
    var background: String = _background
    var savesProficiencies: MutableSet<String> = mutableSetOf("Strength","Constitution") // TODO Sustituir por un getSaves a la clase elegida
    var skillsProficiencies: MutableSet<String> = _skillsProficiencies
    var skillsExpertise: MutableSet<String> = mutableSetOf()
    var spellsKnown: MutableList<MutableSet<String>> = mutableListOf()
    var spellsPrepared: MutableList<MutableSet<String>> = mutableListOf()
    var weaponsProficiencies: MutableSet<String> = mutableSetOf() // TODO Sustituir por un getWeaponsProficiencies a la clase y a la raza (y a saber si al background)
    var armorProficiencies: MutableSet<String> = mutableSetOf() // TODO Sustituir por un getArmorProficiencies a la clase y a la raza (y a saber si al background)
    var toolsProficiencies: MutableSet<String> = _toolsProficiencies
    var languages: MutableSet<String> = _languages
    var equipment: MutableMap<String, Int> = _equipment
    var alignment: String = _alignment
    var proficiencyBonus: Int = 2 // TODO Sustituir por un getProficiency al nivel del personaje
    var strength: Int = _strength
    var dexterity: Int = _dexterity
    var constitution: Int = _constitution
    var intelligence: Int = _intelligence
    var wisdom: Int = _wisdom
    var charisma: Int = _charisma


}