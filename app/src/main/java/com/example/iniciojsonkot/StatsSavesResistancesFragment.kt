package com.example.iniciojsonkot

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.stats_saves_resistances.view.*
import kotlin.math.floor

class StatsSavesResistancesFragment : Fragment() {
    private lateinit var viewOfLayout: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewOfLayout = inflater!!.inflate(R.layout.stats_saves_resistances, container, false)

        viewOfLayout.button3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val modifyStatsIntent = Intent(context, ModifyStatsActivity::class.java)
                startActivity(modifyStatsIntent)
            }
        })

        viewOfLayout.speedNum.text = Character.speed.toString()
        viewOfLayout.proficiencyNum.text = "+" + Character.proficiencyBonus.toString()





        return viewOfLayout
    }

    override fun onResume() {
        super.onResume()
        viewOfLayout.strTotal.text = Character.strength.toString()
        viewOfLayout.dexTotal.text = Character.dexterity.toString()
        viewOfLayout.conTotal.text = Character.constitution.toString()
        viewOfLayout.intTotal.text = Character.intelligence.toString()
        viewOfLayout.wisTotal.text = Character.wisdom.toString()
        viewOfLayout.chaTotal.text = Character.charisma.toString()

        var tempStr = calcMod(Character.strength)
        var tempDex = calcMod(Character.dexterity)
        var tempCon = calcMod(Character.constitution)
        var tempInt = calcMod(Character.intelligence)
        var tempWis = calcMod(Character.wisdom)
        var tempCha = calcMod(Character.charisma)

        viewOfLayout.strMod.text = if (tempStr >= 0) "+" + tempStr.toString() else tempStr.toString()
        viewOfLayout.dexMod.text = if (tempDex >= 0) "+" + tempDex.toString() else tempDex.toString()
        viewOfLayout.conMod.text = if (tempCon >= 0) "+" + tempCon.toString() else tempCon.toString()
        viewOfLayout.intMod.text = if (tempInt >= 0) "+" + tempInt.toString() else tempInt.toString()
        viewOfLayout.wisMod.text = if (tempWis >= 0) "+" + tempWis.toString() else tempWis.toString()
        viewOfLayout.chaMod.text = if (tempCha >= 0) "+" + tempCha.toString() else tempCha.toString()

        if (Character.savesProficiencies.contains("Strength"))
            tempStr += Character.proficiencyBonus

        if (Character.savesProficiencies.contains("Dexterity"))
            tempDex += Character.proficiencyBonus

        if (Character.savesProficiencies.contains("Constitution"))
            tempCon += Character.proficiencyBonus

        if (Character.savesProficiencies.contains("Intelligence"))
            tempInt += Character.proficiencyBonus

        if (Character.savesProficiencies.contains("Wisdom"))
            tempWis += Character.proficiencyBonus

        if (Character.savesProficiencies.contains("Charisma"))
            tempCha += Character.proficiencyBonus

        viewOfLayout.strSave.text = if (tempStr >= 0) "+" + tempStr.toString() else tempStr.toString()
        viewOfLayout.dexSave.text = if (tempDex >= 0) "+" + tempDex.toString() else tempDex.toString()
        viewOfLayout.conSave.text = if (tempCon >= 0) "+" + tempCon.toString() else tempCon.toString()
        viewOfLayout.intSave.text = if (tempInt >= 0) "+" + tempInt.toString() else tempInt.toString()
        viewOfLayout.wisSave.text = if (tempWis >= 0) "+" + tempWis.toString() else tempWis.toString()
        viewOfLayout.chaSave.text = if (tempCha >= 0) "+" + tempCha.toString() else tempCha.toString()


        var tempPerception = calcMod(Character.wisdom) + 10
        if (Character.skillsProficiencies.contains("Perception")){
            if (Character.skillsExpertise.contains("Perception")){
                tempPerception += Character.proficiencyBonus * 2
            }else{
                tempPerception += Character.proficiencyBonus
            }
        }
        viewOfLayout.perceptionNum.text = tempPerception.toString()
        viewOfLayout.initiativeNum.text = "+" + calcMod(Character.dexterity)


    }


    private fun calcMod(x: Int): Int{
        return floor((x.toDouble()-10)/2).toInt()
    }

}