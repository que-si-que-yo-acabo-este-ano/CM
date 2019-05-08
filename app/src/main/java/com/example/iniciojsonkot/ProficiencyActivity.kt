package com.example.iniciojsonkot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Switch
import kotlinx.android.synthetic.main.create_character_1.*
import kotlinx.android.synthetic.main.proficiency_selector.*


class ProficiencyActivity : AppCompatActivity() {
    //private lateinit var viewOfLayout: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.proficiency_selector)
        val viewOfLayout =  findViewById<LinearLayout>(R.id.proficiencies)

        var stats = listOf("Strength", "Dexterity", "Intelligence", "Constitution", "Wisdom", "Charisma")


        for (stat in stats) {
            viewOfLayout.findViewWithTag<Switch>(stat).setOnClickListener {
                var switchChecked = viewOfLayout.findViewWithTag<Switch>(stat).isChecked
                run {
                    if (switchChecked) {
                        switchChecked = true
                        Global.tempSaves.add(stat)
                    } else {
                        switchChecked = false
                        Global.tempSaves.remove(stat)
                    }
                }
            }
        }

        var skills = listOf("Athletics", "Acrobatics", "Sleight", "Stealth", "Arcana",
            "History", "Investigation", "Religion", "Animal", "Insight", "Medicine", "Perception", "Survival",
            "Deception", "Intimidation", "Performance", "Persuasion")

        for (s in skills) {
            viewOfLayout.findViewWithTag<Switch>(s).setOnClickListener {
                var switchChecked = viewOfLayout.findViewWithTag<Switch>(s).isChecked
                run {
                    if (switchChecked) {
                        switchChecked = true
                        Global.tempProfs.add(s)
                    } else {
                        switchChecked = false
                        Global.tempProfs.remove(s)
                    }

                }
            }
        }

        backbutton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                finish()
            }
        })

        exitButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                Global.loadedCharacter.savesProficiencies.addAll(Global.tempSaves)
                Global.loadedCharacter.skillsProficiencies.addAll(Global.tempProfs)
                val intent = Intent("finish_activity")
                sendBroadcast(intent)
                finish()
                Global.loadedCharacter.createJson(applicationContext)
                val mainIntent = Intent(this@ProficiencyActivity, MainActivity::class.java)
                startActivity(mainIntent)
            }
        })



    }
    override fun onResume() {
        super.onResume()

        val viewOfLayout =  findViewById<LinearLayout>(R.id.proficiencies)

        var stats = listOf("Strength", "Dexterity", "Intelligence", "Constitution", "Wisdom", "Charisma")

        var skills = listOf(
            "Athletics", "Acrobatics", "Sleight", "Stealth", "Arcana",
            "History", "Investigation", "Religion", "Animal", "Insight", "Medicine", "Perception", "Survival",
            "Deception", "Intimidation", "Performance", "Persuasion"
        )

        for(skill in skills){

            if(Global.tempProfs.contains(skill))
                viewOfLayout.findViewWithTag<Switch>(skill).isChecked = true


        }
        for(skill in stats){

            if(Global.tempSaves.contains(skill))
                viewOfLayout.findViewWithTag<Switch>(skill).isChecked = true
        }

    }

}
