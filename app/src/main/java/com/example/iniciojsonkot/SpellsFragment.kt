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
import com.example.iniciojsonkot.Searchers.Companion.searchCastingTimeFromSpell
import com.example.iniciojsonkot.Searchers.Companion.searchComponentsFromSpell
import com.example.iniciojsonkot.Searchers.Companion.searchDescriptionFromSpell
import com.example.iniciojsonkot.Searchers.Companion.searchSpell
import kotlinx.android.synthetic.main.select_spells_layout.*
import kotlinx.android.synthetic.main.select_spells_layout.cantrips
import kotlinx.android.synthetic.main.select_spells_layout.linLay
import kotlinx.android.synthetic.main.spells_fragment_layout.*
import kotlinx.android.synthetic.main.spells_fragment_layout.view.*

//import kotlinx.android.synthetic.main.fragment_your_fragment_name.view.*

class SpellsFragment : Fragment() {
    private lateinit var viewOfLayout: View
    private val spellsToShow : MutableSet<Int> = mutableSetOf()
    var global = Global()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewOfLayout = inflater!!.inflate(R.layout.spells_fragment_layout, container, false)


        viewOfLayout.cantrips.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(0)
                viewOfLayout.cantrips.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
            }else{
                spellsToShow.remove(0)
                viewOfLayout.cantrips.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }

        viewOfLayout.spellsLvl1.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(1)
                viewOfLayout.spellsLvl1.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
            }else{
                spellsToShow.remove(1)
                viewOfLayout.spellsLvl1.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }

        viewOfLayout.spellsLvl2.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(2)
                viewOfLayout.spellsLvl2.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
            }else{
                spellsToShow.remove(2)
                viewOfLayout.spellsLvl2.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }

        viewOfLayout.spellsLvl3.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(3)
                viewOfLayout.spellsLvl3.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
            }else{
                spellsToShow.remove(3)
                viewOfLayout.spellsLvl3.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }

        viewOfLayout.spellsLvl4.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(4)
                viewOfLayout.spellsLvl4.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
            }else{
                spellsToShow.remove(4)
                viewOfLayout.spellsLvl4.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }

        viewOfLayout.spellsLvl5.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(5)
                viewOfLayout.spellsLvl5.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
            }else{
                spellsToShow.remove(5)
                viewOfLayout.spellsLvl5.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }

        viewOfLayout.spellsLvl6.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(6)
                viewOfLayout.spellsLvl6.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
            }else{
                spellsToShow.remove(6)
                viewOfLayout.spellsLvl6.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }

        viewOfLayout.spellsLvl7.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(7)
                viewOfLayout.spellsLvl7.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
            }else{
                spellsToShow.remove(7)
                viewOfLayout.spellsLvl7.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }

        viewOfLayout.spellsLvl8.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(8)
                viewOfLayout.spellsLvl8.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
            }else{
                spellsToShow.remove(8)
                viewOfLayout.spellsLvl8.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }

        viewOfLayout.spellsLvl9.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                spellsToShow.add(9)
                viewOfLayout.spellsLvl9.setBackgroundResource(R.drawable.drawable_togglebutton_clicked)
            }else{
                spellsToShow.remove(9)
                viewOfLayout.spellsLvl9.setBackgroundResource(R.drawable.drawable_togglebutton)
            }
        }

        viewOfLayout.buttonSelect.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val changeSpellsIntent = Intent(context, SelectSpellsActivity::class.java)
                startActivity(changeSpellsIntent)
            }
        })


        viewOfLayout.button2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

                val spellsSorted = spellsToShow.toList().sorted()
                linLay.removeAllViewsInLayout()
                for (i in spellsSorted) {
                    val textView = TextView(v.context)
                    val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    params.setMargins(50,20,50,0)
                    textView.layoutParams = params

                    val spellsOfMap: List<String> = Global.loadedCharacter.spellsKnown[i]!!.toList().sorted()
                    if(i==0){
                        textView.text = "Cantrips"
                    }else{
                        textView.text = "Spells of level " + i
                    }
                    textView.textSize = 25f
                    textView.setPadding(30,10,0,10)
                    textView.setBackgroundColor(Color.parseColor("#86AC41"))
                    linLay.addView(textView)
                    println(Global.loadedCharacter.spellsKnown)

                    for (spell in spellsOfMap){
                        if(spell.isNotBlank()){
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
            }
        })

        return viewOfLayout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun prueba(tx: TextView){
        val descLay = LinearLayout(context)
        descLay.setBackgroundColor(Color.parseColor("#A2C540")) //right_green
        descLay.orientation = LinearLayout.VERTICAL

        val spellCode = searchSpell(tx.text.toString())

        val paramsPrueba : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsPrueba.setMargins(50,20,20,0)

        val castingTimeTV = TextView(context)
        castingTimeTV.layoutParams = paramsPrueba
        castingTimeTV.text = "Casting time: " + searchCastingTimeFromSpell(spellCode)
        castingTimeTV.textSize = 16f
        castingTimeTV.setPadding(30,10,30,10)
        castingTimeTV.setBackgroundColor(Color.parseColor("#c9e26c"))


        val rangeTextView = TextView(context)
        rangeTextView.layoutParams = paramsPrueba
        rangeTextView.text = "Range: " + Searchers.searchRangeFromSpell(spellCode)
        rangeTextView.textSize = 16f
        rangeTextView.setPadding(30,10,30,10)
        rangeTextView.setBackgroundColor(Color.parseColor("#c9e26c"))


        val componentsTV = TextView(context)
        componentsTV.layoutParams = paramsPrueba
        componentsTV.text = "Components: " + searchComponentsFromSpell(spellCode)
        componentsTV.textSize = 16f
        componentsTV.setPadding(30,10,30,10)
        componentsTV.setBackgroundColor(Color.parseColor("#c9e26c"))


        val durationTV = TextView(context)
        durationTV.layoutParams = paramsPrueba
        durationTV.text = "Duration: " + Searchers.searchDurationFromSpell(spellCode)
        durationTV.textSize = 16f
        durationTV.setPadding(30,10,30,10)
        durationTV.setBackgroundColor(Color.parseColor("#c9e26c"))


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