package com.example.iniciojsonkot

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.initial_layout.*
import kotlinx.android.synthetic.main.select_spells_layout.*

class InitialActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.initial_layout)

        for(i in 0..(Global.characters.size-1)){
            val textView = TextView(this)
            val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,100)
            textView.layoutParams = params
            textView.text = Global.characters[i].name
            textView.setBackgroundResource(R.drawable.back)
            initialCharacters.addView(textView)
        }

        /*val textView = TextView(this)
        val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(50,20,50,0)
        textView.layoutParams = params
        textView.height = 40

        textView.text = "Character"
        textView.textSize = 25f
        textView.setPadding(30,10,0,10)
        textView.setBackgroundColor(Color.GREEN)
        initialScrollView.addView(textView)*/
    }
}