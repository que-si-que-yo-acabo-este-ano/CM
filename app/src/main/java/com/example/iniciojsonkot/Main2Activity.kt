package com.example.iniciojsonkot

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*
import android.widget.*
import com.beust.klaxon.JsonObject
import com.example.iniciojsonkot.Global.Companion.basicitems
import com.example.iniciojsonkot.Global.Companion.items
import com.example.iniciojsonkot.Global.Companion.spells


class Main2Activity : AppCompatActivity() {
    var global = Global()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val spellsToShow : MutableSet<Int> = mutableSetOf()

        val cantrips = findViewById<ToggleButton>(R.id.cantrips)
        cantrips?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(0)
            }else{
                spellsToShow.remove(0)
            }
        }
        val spellsLvl1 = findViewById<ToggleButton>(R.id.spellsLvl1)
        spellsLvl1?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(1)
            }else{
                spellsToShow.remove(1)
            }
        }
        val spellsLvl2 = findViewById<ToggleButton>(R.id.spellsLvl2)
        spellsLvl2?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(2)
            }else{
                spellsToShow.remove(2)
            }
        }
        val spellsLvl3 = findViewById<ToggleButton>(R.id.spellsLvl3)
        spellsLvl3?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(3)
            }else{
                spellsToShow.remove(3)
            }
        }
        val spellsLvl4 = findViewById<ToggleButton>(R.id.spellsLvl4)
        spellsLvl4?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(4)
            }else{
                spellsToShow.remove(4)
            }
        }
        val spellsLvl5 = findViewById<ToggleButton>(R.id.spellsLvl5)
        spellsLvl5?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(5)
            }else{
                spellsToShow.remove(5)
            }
        }
        val spellsLvl6 = findViewById<ToggleButton>(R.id.spellsLvl6)
        spellsLvl6?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(6)
            }else{
                spellsToShow.remove(6)
            }
        }
        val spellsLvl7 = findViewById<ToggleButton>(R.id.spellsLvl7)
        spellsLvl7?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(7)
            }else{
                spellsToShow.remove(7)
            }
        }
        val spellsLvl8 = findViewById<ToggleButton>(R.id.spellsLvl8)
        spellsLvl8?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(8)
            }else{
                spellsToShow.remove(8)
            }
        }
        val spellsLvl9 = findViewById<ToggleButton>(R.id.spellsLvl9)
        spellsLvl9?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(9)
            }else{
                spellsToShow.remove(9)
            }
        }




        button2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val spellsSorted = spellsToShow.toList().sorted()
                val spellsRequested = searchSpellsByLevels(spellsSorted)
                linLay.removeAllViewsInLayout()
                for (i in spellsRequested.keys) {
                    val textView = TextView(v.context)
                    val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    params.setMargins(50,20,50,0)
                    textView.layoutParams = params

                    val spellsOfMap = spellsRequested[i]!!
                    textView.text = "Spells of level " + i
                    textView.textSize = 25f
                    textView.setPadding(30,10,0,10)
                    textView.setBackgroundColor(Color.GREEN)
                    linLay.addView(textView)

                    for (spell in spellsOfMap){
                        val spellView = TextView(v.context)
                        val spellParams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        spellParams.setMargins(50,20,50,0)
                        spellView.layoutParams = spellParams
                        spellView.text = spell
                        spellView.textSize = 25f
                        spellView.setPadding(30,10,0,10)
                        spellView.setBackgroundColor(Color.MAGENTA)

                        spellView.setOnClickListener(object : View.OnClickListener{
                            override fun onClick(v: View) {
                                prueba(spellView)
                            }
                        })

                        linLay.addView(spellView)
                    }

                }
            }
        })


    }

    fun prueba(tx:TextView){
        val descLay = LinearLayout(this)
        descLay.setBackgroundColor(Color.CYAN)
        descLay.orientation = LinearLayout.VERTICAL

        val spellCode = searchSpell(tx.text.toString())

        val paramsPrueba : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsPrueba.setMargins(60,20,20,20)

        val textViewPrueba = TextView(this)
        textViewPrueba.layoutParams = paramsPrueba
        textViewPrueba.text = "Casting time:\n" + searchCastingTimeFromSpell(spellCode)
        textViewPrueba.textSize = 16f
        textViewPrueba.setPadding(30,10,10,10)
        textViewPrueba.setBackgroundColor(Color.LTGRAY)

        val paramsPrueba2 : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsPrueba2.setMargins(20,20,20,20)

        val textViewPrueba2 = TextView(this)
        textViewPrueba2.layoutParams = paramsPrueba2
        textViewPrueba2.text = "Range:\n60 feet"
        textViewPrueba2.textSize = 16f
        textViewPrueba2.setPadding(30,10,10,10)
        textViewPrueba2.setBackgroundColor(Color.LTGRAY)

        val paramsPrueba3 : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsPrueba3.setMargins(20,20,20,20)

        val textViewPrueba3 = TextView(this)
        textViewPrueba3.layoutParams = paramsPrueba3
        textViewPrueba3.text = "Components:\n" + searchComponentsFromSpell(spellCode)
        textViewPrueba3.textSize = 16f
        textViewPrueba3.setPadding(30,10,10,10)
        textViewPrueba3.setBackgroundColor(Color.LTGRAY)

        val paramsPrueba4 : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsPrueba4.setMargins(20,20,20,20)

        val textViewPrueba4 = TextView(this)
        textViewPrueba4.layoutParams = paramsPrueba4
        textViewPrueba4.text = "Duration:\ninstantaneous"
        textViewPrueba4.textSize = 16f
        textViewPrueba4.setPadding(30,10,10,10)
        textViewPrueba4.setBackgroundColor(Color.LTGRAY)


        val horizLay = LinearLayout(this)
        horizLay.orientation = LinearLayout.HORIZONTAL
        horizLay.addView(textViewPrueba)
        horizLay.addView(textViewPrueba2)
        horizLay.addView(textViewPrueba3)
        horizLay.addView(textViewPrueba4)


        val paramsDesc : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsDesc.setMargins(50,15,50,30)

        val textViewDescription = TextView(this)
        textViewDescription.layoutParams = paramsDesc
        textViewDescription.text = "You hurl a bubble of acid. Choose one creature within range, or choose two creatures within range that are within 5 feet of each other. A target must succeed on a Dexterity saving throw or take 1d6 acid damage.This spell's damage increases by 1d6 when you reach 5th Level (2d6), 11th level (3d6) and 17th level (4d6)."
        textViewDescription.textSize = 16f
        textViewDescription.setPadding(30,10,10,10)
        textViewDescription.setBackgroundColor(Color.WHITE)



        descLay.addView(horizLay)
        descLay.addView(textViewDescription)


        descLay.setTag("prueba" + tx.text.toString())
        val prueba = linLay.findViewWithTag<View>("prueba" + tx.text.toString())

        if(prueba == null){
            linLay.addView(descLay, linLay.indexOfChild(tx)+1)
        }else{
            linLay.removeViewAt(linLay.indexOfChild(prueba))
        }




    }



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



}
