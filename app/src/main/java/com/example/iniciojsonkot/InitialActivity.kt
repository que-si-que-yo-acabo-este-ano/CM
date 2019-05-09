package com.example.iniciojsonkot

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.initial_layout.*
import kotlinx.android.synthetic.main.modify_stats_layout.view.*

class InitialActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.initial_layout)
        loadDataFromGit()
        println("===================LOAD==================")
        Character.loadCharacters(applicationContext)

        for(i in 0..(Global.characters.size-1)){
            val character = Global.characters[i]
            val textView = TextView(this)
            val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,100)
            params.setMargins(50,20,50,0)
            textView.layoutParams = params
            textView.text = character.name
            textView.setBackgroundResource(R.drawable.top_green)
            textView.setBackgroundResource(R.drawable.top_green)
            textView.tag = character.name
            initialCharacters.addView(textView)
            //initialCharacters.findViewWithTag<TextView>(character.name)


            textView.setOnClickListener {
                Global.loadedCharacter = character
                Global.showAllSpells = false
                Global.tempSaves.addAll(Global.loadedCharacter.savesProficiencies)
                Global.tempProfs.addAll(Global.loadedCharacter.skillsProficiencies)
                val selectedCharacterIntent = Intent(this@InitialActivity, MainActivity::class.java)
                startActivity(selectedCharacterIntent)
            }
        }

        initialCreateCharacter.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val createCharIntent = Intent(this@InitialActivity, CreateCharacter::class.java)
                startActivity(createCharIntent)
            }
        })

        initialShowSpells.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                Global.showAllSpells = true
                val allSpellsIntent = Intent(this@InitialActivity, AllSpellsActivity::class.java)
                startActivity(allSpellsIntent)
            }
        })

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
    fun loadDataFromGit(){
        Global.spells = MainActivity.DownloadGit().execute("https://raw.githubusercontent.com/TheGiddyLimit/TheGiddyLimit.github.io/master/data/spells/spells-phb.json").get()
    }
}