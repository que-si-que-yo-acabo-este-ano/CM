package com.example.iniciojsonkot

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.create_character_1.*
import kotlinx.android.synthetic.main.create_character_1.editCha
import kotlinx.android.synthetic.main.create_character_1.editCon
import kotlinx.android.synthetic.main.create_character_1.editDex
import kotlinx.android.synthetic.main.create_character_1.editInt
import kotlinx.android.synthetic.main.create_character_1.editStr
import kotlinx.android.synthetic.main.create_character_1.editWis
import kotlinx.android.synthetic.main.create_character_1.view.*
import kotlinx.android.synthetic.main.modify_stats_layout.*
import kotlin.math.floor

class CreateCharacter : AppCompatActivity(), AdapterView.OnItemSelectedListener{

    val listOfClasses = arrayOf("","Bard","Cleric","Druid","Paladin","Ranger","Sorcerer","Warlock","Wizard")
    val listOfRaces = arrayOf("","Dwarf","Elf","Halfling","Human","Dragonborn","Gnome","Half-Elf","Half-Orc","Tiefling")

    var spinnerClass: Spinner? = null
    var spinnerRace: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_character_1)

        spinnerClass = this.classSpinner
        spinnerClass!!.setOnItemSelectedListener(this)
        val arrayAdapterClasses = ArrayAdapter(this,android.R.layout.simple_spinner_item,listOfClasses)
        arrayAdapterClasses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerClass!!.setAdapter(arrayAdapterClasses)

        spinnerRace = this.raceSpinner
        spinnerRace!!.setOnItemSelectedListener(this)
        val arrayAdapterRaces = ArrayAdapter(this,android.R.layout.simple_spinner_item,listOfRaces)
        arrayAdapterRaces.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRace!!.setAdapter(arrayAdapterRaces)


        addClassButton.setOnClickListener (object : View.OnClickListener {
            override fun onClick(v: View) {
                val classView = LinearLayout(v.context)
                classView.orientation = LinearLayout.HORIZONTAL

                val className = TextView(v.context)
                className.setText(classSpinner.selectedItem.toString())

                val classLevel = EditText(v.context)
                classLevel.maxEms = 2
                classLevel.inputType = InputType.TYPE_CLASS_NUMBER

                classView.addView(className)
                classView.addView(classLevel)
                classView.setTag(className.text)

                val alreadyExists = classesList.findViewWithTag<View>(className.text)

                if(alreadyExists == null){
                    classesList.addView(classView)
                }


            }
        })

        editStr.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }
                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    var stat: Int
                    if (s.any()){
                        stat = Integer . parseInt (s.toString())
                    }else{
                        stat = 0
                    }
                    if (stat < 10) {
                        strMod.setText(calcMod(stat))
                    }else{
                        strMod.setText("+" + calcMod(stat))
                    }
                }
            })

        editDex.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }
                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    var stat: Int
                    if (s.any()){
                        stat = Integer . parseInt (s.toString())
                    }else{
                        stat = 0
                    }
                    if (stat < 10) {
                        dexMod.setText(calcMod(stat))
                    }else{
                        dexMod.setText("+" + calcMod(stat))
                    }
                }
            })

        editCon.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }
                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    var stat: Int
                    if (s.any()){
                        stat = Integer . parseInt (s.toString())
                    }else{
                        stat = 0
                    }
                    if (stat < 10) {
                        conMod.setText(calcMod(stat))
                    }else{
                        conMod.setText("+" + calcMod(stat))
                    }
                }
            })

        editInt.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }
                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    var stat: Int
                    if (s.any()){
                        stat = Integer . parseInt (s.toString())
                    }else{
                        stat = 0
                    }
                    if (stat < 10) {
                        intMod.setText(calcMod(stat))
                    }else{
                        intMod.setText("+" + calcMod(stat))
                    }
                }
            })

        editWis.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }
                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    var stat: Int
                    if (s.any()){
                        stat = Integer . parseInt (s.toString())
                    }else{
                        stat = 0
                    }
                    if (stat < 10) {
                        wisMod.setText(calcMod(stat))
                    }else{
                        wisMod.setText("+" + calcMod(stat))
                    }
                }
            })

        editCha.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }
                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    var stat: Int
                    if (s.any()){
                        stat = Integer . parseInt (s.toString())
                    }else{
                        stat = 0
                    }
                    if (stat < 10) {
                        chaMod.setText(calcMod(stat))
                    }else{
                        chaMod.setText("+" + calcMod(stat))
                    }
                }
            })

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }


    fun calcMod(x: Int): String{
        return floor((x.toDouble()-10)/2).toString()
    }
}