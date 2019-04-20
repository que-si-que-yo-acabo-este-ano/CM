package com.example.iniciojsonkot

import com.beust.klaxon.JsonObject

class Searchers {

    companion object {

        fun searchSpell(spell: String): List<JsonObject>{
            val res = Global.spells.array<JsonObject>("spell")!!.filter {
                it.string("name") == spell
            }
            return res
        }

        fun searchComponentsFromSpell(spell: List<JsonObject>): String{
            val res = spell.map {
                it.obj("components")
            }

            return res[0]!!.toString()
        }

        fun searchMaterialFromComponents(spell:List<JsonObject>): String{
            val res = spell.map {
                it.obj("components")!!.string("m")
            }

            return res[0]!!.toString()
        }

        fun searchLevelFromSpell(spell: List<JsonObject>):String{
            val res = spell.map{
                it.int("level")
            }

            return res[0]!!.toString()
        }

        fun searchSpellsByLevel(level: Int,classes: Set<String>): List<String?>{
            val spellsList = Global.spells.array<JsonObject>("spell")!!.filter {
                it.int("level") == level
            }.filter {
                !it.obj("classes")!!.array<JsonObject>("fromClassList")!!.filter {
                    classes.contains(it.string("name"))
                }.isEmpty()
            }.map {
                it.string("name")
            }

            return spellsList
        }

        fun searchSpellsByLevels(levels: List<Int>,classes: Set<String>): Map<Int,List<String?>>{
            val mutableRes :MutableMap<Int,List<String?>>  = mutableMapOf()
            for(level in levels){
                mutableRes[level] = searchSpellsByLevel(level,classes)
            }
            val res : Map<Int,List<String?>> = mutableRes
            return res
        }

        fun searchCastingTimeFromSpell(spell: List<JsonObject>):String{
            val time = spell.map {
                it.array<JsonObject>("time")
            }.get(0)
            val res = time!!.map{
                it!!.int("number").toString() + " " + it.string("unit")
            }

            return res[0]!!.toString()
        }

        fun searchDescriptionFromSpell(spell: List<JsonObject>):String{
            var text: String = ""

            val description = spell.map {
                it.array<Any>("entries")
            }

            for(i in 0..(description[0]!!.size-1)){
                val positionText = description[0]!![i]
                if(positionText !is String){
                    val descriptionObject = positionText as JsonObject
                    if(descriptionObject.string("type").equals("list")){
                        val items = descriptionObject.array<String>("items")
                        for(i in 0..(items!!.size-1)){
                            text += "\n * " + items[i]
                        }
                    }else{
                        text += "\n * "
                        val entries = descriptionObject.array<String>("entries")
                        for(i in 0..(entries!!.size-1)){
                            if(i==0){
                                text += entries[i]
                            }else{
                                text += "\n" + entries[i]
                            }
                        }
                    }


                }else{
                    text += "\n" + positionText
                }

            }

            return text
        }
    }

}