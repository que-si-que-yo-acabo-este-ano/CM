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
                //val modifyStatsIntent = Intent(context, ModifyStatsActivity::class.java)
                //startActivity(modifyStatsIntent)

                // Mover este código al lugar donde se cargue el personaje seleccionado
                Global.tempSaves.addAll(Global.loadedCharacter.savesProficiencies)
                Global.tempProfs.addAll(Global.loadedCharacter.skillsProficiencies)
                // Quitar este intent y dejar el que está comentado arriba
                val pruebaIntent = Intent(context, CreateCharacter::class.java)
                startActivity(pruebaIntent)
            }
        })

        viewOfLayout.speedNum.text = Global.loadedCharacter.speed.toString()
        viewOfLayout.proficiencyNum.text = "+" + Global.loadedCharacter.proficiencyBonus.toString()

        return viewOfLayout
    }

    override fun onResume() {
        super.onResume()
        viewOfLayout.strTotal.text = Global.loadedCharacter.strength.toString()
        viewOfLayout.dexTotal.text = Global.loadedCharacter.dexterity.toString()
        viewOfLayout.conTotal.text = Global.loadedCharacter.constitution.toString()
        viewOfLayout.intTotal.text = Global.loadedCharacter.intelligence.toString()
        viewOfLayout.wisTotal.text = Global.loadedCharacter.wisdom.toString()
        viewOfLayout.chaTotal.text = Global.loadedCharacter.charisma.toString()

        var tempStr = calcMod(Global.loadedCharacter.strength)
        var tempDex = calcMod(Global.loadedCharacter.dexterity)
        var tempCon = calcMod(Global.loadedCharacter.constitution)
        var tempInt = calcMod(Global.loadedCharacter.intelligence)
        var tempWis = calcMod(Global.loadedCharacter.wisdom)
        var tempCha = calcMod(Global.loadedCharacter.charisma)

        viewOfLayout.strMod.text = if (tempStr >= 0) "+" + tempStr.toString() else tempStr.toString()
        viewOfLayout.dexMod.text = if (tempDex >= 0) "+" + tempDex.toString() else tempDex.toString()
        viewOfLayout.conMod.text = if (tempCon >= 0) "+" + tempCon.toString() else tempCon.toString()
        viewOfLayout.intMod.text = if (tempInt >= 0) "+" + tempInt.toString() else tempInt.toString()
        viewOfLayout.wisMod.text = if (tempWis >= 0) "+" + tempWis.toString() else tempWis.toString()
        viewOfLayout.chaMod.text = if (tempCha >= 0) "+" + tempCha.toString() else tempCha.toString()

        if (Global.loadedCharacter.savesProficiencies.contains("Strength"))
            tempStr += Global.loadedCharacter.proficiencyBonus

        if (Global.loadedCharacter.savesProficiencies.contains("Dexterity"))
            tempDex += Global.loadedCharacter.proficiencyBonus

        if (Global.loadedCharacter.savesProficiencies.contains("Constitution"))
            tempCon += Global.loadedCharacter.proficiencyBonus

        if (Global.loadedCharacter.savesProficiencies.contains("Intelligence"))
            tempInt += Global.loadedCharacter.proficiencyBonus

        if (Global.loadedCharacter.savesProficiencies.contains("Wisdom"))
            tempWis += Global.loadedCharacter.proficiencyBonus

        if (Global.loadedCharacter.savesProficiencies.contains("Charisma"))
            tempCha += Global.loadedCharacter.proficiencyBonus

        viewOfLayout.strSave.text = if (tempStr >= 0) "+" + tempStr.toString() else tempStr.toString()
        viewOfLayout.dexSave.text = if (tempDex >= 0) "+" + tempDex.toString() else tempDex.toString()
        viewOfLayout.conSave.text = if (tempCon >= 0) "+" + tempCon.toString() else tempCon.toString()
        viewOfLayout.intSave.text = if (tempInt >= 0) "+" + tempInt.toString() else tempInt.toString()
        viewOfLayout.wisSave.text = if (tempWis >= 0) "+" + tempWis.toString() else tempWis.toString()
        viewOfLayout.chaSave.text = if (tempCha >= 0) "+" + tempCha.toString() else tempCha.toString()


        var tempPerception = calcMod(Global.loadedCharacter.wisdom) + 10
        if (Global.loadedCharacter.skillsProficiencies.contains("Perception")){
            if (Global.loadedCharacter.skillsExpertise.contains("Perception")){
                tempPerception += Global.loadedCharacter.proficiencyBonus * 2
            }else{
                tempPerception += Global.loadedCharacter.proficiencyBonus
            }
        }
        viewOfLayout.perceptionNum.text = tempPerception.toString()
        viewOfLayout.initiativeNum.text = "+" + calcMod(Global.loadedCharacter.dexterity)


    }


    private fun calcMod(x: Int): Int{
        return floor((x.toDouble()-10)/2).toInt()
    }

}