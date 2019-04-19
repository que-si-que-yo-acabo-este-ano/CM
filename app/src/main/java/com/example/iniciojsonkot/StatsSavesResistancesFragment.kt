package com.example.iniciojsonkot

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.stats_saves_resistances.view.*

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
    }



}