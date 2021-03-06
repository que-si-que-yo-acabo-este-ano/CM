package com.example.iniciojsonkot

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.View
import kotlinx.android.synthetic.main.select_spells_layout.*
import android.widget.*
import com.example.iniciojsonkot.Searchers.Companion.searchCastingTimeFromSpell
import com.example.iniciojsonkot.Searchers.Companion.searchComponentsFromSpell
import com.example.iniciojsonkot.Searchers.Companion.searchDescriptionFromSpell
import com.example.iniciojsonkot.Searchers.Companion.searchDurationFromSpell
import com.example.iniciojsonkot.Searchers.Companion.searchRangeFromSpell
import com.example.iniciojsonkot.Searchers.Companion.searchSpell
import com.example.iniciojsonkot.Searchers.Companion.searchSpellsByLevelsAndClasses
import kotlinx.android.synthetic.main.spells_fragment_layout.view.*
import kotlin.streams.toList


class SelectSpellsActivity : AppCompatActivity() {
    //var global = Global()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_spells_layout)
        val spellsToShow : MutableSet<Int> = mutableSetOf()
        var spellsSelected : MutableList<MutableSet<String>> = mutableListOf()
        //CharacterTemp.spellsKnown
        for (i in 0..9){
            spellsSelected.add(mutableSetOf())
            spellsSelected[i].addAll(Global.loadedCharacter.spellsKnown[i])
        }

        val cantrips = findViewById<ToggleButton>(R.id.cantrips)


        cantrips?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                cantrips.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
                spellsToShow.add(0)
            }else{
                spellsToShow.remove(0)
                cantrips.setBackgroundResource(R.drawable.drawable_togglebutton)

            }
        }

        val spellsLvl1 = findViewById<ToggleButton>(R.id.spellsLvl1)

        spellsLvl1?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsLvl1.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
                spellsToShow.add(1)
            }else{
                spellsToShow.remove(1)
                spellsLvl1.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }
        val spellsLvl2 = findViewById<ToggleButton>(R.id.spellsLvl2)

        spellsLvl2?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsLvl2.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
                spellsToShow.add(2)
            }else{
                spellsToShow.remove(2)
                spellsLvl2.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }
        val spellsLvl3 = findViewById<ToggleButton>(R.id.spellsLvl3)

        spellsLvl3?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsLvl3.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
                spellsToShow.add(3)
            }else{
                spellsToShow.remove(3)
                spellsLvl3.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }
        val spellsLvl4 = findViewById<ToggleButton>(R.id.spellsLvl4)

        spellsLvl4?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsLvl4.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
                spellsToShow.add(4)
            }else{
                spellsToShow.remove(4)
                spellsLvl4.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }
        val spellsLvl5 = findViewById<ToggleButton>(R.id.spellsLvl5)

        spellsLvl5?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsLvl5.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
                spellsToShow.add(5)
            }else{
                spellsToShow.remove(5)
                spellsLvl5.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }
        val spellsLvl6 = findViewById<ToggleButton>(R.id.spellsLvl6)

        spellsLvl6?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsLvl6.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
                spellsToShow.add(6)
            }else{
                spellsToShow.remove(6)
                spellsLvl6.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }
        val spellsLvl7 = findViewById<ToggleButton>(R.id.spellsLvl7)

        spellsLvl7?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsLvl7.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
                spellsToShow.add(7)
            }else{
                spellsLvl7.setBackgroundResource(R.drawable.drawable_togglebutton)
                spellsToShow.remove(7)

            }
        }
        val spellsLvl8 = findViewById<ToggleButton>(R.id.spellsLvl8)

        spellsLvl8?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsLvl8.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
                spellsToShow.add(8)
            }else{
                spellsToShow.remove(8)
                spellsLvl8.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }
        val spellsLvl9 = findViewById<ToggleButton>(R.id.spellsLvl9)

        spellsLvl9?.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsLvl9.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
                spellsToShow.add(9)
            }else{
                spellsToShow.remove(9)
                spellsLvl9.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }

        buttonSelect.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                for (i in 0..9){
                    Global.loadedCharacter.spellsKnown[i].clear()
                    Global.loadedCharacter.spellsKnown[i].addAll(spellsSelected[i])
                }
                /*println("---------SelectSpellsActivity-------------")
                println(Global.loadedCharacter.spellsKnown)*/
                Global.loadedCharacter.createJson(applicationContext)
                finish()
            }
        })


        button2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val spellsSorted = spellsToShow.toList().sorted()
                val spellsRequested = searchSpellsByLevelsAndClasses(spellsSorted,Global.loadedCharacter.classes.keys)
                linLay.removeAllViewsInLayout()
                for (i in spellsRequested.keys) {
                    val textView = TextView(v.context)
                    val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                    params.setMargins(50,20,50,0)
                    textView.layoutParams = params

                    val spellsOfMap = spellsRequested[i]!!
                    if(i==0){
                        textView.text = "Cantrips"
                    }else{
                        textView.text = "Spells of level " + i
                    }
                    textView.textSize = 25f
                    textView.setPadding(30,10,0,10)
                    textView.setBackgroundColor(Color.parseColor("#86AC41"))
                    linLay.addView(textView)

                    for (spell in spellsOfMap){
                        val horizLay = LinearLayout(this@SelectSpellsActivity)
                        val horizLayParams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                        horizLayParams.setMargins(50,10,50,0)
                        horizLay.layoutParams = horizLayParams
                        horizLay.orientation = LinearLayout.HORIZONTAL
                        horizLay.setBackgroundColor(Color.parseColor("#c99174"))
                        horizLay.weightSum = 10f

                        val spellView = TextView(v.context)
                        val spellParams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 2f)
                        spellView.layoutParams = spellParams
                        spellView.text = spell
                        spellView.textSize = 25f
                        spellView.setPadding(30,10,0,10)
                        spellView.setBackgroundColor(Color.parseColor("#A2C523"))

                        //spellView.width = (330 * applicationContext.resources.displayMetrics.density).toInt()

                        spellView.setOnClickListener(object : View.OnClickListener{
                            override fun onClick(v: View) {
                                val descLayout = prueba(spellView)

                                descLayout.setTag("prueba" + spellView.text.toString())
                                val prueba = linLay.findViewWithTag<View>("prueba" + spellView.text.toString())

                                if(prueba == null){
                                    linLay.addView(descLayout, linLay.indexOfChild(horizLay)+1)
                                }else{
                                    linLay.removeViewAt(linLay.indexOfChild(prueba))
                                }
                            }
                        })

                        val spellSelectToggle = Switch(this@SelectSpellsActivity)
                        spellSelectToggle.setBackgroundColor(Color.parseColor("#86AC41"))
                        val spellSelectToggleParams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 8f)
                        spellSelectToggle.layoutParams = spellSelectToggleParams

                        if(spellsSelected[i].contains(spell.toString())) {
                            spellSelectToggle.isChecked = true
                        }

                        spellSelectToggle?.setOnClickListener {
                            var swichtChecked = spellSelectToggle.isChecked
                            if (swichtChecked) {
                                spellsSelected[i].add(spell.toString())
                            } else {
                                spellsSelected[i].remove(spell.toString())
                            }
                        }
                        horizLay.addView(spellView)
                        horizLay.addView(spellSelectToggle)

                        linLay.addView(horizLay)
                    }

                }
            }
        })


    }

    fun prueba(tx:TextView):LinearLayout{
        val descLay = LinearLayout(this)
        descLay.setBackgroundColor(Color.parseColor("#A2C540"))
        descLay.orientation = LinearLayout.VERTICAL

        val spellCode = searchSpell(tx.text.toString())

        val paramsPrueba : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsPrueba.setMargins(50,20,20,0)

        val castingTimeTV = TextView(this)
        castingTimeTV.layoutParams = paramsPrueba
        castingTimeTV.text = "Casting time: " + searchCastingTimeFromSpell(spellCode)
        castingTimeTV.textSize = 16f
        castingTimeTV.setPadding(30,10,30,10)
        castingTimeTV.setBackgroundColor(Color.parseColor("#c9e26c"))


        val rangeTextView = TextView(this)
        rangeTextView.layoutParams = paramsPrueba
        rangeTextView.text = "Range: " + searchRangeFromSpell(spellCode)
        rangeTextView.textSize = 16f
        rangeTextView.setPadding(30,10,30,10)
        rangeTextView.setBackgroundColor(Color.parseColor("#c9e26c"))


        val componentsTV = TextView(this)
        componentsTV.layoutParams = paramsPrueba
        componentsTV.text = "Components: " + searchComponentsFromSpell(spellCode)
        componentsTV.textSize = 16f
        componentsTV.setPadding(30,10,30,10)
        componentsTV.setBackgroundColor(Color.parseColor("#c9e26c"))


        val durationTV = TextView(this)
        durationTV.layoutParams = paramsPrueba
        durationTV.text = "Duration: " + searchDurationFromSpell(spellCode)
        durationTV.textSize = 16f
        durationTV.setPadding(30,10,30,10)
        durationTV.setBackgroundColor(Color.parseColor("#c9e26c"))


        val horizLay = LinearLayout(this)
        horizLay.orientation = LinearLayout.HORIZONTAL
        horizLay.addView(castingTimeTV)
        horizLay.addView(componentsTV)

        val horizLay2 = LinearLayout(this)
        horizLay2.orientation = LinearLayout.HORIZONTAL
        horizLay2.addView(rangeTextView)
        horizLay2.addView(durationTV)


        val paramsDesc : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsDesc.setMargins(50,20,50,30)

        val descriptionTV = TextView(this)
        descriptionTV.layoutParams = paramsDesc
        descriptionTV.text = searchDescriptionFromSpell(spellCode).trim()
        descriptionTV.textSize = 16f
        descriptionTV.setPadding(30,10,10,10)
        descriptionTV.setBackgroundColor(Color.parseColor("#c9e26c"))


        descLay.addView(horizLay)
        descLay.addView(horizLay2)
        descLay.addView(descriptionTV)

        return descLay
    }




}
