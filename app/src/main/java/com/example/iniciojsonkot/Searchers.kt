package com.example.iniciojsonkot

import com.beust.klaxon.JsonArray
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

        //To use the next function you need to use the next if statement:
        // val components = searchComponentsFromSpell(spell)
        //if(components.equals("v,s,m") or components.equals("v,m") or components.equals("s,m") or components.equals("m"))

        fun searchMaterialFromComponents(spell:List<JsonObject>): String{
            var text = ""
            val components = spell.map {
                it.obj("components")!!
            }[0]
            val material = components["m"]
            if(material is String){
                text = material
            }else{
                val materialJson = material as JsonObject
                var materialText = materialJson.string("text")
                text = materialText.toString()
            }
            return text
        }

        fun searchLevelFromSpell(spell: List<JsonObject>):String{
            val res = spell.map{
                it.int("level")
            }

            return res[0]!!.toString()
        }

        fun searchSpellsByLevel(level: Int): List<String>{
            val spellsList = Global.spells.array<JsonObject>("spell")!!.filter {
                it.int("level") == level
            }.map {
                it.string("name").toString()
            }
            return spellsList
        }

        fun searchSpellsByLevelAndClasses(level: Int,classes: Set<String>): List<String?>{
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

        fun searchSpellsByLevelsAndClasses(levels: List<Int>,classes: Set<String>): Map<Int,List<String?>>{
            val mutableRes :MutableMap<Int,List<String?>>  = mutableMapOf()
            for(level in levels){
                mutableRes[level] = searchSpellsByLevelAndClasses(level,classes)
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

            for(i in 0..(description[0]!!.size-1)) {
                val positionText = description[0]!![i]
                if (positionText !is String) {
                    val descriptionObject = positionText as JsonObject
                    val typeFromDescriptionObject = descriptionObject.string("type")
                    if (typeFromDescriptionObject.equals("list")) {
                        val items = descriptionObject.array<String>("items")
                        for (i in 0..(items!!.size - 1)) {
                            text += "\n * " + items[i]
                        }
                    }else if(typeFromDescriptionObject.equals("table")){
                        text+="\n ${descriptionObject.string("caption")}\n"
                        val colLabels = descriptionObject.array<String>("colLabels")
                        for(i in 0..(colLabels!!.size-1)){
                            text+="${colLabels[i]} "
                        }
                        val rows = descriptionObject.array<Any>("rows")
                        for(i in 0..(rows!!.size-1)){
                            val row = rows[i] as JsonArray<String>
                            text+= "\n${row[0]}     ${row[1]}"
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
            val pattern = "[{]@(dice|condition|hit|damage|creature|spell|scaledice|filter|item) ([\\w]+)[}]".toRegex()

            return pattern.replace(text,"$2")
        }

        fun searchDurationFromSpell(spell: List<JsonObject>): String{
            var text : String = ""

            val duration = spell.map {
                it.array<JsonObject>("duration")
            }[0]
            val type = duration!!.string("type")[0]
            if(type.equals("timed")){
                val durationInfo = duration!!.obj("duration")
                if(!durationInfo.filter { it!!.boolean("upTo") != null }.isEmpty()){
                    text+= "Up to "
                }
                val amount = durationInfo.int("amount")[0]
                val type = durationInfo.string("type")[0]
                text+= "$amount $type"
            }else if(type.equals("permanent")){
                text+= "Until "
                val ends = duration.map {
                    it.array<String>("ends")
                }[0]
                for(i in 0..(ends!!.size-1)){
                    var end : String
                    when(ends[i].equals("trigger")){
                        true -> end = "triggered"
                        false -> end = "dispelled"
                    }
                    if(i==0){
                        text+= end
                    }else{
                        text+= " or $end"
                    }
                }

            }else{
                text+= "Instantaneous"
            }

            return text
        }


        fun searchRangeFromSpell(spell: List<JsonObject>): String{
            var text = ""
            val range = spell.map {
                it.obj("range")
            }[0]
            val type = range!!.string("type")
            if(type.equals("point")){
                val distance = range!!.obj("distance")
                val typeDistance = distance!!.string("type")
                if(typeDistance.equals("self")){
                    text+= "Self"
                }else if(typeDistance.equals("touch")){
                    text+= "Touch"
                }else if(typeDistance.equals("feet")){
                    val amount = distance!!.int("amount")
                    text+= "$amount feet"
                }else if(typeDistance.equals("miles")){
                    val amount = distance!!.int("amount")
                    text+= "$amount miles"
                }else if(typeDistance.equals("sight")){
                    text+= "Sight"
                }else if(typeDistance.equals("unlimited")){
                    text+= "Unlimited"
                }
            }else if(type.equals("radius")){
                val distance = range!!.obj("distance")
                val typeDistance = distance!!.string("type")
                if(typeDistance.equals("feet")){
                    val amount = distance!!.int("amount")
                    text+= "$amount feet"
                }else if(typeDistance.equals("miles")){
                    val amount = distance!!.int("amount")
                    text+= "$amount miles"
                }
            }else if(type.equals("special")){
                text+= "Special"
            }else if(type.equals("cone")){
                val distance = range!!.obj("distance")
                val amount = distance!!.int("amount")
                text+= "Self($amount-foot cone)"
            }else if(type.equals("line")){
                val distance = range!!.obj("distance")
                val amount = distance!!.int("amount")
                text+= "Self($amount-foot line)"
            }else if(type.equals("hemisphere")) {
                val distance = range!!.obj("distance")
                val amount = distance!!.int("amount")
                text += "Self($amount-foot-radius hemisphere)"
            }else if(type.equals("sphere")) {
                val distance = range!!.obj("distance")
                val amount = distance!!.int("amount")
                text += "Self($amount-foot-radius sphere)"
            }else if(type.equals("cube")) {
                val distance = range!!.obj("distance")
                val amount = distance!!.int("amount")
                text += "Self($amount-foot cube)"
            }

            return text
        }

        //CLASES
        fun searchClass(classCharacter: String): List<JsonObject>{
            val url = "https://raw.githubusercontent.com/TheGiddyLimit/TheGiddyLimit.github.io/master/data/class/class-$classCharacter.json"
            val data = MainActivity.DownloadGit().execute(url).get()
            return data.array<JsonObject>("class")!!.filter {
                it.string("name").equals(classCharacter.capitalize())
            }
        }

        fun searchSavesProfiencies(classCharacter: List<JsonObject>): MutableSet<String>{
            return classCharacter.map {
                it.array<String>("proficiency")
            }[0]!!.toMutableSet()
        }
    }

}