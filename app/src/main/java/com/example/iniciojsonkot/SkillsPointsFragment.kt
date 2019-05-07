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

        var tempStr = calcMod(CharacterTemp.strength)
        var tempDex = calcMod(CharacterTemp.dexterity)
        var tempCon = calcMod(CharacterTemp.constitution)
        var tempInt = calcMod(CharacterTemp.intelligence)
        var tempWis = calcMod(CharacterTemp.wisdom)
        var tempCha = calcMod(CharacterTemp.charisma)

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
            if(CharacterTemp.skillsProficiencies.contains(skill))
                mod += CharacterTemp.proficiencyBonus

            if(CharacterTemp.skillsExpertise.contains(skill))
                mod += CharacterTemp.proficiencyBonus

            viewOfLayout.findViewWithTag<TextView>(skill).text = if (mod >= 0) "+" + mod.toString() else mod.toString()
        }

    }

    private fun calcMod(x: Int): Int{
        return floor((x.toDouble()-10)/2).toInt()
    }

}