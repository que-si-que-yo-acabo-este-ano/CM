package com.example.iniciojsonkot

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.initial_layout.*
import kotlinx.android.synthetic.main.modify_stats_layout.view.*
import kotlinx.android.synthetic.main.proficiency_selector.*

class InitialActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.initial_layout)
        loadDataFromGit()
        println("===================LOAD==================")
        Character.loadCharacters(applicationContext)
        initialCharacters.removeAllViewsInLayout()

        for(i in 0..(Global.characters.size-1)){
            val character = Global.characters[i]
            val textView = TextView(this)
            val horizLayParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,100)
            horizLayParams.setMargins(50,20,50,0)
            var horizLay = LinearLayout(this)
            horizLay.orientation = LinearLayout.HORIZONTAL
            horizLay.layoutParams = horizLayParams
            horizLay.tag = character.name
            horizLay.weightSum = 10f

            val textParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,1f)
            textView.layoutParams = textParams
            textView.text = character.name
            textView.gravity = Gravity.CENTER_VERTICAL
            textView.setBackgroundResource(R.drawable.top_green)

            val deleteChar = TextView(this@InitialActivity)
            val delParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 9f)
            deleteChar.layoutParams = delParams
            deleteChar.text = "X"
            deleteChar.gravity = Gravity.CENTER
            deleteChar.setBackgroundColor(Color.RED)
            deleteChar.setOnClickListener {
                showDialog(character, initialCharacters)
            }

            horizLay.addView(textView)
            horizLay.addView(deleteChar)

            initialCharacters.addView(horizLay)

            textView.setOnClickListener {
                Global.loadedCharacter = character
                Global.showAllSpells = false
                println(Global.loadedCharacter.spellsKnown)
                Global.tempSaves.addAll(Global.loadedCharacter.savesProficiencies)
                Global.tempProfs.addAll(Global.loadedCharacter.skillsProficiencies)
                val selectedCharacterIntent = Intent(this@InitialActivity, MainActivity::class.java)
                startActivity(selectedCharacterIntent)
                finish()
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
        if(Global.isNotSpellsLoaded){
            Global.spells = MainActivity.DownloadGit().execute("https://raw.githubusercontent.com/TheGiddyLimit/TheGiddyLimit.github.io/master/data/spells/spells-phb.json").get()
            Global.isNotSpellsLoaded = false
        }
    }

    private fun showDialog(charToDelete : Character, charsLayout : LinearLayout){
        // Late initialize an alert dialog object
        lateinit var dialog:AlertDialog

        // Initialize a new instance of alert dialog builder object
        val builder = AlertDialog.Builder(this)

        // Set a title for alert dialog

        builder.setTitle("Delete")

        // Set a message for alert dialog
        builder.setMessage("Do you really want to delete " + charToDelete.name + "?")


        // On click listener for dialog buttons
        val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE -> {
                    charsLayout.removeView(charsLayout.findViewWithTag(charToDelete.name))
                    charToDelete.deleteCharacter(applicationContext)

                }
                DialogInterface.BUTTON_NEGATIVE -> dialog.dismiss()
            }
        }
        // Set the alert dialog positive/yes button
        builder.setPositiveButton("YES",dialogClickListener)

        // Set the alert dialog negative/no button
        builder.setNegativeButton("NO",dialogClickListener)


        // Initialize the AlertDialog using builder object
        dialog = builder.create()

        // Finally, display the alert dialog
        dialog.show()
    }
}