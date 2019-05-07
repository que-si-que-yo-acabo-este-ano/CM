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

        viewOfLayout.speedNum.text = CharacterTemp.speed.toString()
        viewOfLayout.proficiencyNum.text = "+" + CharacterTemp.proficiencyBonus.toString()

        return viewOfLayout
    }

    override fun onResume() {
        super.onResume()
        viewOfLayout.strTotal.text = CharacterTemp.strength.toString()
        viewOfLayout.dexTotal.text = CharacterTemp.dexterity.toString()
        viewOfLayout.conTotal.text = CharacterTemp.constitution.toString()
        viewOfLayout.intTotal.text = CharacterTemp.intelligence.toString()
        viewOfLayout.wisTotal.text = CharacterTemp.wisdom.toString()
        viewOfLayout.chaTotal.text = CharacterTemp.charisma.toString()

        var tempStr = calcMod(CharacterTemp.strength)
        var tempDex = calcMod(CharacterTemp.dexterity)
        var tempCon = calcMod(CharacterTemp.constitution)
        var tempInt = calcMod(CharacterTemp.intelligence)
        var tempWis = calcMod(CharacterTemp.wisdom)
        var tempCha = calcMod(CharacterTemp.charisma)

        viewOfLayout.strMod.text = if (tempStr >= 0) "+" + tempStr.toString() else tempStr.toString()
        viewOfLayout.dexMod.text = if (tempDex >= 0) "+" + tempDex.toString() else tempDex.toString()
        viewOfLayout.conMod.text = if (tempCon >= 0) "+" + tempCon.toString() else tempCon.toString()
        viewOfLayout.intMod.text = if (tempInt >= 0) "+" + tempInt.toString() else tempInt.toString()
        viewOfLayout.wisMod.text = if (tempWis >= 0) "+" + tempWis.toString() else tempWis.toString()
        viewOfLayout.chaMod.text = if (tempCha >= 0) "+" + tempCha.toString() else tempCha.toString()

        if (CharacterTemp.savesProficiencies.contains("Strength"))
            tempStr += CharacterTemp.proficiencyBonus

        if (CharacterTemp.savesProficiencies.contains("Dexterity"))
            tempDex += CharacterTemp.proficiencyBonus

        if (CharacterTemp.savesProficiencies.contains("Constitution"))
            tempCon += CharacterTemp.proficiencyBonus

        if (CharacterTemp.savesProficiencies.contains("Intelligence"))
            tempInt += CharacterTemp.proficiencyBonus

        if (CharacterTemp.savesProficiencies.contains("Wisdom"))
            tempWis += CharacterTemp.proficiencyBonus

        if (CharacterTemp.savesProficiencies.contains("Charisma"))
            tempCha += CharacterTemp.proficiencyBonus

        viewOfLayout.strSave.text = if (tempStr >= 0) "+" + tempStr.toString() else tempStr.toString()
        viewOfLayout.dexSave.text = if (tempDex >= 0) "+" + tempDex.toString() else tempDex.toString()
        viewOfLayout.conSave.text = if (tempCon >= 0) "+" + tempCon.toString() else tempCon.toString()
        viewOfLayout.intSave.text = if (tempInt >= 0) "+" + tempInt.toString() else tempInt.toString()
        viewOfLayout.wisSave.text = if (tempWis >= 0) "+" + tempWis.toString() else tempWis.toString()
        viewOfLayout.chaSave.text = if (tempCha >= 0) "+" + tempCha.toString() else tempCha.toString()


        var tempPerception = calcMod(CharacterTemp.wisdom) + 10
        if (CharacterTemp.skillsProficiencies.contains("Perception")){
            if (CharacterTemp.skillsExpertise.contains("Perception")){
                tempPerception += CharacterTemp.proficiencyBonus * 2
            }else{
                tempPerception += CharacterTemp.proficiencyBonus
            }
        }
        viewOfLayout.perceptionNum.text = tempPerception.toString()
        viewOfLayout.initiativeNum.text = "+" + calcMod(CharacterTemp.dexterity)


    }


    private fun calcMod(x: Int): Int{
        return floor((x.toDouble()-10)/2).toInt()
    }

}