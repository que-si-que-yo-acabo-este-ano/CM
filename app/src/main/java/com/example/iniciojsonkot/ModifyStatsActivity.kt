package com.example.iniciojsonkot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.modify_stats_layout.*
import kotlin.math.floor
import android.view.View
import kotlinx.android.synthetic.main.stats_saves_resistances.*


class ModifyStatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modify_stats_layout)
        Global.personaje.createJson(this.applicationContext)
        Global.personaje = Character.createCharacterFromJson("Orpheus",this.applicationContext)
        println("***************************** -- ${Global.characters.size}")
        println("${Global.personaje.name}.json")
        println(Global.personaje.classes)
        //Global.characters[0].deleteCharacter(this.applicationContext)



        editStr.setText(Global.loadedCharacter.strength.toString())
        editDex.setText(Global.loadedCharacter.dexterity.toString())
        editCon.setText(Global.loadedCharacter.constitution.toString())
        editInt.setText(Global.loadedCharacter.intelligence.toString())
        editWis.setText(Global.loadedCharacter.wisdom.toString())
        editCha.setText(Global.loadedCharacter.charisma.toString())



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
                        textView8.setText(calcMod(stat))
                    }else{
                        textView8.setText("+" + calcMod(stat))
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
                        textView9.setText(calcMod(stat))
                    }else{
                        textView9.setText("+" + calcMod(stat))
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
                        textView15.setText(calcMod(stat))
                    }else{
                        textView15.setText("+" + calcMod(stat))
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
                        textView16.setText(calcMod(stat))
                    }else{
                        textView16.setText("+" + calcMod(stat))
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
                        textView17.setText(calcMod(stat))
                    }else{
                        textView17.setText("+" + calcMod(stat))
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
                        textView18.setText(calcMod(stat))
                    }else{
                        textView18.setText("+" + calcMod(stat))
                    }
                }
            })


        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                Global.loadedCharacter.strength = Integer.parseInt(editStr.text.toString())
                Global.loadedCharacter.dexterity = Integer.parseInt(editDex.text.toString())
                Global.loadedCharacter.constitution = Integer.parseInt(editCon.text.toString())
                Global.loadedCharacter.intelligence = Integer.parseInt(editInt.text.toString())
                Global.loadedCharacter.wisdom = Integer.parseInt(editWis.text.toString())
                Global.loadedCharacter.charisma = Integer.parseInt(editCha.text.toString())

                finish()
            }
        })



    }





    fun calcMod(x: Int): String{
        return floor((x.toDouble()-10)/2).toString()
    }


}
