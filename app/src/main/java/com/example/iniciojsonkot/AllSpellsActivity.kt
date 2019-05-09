package com.example.iniciojsonkot

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.modify_stats_layout.*
import kotlinx.android.synthetic.main.select_spells_layout.*
import kotlinx.android.synthetic.main.spells_fragment_layout.view.*

class AllSpellsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spells_fragment_layout)
        val spellsToShow: MutableSet<Int> = mutableSetOf()
        //CharacterTemp.spellsKnown

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
        val buttonSelect = findViewById<Button>(R.id.buttonSelect)
        buttonSelect.text = "Back"
        buttonSelect.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                finish()
            }
        })

        button2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

                val spellsSorted = spellsToShow.toList().sorted()
                linLay.removeAllViewsInLayout()
                for (i in spellsSorted) {
                    val textView = TextView(v.context)
                    val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    params.setMargins(50,20,50,0)
                    textView.layoutParams = params

                    val spellsOfMap: List<String> = Searchers.searchSpellsByLevel(i).sorted()
                    textView.text = "Spells of level " + i
                    textView.textSize = 25f
                    textView.setPadding(30,10,0,10)
                    textView.setBackgroundColor(Color.parseColor("#86AC41"))
                    linLay.addView(textView)

                    for (spell in spellsOfMap){
                        val spellView = TextView(v.context)
                        val spellParams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        spellParams.setMargins(50,20,50,0)
                        spellView.layoutParams = spellParams
                        spellView.text = spell
                        spellView.textSize = 25f
                        spellView.setPadding(30,10,0,10)
                        spellView.setBackgroundColor(Color.parseColor("#A2C523"))

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

    fun prueba(tx: TextView){
        val descLay = LinearLayout(this@AllSpellsActivity)
        descLay.setBackgroundColor(Color.parseColor("#A2C540")) //right_green
        descLay.orientation = LinearLayout.VERTICAL

        val spellCode = Searchers.searchSpell(tx.text.toString())

        val paramsPrueba : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsPrueba.setMargins(50,20,20,0)

        val castingTimeTV = TextView(this@AllSpellsActivity)
        castingTimeTV.layoutParams = paramsPrueba
        castingTimeTV.text = "Casting time: " + Searchers.searchCastingTimeFromSpell(spellCode)
        castingTimeTV.textSize = 16f
        castingTimeTV.setPadding(30,10,30,10)
        castingTimeTV.setBackgroundColor(Color.parseColor("#c9e26c"))


        val rangeTextView = TextView(this@AllSpellsActivity)
        rangeTextView.layoutParams = paramsPrueba
        rangeTextView.text = "Range: " + Searchers.searchRangeFromSpell(spellCode)
        rangeTextView.textSize = 16f
        rangeTextView.setPadding(30,10,30,10)
        rangeTextView.setBackgroundColor(Color.parseColor("#c9e26c"))


        val componentsTV = TextView(this@AllSpellsActivity)
        componentsTV.layoutParams = paramsPrueba
        componentsTV.text = "Components: " + Searchers.searchComponentsFromSpell(spellCode)
        componentsTV.textSize = 16f
        componentsTV.setPadding(30,10,30,10)
        componentsTV.setBackgroundColor(Color.parseColor("#c9e26c"))


        val durationTV = TextView(this@AllSpellsActivity)
        durationTV.layoutParams = paramsPrueba
        durationTV.text = "Duration: " + Searchers.searchDurationFromSpell(spellCode)
        durationTV.textSize = 16f
        durationTV.setPadding(30,10,30,10)
        durationTV.setBackgroundColor(Color.parseColor("#c9e26c"))


        val horizLay = LinearLayout(this@AllSpellsActivity)
        horizLay.orientation = LinearLayout.HORIZONTAL
        horizLay.addView(castingTimeTV)
        horizLay.addView(componentsTV)

        val horizLay2 = LinearLayout(this@AllSpellsActivity)
        horizLay2.orientation = LinearLayout.HORIZONTAL
        horizLay2.addView(rangeTextView)
        horizLay2.addView(durationTV)


        val paramsDesc : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsDesc.setMargins(50,20,50,30)

        val descriptionTV = TextView(this@AllSpellsActivity)
        descriptionTV.layoutParams = paramsDesc
        descriptionTV.text = Searchers.searchDescriptionFromSpell(spellCode).trim()
        descriptionTV.textSize = 16f
        descriptionTV.setPadding(30,10,10,10)
        descriptionTV.setBackgroundColor(Color.parseColor("#c9e26c"))


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
}