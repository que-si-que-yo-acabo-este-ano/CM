package com.example.iniciojsonkot


class Character(_name: String, _level: Int, _race: String,_speed: Int, _classes: MutableMap<String,Int>, _savesProficiencies: MutableSet<String>,
                _skillsProficiencies: MutableSet<String>, _skillsExpertise: MutableSet<String>, _spellsKnown: MutableList<MutableSet<String>>,
                _spellsPrepared: MutableList<MutableSet<String>>,_strength: Int, _dexterity: Int, _constitution: Int, _intelligence: Int,
                _wisdom: Int, _charisma: Int) {

    var name: String = _name
    var level: Int = _level
    var race: String = _race
    var speed: Int = _speed // TODO Sustituir por un getSpeed a la raza elegida
    var classes: MutableMap<String,Int> = _classes
    var savesProficiencies: MutableSet<String> = _savesProficiencies
    var skillsProficiencies: MutableSet<String> = _skillsProficiencies
    var skillsExpertise: MutableSet<String> = _skillsExpertise
    var spellsKnown: MutableList<MutableSet<String>> = _spellsKnown
    var spellsPrepared: MutableList<MutableSet<String>> = _spellsPrepared
    var proficiencyBonus: Int = 2 // TODO Sustituir por un getProficiency al nivel del personaje
    var strength: Int = _strength
    var dexterity: Int = _dexterity
    var constitution: Int = _constitution
    var intelligence: Int = _intelligence
    var wisdom: Int = _wisdom
    var charisma: Int = _charisma


    fun createJson():String{

        //classes
        var stringClasses = mapToString(classes)
        val json =
                "{" +
                        "\"name\": \"$name\" ," +
                        "\"level\": $level ," +
                        "\"race\": \"$race\" ," +
                        "\"speed\": $speed ," +
                        "\"classes\": $stringClasses ," +
                        "\"saveProficiencies\": $savesProficiencies ," +
                        "\"skillsProficiencies\": $skillsProficiencies ," +
                        "\"skillsExpertise\": $skillsExpertise ," +


                "}"
        return json
    }

    companion object {
        fun mapToString(jsonMap:MutableMap<String,Int>):String{
            var string = "{"
            for(key in jsonMap.keys){
                string += "\"$key\": ${jsonMap[key]},"
            }
            string += "}"
            return string
        }

        fun listOfSetToListOfString(listOfSet:MutableList<MutableSet<String>>):MutableList<String>{
            val listOfString = mutableListOf<String>()
            for(setFromList in listOfSet){
                var string = "\""
                val setFromListOrdered = setFromList.toMutableList()
                for(i in 0..(setFromListOrdered.size-1)){
                    if(i!=setFromListOrdered.size-1){
                        string+= "${setFromListOrdered[i]},"
                    }else{
                        string+= "${setFromListOrdered[i]}\""
                    }
                }
                listOfString.add(string)
            }
            return listOfString
        }

        fun listOfStringToListOfSet(listOfString:MutableList<String>): MutableList<MutableSet<String>>{
            val listOfSet = mutableListOf<MutableSet<String>>()
            for(value in listOfString){
                listOfSet.add(value.split(",").toMutableSet())
            }
            return listOfSet
        }

    }

}

class CharacterTunnel(val _name: String, val _level: Int, val _race: String, val _speed: Int, val _classes: MutableMap<String,Int>, val _savesProficiencies: MutableSet<String>,
                      val _skillsProficiencies: MutableSet<String>, val _skillsExpertise: MutableSet<String>, val _spellsKnown: MutableList<String>,
                      val _spellsPrepared: MutableList<String>, val _strength: Int, val _dexterity: Int, val _constitution: Int, val _intelligence: Int,
                      val _wisdom: Int, val _charisma: Int)