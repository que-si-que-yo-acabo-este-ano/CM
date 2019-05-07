package com.example.iniciojsonkot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Switch


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
                        CharacterTemp.savesProficiencies.add(stat)
                    } else {
                        switchChecked = false
                        CharacterTemp.savesProficiencies.remove(stat)
                    }
                }
            }
        }

        var skills = listOf("Athletics", "Acrobatics", "Sleight", "Stealth", "Thieves", "Arcana",
            "History", "Investigation", "Religion", "Animal", "Insight", "Medicine", "Perception", "Survival",
            "Deception", "Intimidation", "Performance", "Persuasion")

        for (s in skills) {
            viewOfLayout.findViewWithTag<Switch>(s).setOnClickListener {
                var switchChecked = viewOfLayout.findViewWithTag<Switch>(s).isChecked
                run {
                    if (switchChecked) {
                        switchChecked = true
                        CharacterTemp.skillsProficiencies.add(s)
                    } else {
                        switchChecked = false
                        CharacterTemp.skillsProficiencies.remove(s)
                    }

                }
            }
        }
    }
    override fun onResume() {
        super.onResume()

        val viewOfLayout =  findViewById<LinearLayout>(R.id.proficiencies)

        var stats = listOf("Strength", "Dexterity", "Intelligence", "Constitution", "Wisdom", "Charisma")

        var skills = listOf(
            "Athletics", "Acrobatics", "Sleight", "Stealth", "Thieves", "Arcana",
            "History", "Investigation", "Religion", "Animal", "Insight", "Medicine", "Perception", "Survival",
            "Deception", "Intimidation", "Performance", "Persuasion"
        )

        for(skill in skills){

            if(CharacterTemp.skillsProficiencies.contains(skill))
                viewOfLayout.findViewWithTag<Switch>(skill).isChecked = true


        }
        for(skill in stats){

            if(  CharacterTemp.savesProficiencies.contains(skill))
                viewOfLayout.findViewWithTag<Switch>(skill).isChecked = true
        }

    }

}
