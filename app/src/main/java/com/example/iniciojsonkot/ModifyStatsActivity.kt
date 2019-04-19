package com.example.iniciojsonkot

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.modify_stats_layout.*
import kotlin.math.floor
import android.content.Intent
import android.os.AsyncTask
import android.view.View
import android.widget.TextView
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.URL
import com.example.iniciojsonkot.Global.Companion.basicitems
import com.example.iniciojsonkot.Global.Companion.items
import com.example.iniciojsonkot.Global.Companion.spells
import kotlinx.android.synthetic.main.stats_saves_resistances.*
import kotlinx.android.synthetic.main.stats_saves_resistances.view.*


class ModifyStatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modify_stats_layout)


        editStr.setText(Character.strength.toString())
        editDex.setText(Character.dexterity.toString())
        editCon.setText(Character.constitution.toString())
        editInt.setText(Character.intelligence.toString())
        editWis.setText(Character.wisdom.toString())
        editCha.setText(Character.charisma.toString())



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
                Character.strength = Integer.parseInt(editStr.text.toString())
                Character.dexterity = Integer.parseInt(editDex.text.toString())
                Character.constitution = Integer.parseInt(editCon.text.toString())
                Character.intelligence = Integer.parseInt(editInt.text.toString())
                Character.wisdom = Integer.parseInt(editWis.text.toString())
                Character.charisma = Integer.parseInt(editCha.text.toString())

                finish()
            }
        })



    }





    fun calcMod(x: Int): String{
        return floor((x.toDouble()-10)/2).toString()
    }


}
