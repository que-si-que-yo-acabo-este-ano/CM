package com.example.iniciojsonkot

import android.content.Context
import com.beust.klaxon.Klaxon
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.lang.StringBuilder


class Character(_name: String, _level: Int, _race: String,_speed: Int, _maxHealth: Int, _currentHealth: Int, _armor: Int, _classes: MutableMap<String,Int>, _savesProficiencies: MutableSet<String>,
                _skillsProficiencies: MutableSet<String>, _skillsExpertise: MutableSet<String>, _spellsKnown: MutableList<MutableSet<String>>,
                _spellsPrepared: MutableList<MutableSet<String>>,_strength: Int, _dexterity: Int, _constitution: Int, _intelligence: Int,
                _wisdom: Int, _charisma: Int) {

    var name: String = _name
    var race: String = _race
    var speed: Int = _speed
    var maxHealth: Int = _maxHealth
    var currentHealth : Int = _currentHealth
    var armor: Int = _armor
    var classes: MutableMap<String,Int> = _classes
    val level: Int
        get() {
            var total = 0
            for (classLevel in classes.values){
                total += classLevel
            }
            return total
        }
    var savesProficiencies: MutableSet<String> = _savesProficiencies
    var skillsProficiencies: MutableSet<String> = _skillsProficiencies
    var skillsExpertise: MutableSet<String> = _skillsExpertise
    var spellsKnown: MutableList<MutableSet<String>> = _spellsKnown
    var spellsPrepared: MutableList<MutableSet<String>> = _spellsPrepared
    val proficiencyBonus: Int
        get() = listOf(0,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,6).get(level) // Si fallara mirar si es por ser val en vez de var
    var strength: Int = _strength
    var dexterity: Int = _dexterity
    var constitution: Int = _constitution
    var intelligence: Int = _intelligence
    var wisdom: Int = _wisdom
    var charisma: Int = _charisma



    fun createJson(context:Context){
        //classes
        val stringClasses = mapToString(classes)

        //savesProficiencies
        val stringSavesProficiencies = setToString(savesProficiencies)

        //skillsProficiencies
        val stringSkillsProficiencies = setToString(skillsProficiencies)

        //savesProficiencies
        val stringSkillsExpertise = setToString(skillsExpertise)

        //spellsKnown
        val stringSpellsKnown = listOfSetToListOfString(spellsKnown)

        //spellsPrepared
        val stringSpellsPrepared = listOfSetToListOfString(spellsPrepared)

        //json
        val json =
                "{" +
                        "\"name\": \"$name\" ," +
                        "\"level\": $level ," +
                        "\"race\": \"$race\" ," +
                        "\"speed\": $speed ," +
                        "\"maxHealth\": $maxHealth ," +
                        "\"currentHealth\": $currentHealth ," +
                        "\"armor\": $armor ," +
                        "\"classes\": $stringClasses ," +
                        "\"savesProficiencies\": $stringSavesProficiencies ," +
                        "\"skillsProficiencies\": $stringSkillsProficiencies ," +
                        "\"skillsExpertise\": $stringSkillsExpertise ," +
                        "\"spellsKnown\": $stringSpellsKnown ," +
                        "\"spellsPrepared\": $stringSpellsPrepared ," +
                        "\"strength\": $strength ," +
                        "\"dexterity\": $dexterity ," +
                        "\"constitution\": $constitution ," +
                        "\"intelligence\": $intelligence ," +
                        "\"wisdom\": $wisdom ," +
                        "\"charisma\": $charisma ," +
                "}"

        //creatingFile
        val filename = "$name.json"
        val fos = context.openFileOutput(filename, Context.MODE_PRIVATE)
        fos.write(json.toByteArray())
        fos.close()
        println("==========")
        println(json)
    }

    fun deleteCharacter(context:Context){
        Global.characters.remove(this)
        context.deleteFile("$name.json")
        println(context.fileList().size)
        println(Global.characters.size)
    }

    companion object {
        fun loadCharacters(context: Context){
            println("=====FICHEROS=====")
            println(context.fileList().size)
            for(fileName in context.fileList()){
                println(fileName)
                val pattern = "([\\w]+).json".toRegex()
                val name = pattern.replace(fileName,"$1")
                Global.characters.add(createCharacterFromJson(name,context))
            }
            println(context.fileList().size)

        }

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
                        string+= "${setFromListOrdered[i]}"
                    }
                }
                string+= "\""
                listOfString.add(string)
            }
            println("-----------------")
            println(listOfString)
            return listOfString
        }

        fun listOfStringToListOfSet(listOfString:MutableList<String>): MutableList<MutableSet<String>>{
            val listOfSet = mutableListOf<MutableSet<String>>()
            for(value in listOfString){
                listOfSet.add(value.split(",").toMutableSet())
            }
            return listOfSet
        }

        fun setToString(setOfString:MutableSet<String>):String{
            var string = "["
            val listOfSet = setOfString.toMutableList()
            for(i in 0..(listOfSet.size -1)){
                if(i!=listOfSet.size-1){
                    string+= "\"${listOfSet[i]}\","
                }else{
                    string+= "\"${listOfSet[i]}\""
                }
            }
            string+="]"
            return string
        }

        fun createCharacterFromJson(name: String,context: Context):Character{
            val filename = "$name.json"
            val fileInputStream = context.openFileInput(filename)
            val inputStream = InputStreamReader(fileInputStream)
            val buffered = BufferedReader(inputStream)
            val stringBuilder = StringBuilder()
            var line = buffered.readLine()
            while(line != null){
                stringBuilder.append(line)
                line = buffered.readLine()
            }
            fileInputStream.close()

            val jsonString = stringBuilder.toString()
            lateinit var character:Character
            if(!jsonString.equals("")){
                val result = Klaxon()
                    .parse<CharacterTunnel>("""$jsonString""")
                character = Character(result!!.name,result!!.level,result!!.race,result!!.speed,result!!.maxHealth,result!!.currentHealth,result!!.armor,result!!.classes,result!!.savesProficiencies,
                    result!!.skillsProficiencies,result!!.skillsExpertise, listOfStringToListOfSet(result!!.spellsKnown),listOfStringToListOfSet(result!!.spellsPrepared),
                    result!!.strength,result!!.dexterity,result!!.constitution,result!!.intelligence,result!!.wisdom,result!!.charisma)
            }
            return character
        }
    }

}

class CharacterTunnel(val name: String, val level: Int, val race: String, val speed: Int, val maxHealth: Int, val currentHealth: Int, val armor:Int, val classes: MutableMap<String,Int>, val savesProficiencies: MutableSet<String>,
                      val skillsProficiencies: MutableSet<String>, val skillsExpertise: MutableSet<String>, val spellsKnown: MutableList<String>,
                      val spellsPrepared: MutableList<String>, val strength: Int, val dexterity: Int, val constitution: Int, val intelligence: Int,
                      val wisdom: Int, val charisma: Int)
