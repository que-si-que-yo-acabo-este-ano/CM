package com.example.iniciojsonkot

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.modify_stats_layout.view.*
import kotlinx.android.synthetic.main.skills_points.view.*
import kotlinx.android.synthetic.main.stats_saves_resistances.*
import kotlinx.android.synthetic.main.stats_saves_resistances.view.*
import kotlin.math.floor

class SkillsPointsFragment : Fragment() {
    private lateinit var viewOfLayout: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewOfLayout = inflater!!.inflate(R.layout.skills_points, container, false)

        viewOfLayout.button4.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val modifySkillsIntent = Intent(context, ProficiencyActivity::class.java)
                startActivity(modifySkillsIntent)
            }
        })

        return viewOfLayout
    }

    override fun onResume() {
        super.onResume()

        var tempStr = calcMod(Global.loadedCharacter.strength)
        var tempDex = calcMod(Global.loadedCharacter.dexterity)
        var tempCon = calcMod(Global.loadedCharacter.constitution)
        var tempInt = calcMod(Global.loadedCharacter.intelligence)
        var tempWis = calcMod(Global.loadedCharacter.wisdom)
        var tempCha = calcMod(Global.loadedCharacter.charisma)

        var strSkills = listOf("Athletics")
        calSkill(strSkills, tempStr)

        var dexSkills = listOf("Acrobatics","Sleight","Stealth","Thieves")
        calSkill(dexSkills, tempDex)

        var intSkills = listOf("Arcana","History","Investigation","Nature","Religion")
        calSkill(intSkills, tempInt)

        var wisSkills = listOf("Animal","Insight","Medicine","Perception","Survival")
        calSkill(wisSkills, tempWis)

        var chaSkills = listOf("Deception","Intimidation","Performance","Persuasion")
        calSkill(chaSkills, tempCha)

    }

    private fun calSkill(x: List<String>, y: Int){
        for(skill in x){
            var mod = y
            if(Global.loadedCharacter.skillsProficiencies.contains(skill))
                mod += Global.loadedCharacter.proficiencyBonus

            if(Global.loadedCharacter.skillsExpertise.contains(skill))
                mod += Global.loadedCharacter.proficiencyBonus

            viewOfLayout.findViewWithTag<TextView>(skill).text = if (mod >= 0) "+" + mod.toString() else mod.toString()
        }

    }

    private fun calcMod(x: Int): Int{
        return floor((x.toDouble()-10)/2).toInt()
    }

}