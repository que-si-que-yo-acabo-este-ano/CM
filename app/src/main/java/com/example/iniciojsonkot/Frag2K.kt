package com.example.iniciojsonkot

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ToggleButton
import com.beust.klaxon.JsonObject
import com.example.iniciojsonkot.R
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.frag2_layout.view.*

//import kotlinx.android.synthetic.main.fragment_your_fragment_name.view.*

class Frag2K : Fragment() {
    private lateinit var viewOfLayout: View
    private val spellsToShow : MutableSet<Int> = mutableSetOf()
    var global = Global()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewOfLayout = inflater!!.inflate(R.layout.frag2_layout, container, false)
        viewOfLayout.cantrips.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(0)
            }else{
                spellsToShow.remove(0)
            }
        }

        viewOfLayout.spellsLvl1.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(1)
            }else{
                spellsToShow.remove(1)
            }
        }

        viewOfLayout.spellsLvl2.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(2)
            }else{
                spellsToShow.remove(2)
            }
        }

        viewOfLayout.spellsLvl3.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(3)
            }else{
                spellsToShow.remove(3)
            }
        }

        viewOfLayout.spellsLvl4.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(4)
            }else{
                spellsToShow.remove(4)
            }
        }

        viewOfLayout.spellsLvl5.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(5)
            }else{
                spellsToShow.remove(5)
            }
        }

        viewOfLayout.spellsLvl6.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(6)
            }else{
                spellsToShow.remove(6)
            }
        }

        viewOfLayout.spellsLvl7.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(7)
            }else{
                spellsToShow.remove(7)
            }
        }

        viewOfLayout.spellsLvl8.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(8)
            }else{
                spellsToShow.remove(8)
            }
        }

        viewOfLayout.spellsLvl9.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(9)
            }else{
                spellsToShow.remove(9)
            }
        }

        viewOfLayout.buttonSelect.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val changeSpellsIntent = Intent(context, Main2Activity::class.java)
                startActivity(changeSpellsIntent)
            }
        })


        viewOfLayout.button2.setOnClickListener(object : View.OnClickListener {
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

        //return inflater.inflate(R.layout.frag2_layout, container, false)
        return viewOfLayout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    fun prueba(tx: TextView){
        val descLay = LinearLayout(context)
        descLay.setBackgroundColor(Color.CYAN)
        descLay.orientation = LinearLayout.VERTICAL

        val spellCode = searchSpell(tx.text.toString())

        val paramsPrueba : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsPrueba.setMargins(50,20,20,0)

        val castingTimeTV = TextView(context)
        castingTimeTV.layoutParams = paramsPrueba
        castingTimeTV.text = "Casting time: " + searchCastingTimeFromSpell(spellCode)
        castingTimeTV.textSize = 16f
        castingTimeTV.setPadding(30,10,30,10)
        castingTimeTV.setBackgroundColor(Color.LTGRAY)


        val rangeTextView = TextView(context)
        rangeTextView.layoutParams = paramsPrueba
        rangeTextView.text = "Range: 60 feet" //TODO
        rangeTextView.textSize = 16f
        rangeTextView.setPadding(30,10,30,10)
        rangeTextView.setBackgroundColor(Color.LTGRAY)


        val componentsTV = TextView(context)
        componentsTV.layoutParams = paramsPrueba
        componentsTV.text = "Components: " + searchComponentsFromSpell(spellCode)
        componentsTV.textSize = 16f
        componentsTV.setPadding(30,10,30,10)
        componentsTV.setBackgroundColor(Color.LTGRAY)


        val durationTV = TextView(context)
        durationTV.layoutParams = paramsPrueba
        durationTV.text = "Duration: instantaneous"//TODO
        durationTV.textSize = 16f
        durationTV.setPadding(30,10,30,10)
        durationTV.setBackgroundColor(Color.LTGRAY)


        val horizLay = LinearLayout(context)
        horizLay.orientation = LinearLayout.HORIZONTAL
        horizLay.addView(castingTimeTV)
        horizLay.addView(componentsTV)

        val horizLay2 = LinearLayout(context)
        horizLay2.orientation = LinearLayout.HORIZONTAL
        horizLay2.addView(rangeTextView)
        horizLay2.addView(durationTV)


        val paramsDesc : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsDesc.setMargins(50,20,50,30)

        val descriptionTV = TextView(context)
        descriptionTV.layoutParams = paramsDesc
        descriptionTV.text = searchDescriptionFromSpell(spellCode).trim()
        descriptionTV.textSize = 16f
        descriptionTV.setPadding(30,10,10,10)
        descriptionTV.setBackgroundColor(Color.WHITE)


        descLay.addView(horizLay)
        descLay.addView(horizLay2)
        descLay.addView(descriptionTV)


        descLay.setTag("prueba" + tx.text.toString())
        val prueba = linLay.findViewWithTag<View>("prueba" + tx.text.toString())

        if(prueba == null){
            linLay.addView(descLay, linLay.indexOfChild(tx)+1)
        }else{
            linLay.removeViewAt(linLay.indexOfChild(prueba))
        }


    }



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

    fun searchSpellsByLevel(level: Int): List<String?>{
        val res = Global.spells.array<JsonObject>("spell")!!.filter {
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