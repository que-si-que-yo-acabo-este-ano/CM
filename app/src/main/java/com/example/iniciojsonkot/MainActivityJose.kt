package com.example.iniciojsonkot

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.beust.klaxon.*
import java.io.*
import java.lang.StringBuilder
import java.net.URL

class MainActivityJose : AppCompatActivity() {

    lateinit var spells: JsonObject
    lateinit var basicitems: JsonObject
    lateinit var items: JsonObject

    //clase para leer desde git y generar el Json


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_jose)
        //Carga de datos
        loadDataFromGit()
        val cargaButton = findViewById(R.id.spell) as TextView
        cargaButton.setOnClickListener{
            val intent = Intent(this,SpellView::class.java)
            val spell = searchSpell("Aid")
            val components = searchComponentsFromSpell(spell)

            if(components == "v,s,m"){
                val material = searchMaterialFromComponents(spell)
                intent.putExtra("material",material)
            }

            //test
            //println(searchLevelFromSpell(spell))
            //println(searchSpellsByLevel(0))
            //println(searchCastingTimeFromSpell(spell))
            println(searchDescriptionFromSpell(spell))
            val levels : List<Int>  = listOf(0,1,2)
            //println(searchSpellsByLevels(levels))

            intent.putExtra("components",components)
            startActivity(intent)

        }

    }

    class DownloadGit : AsyncTask<String,Void, JsonObject>(){
        override fun doInBackground(vararg params: String?): JsonObject {
            val gitJson = URL(params[0]).readText()
            val stringBuilder = StringBuilder(gitJson)
            val parser = Parser.default()
            return parser.parse(stringBuilder) as JsonObject
        }

        override fun onPostExecute(result: JsonObject?) {
            super.onPostExecute(result)
        }
    }

    fun loadDataFromGit(){
        spells = DownloadGit().execute("https://raw.githubusercontent.com/TheGiddyLimit/TheGiddyLimit.github.io/master/data/spells/spells-phb.json").get()
        basicitems = DownloadGit().execute("https://raw.githubusercontent.com/TheGiddyLimit/TheGiddyLimit.github.io/master/data/basicitems.json").get()
        items = DownloadGit().execute("https://raw.githubusercontent.com/TheGiddyLimit/TheGiddyLimit.github.io/master/data/items.json").get()
        /*val file = File("prueba.json")
        file.createNewFile()
        File("prueba.json").writeText("{}")*/
        val prueba = openFileOutput("dondeEsta.json", Context.MODE_PRIVATE)
        prueba.use {
            prueba.write("DONDE ESTÁS ==========================================================".toByteArray())
        }
        val fileInputStream = openFileInput("dondeEsta.json")
        val inputStream = InputStreamReader(fileInputStream)
        val buffered = BufferedReader(inputStream)
        val stringBuilder = StringBuilder()

        stringBuilder.append(buffered.readText())
        println("==========")
        println(stringBuilder.toString())
    }

    //TODO
    // searchDescriptionFromSpell
    // searchCastingTimeFromSpell
    // searchDurationFromSpell
    // searchRitualFromSpell

    fun searchSpell(spell: String): List<JsonObject>{
        val res = spells.array<JsonObject>("spell")!!.filter {
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

    fun searchSpellsByLevel(level: Int): List<String?>{
        val res = spells.array<JsonObject>("spell")!!.filter {
            it.int("level") == level
        }.map {
            it.string("name")
        }

        return res
    }

    fun searchSpellsByLevels(levels: List<Int>): Map<Int,List<String?>>{
        val mutableRes :MutableMap<Int,List<String?>>  = mutableMapOf()
        for(level in levels){
            mutableRes[level] = searchSpellsByLevel(level)
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
        val description = spell.map{
            it.array<String>("entries")
        }
        var entries: String = ""

        for(entry in description.get(0)!!.listIterator()){
            //entries += entry
            entries += "\n" + entry
        }
        return entries
    }




    fun btnClicked(view: View) {
        val primero = spells.array<JsonObject>("spell")?.filter {
            it.obj("classes")?.array<JsonObject>("fromClassList")?.filter {
                it.string("name").equals("Wizard")
            }!!.any() and it.string("name").equals("Web")
        }?.map {
            it.string("name")
        }

        val tv = findViewById(R.id.textView) as TextView
        tv.text = "$primero"
    }
}


