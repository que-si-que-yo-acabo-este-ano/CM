package com.example.dnd

import android.app.ActionBar
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.nio.charset.Charset
import android.view.ViewGroup
import android.widget.ScrollView
import kotlinx.android.synthetic.main.activity_main.view.*


class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)/*
        val scrollable = ScrollView(this)
        val scrollParams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        scrollable.layoutParams = scrollParams
        scrollable.addView(linLay)*/


        val textArray = arrayOf("One", "Two", "Three", "Four")


        button2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                linLay.removeAllViewsInLayout()
                for (i in textArray.indices) {
                    val textView = TextView(v.context)

                    val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

                    params.setMargins(50,20,50,0)
                    textView.layoutParams = params

                    textView.tag = textArray[i]
                    textView.text = textArray[i]
                    textView.textSize = 25f
                    textView.setPadding(30,10,0,10)
                    textView.setBackgroundColor(Color.GREEN)

                    textView.setOnClickListener(object : View.OnClickListener{
                        override fun onClick(v: View) {
                            prueba(textView)
                        }
                    })

                    linLay.addView(textView)
                }
            }
        })


    }

    fun prueba(tx:TextView){
        val descLay = LinearLayout(this)
        descLay.setBackgroundColor(Color.CYAN)
        descLay.orientation = LinearLayout.VERTICAL

        val paramsPrueba : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsPrueba.setMargins(60,20,20,20)

        val textViewPrueba = TextView(this)
        textViewPrueba.layoutParams = paramsPrueba
        textViewPrueba.text = "Casting time:\n 1 action"
        textViewPrueba.textSize = 16f
        textViewPrueba.setPadding(30,10,10,10)
        textViewPrueba.setBackgroundColor(Color.LTGRAY)

        val paramsPrueba2 : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsPrueba2.setMargins(20,20,20,20)

        val textViewPrueba2 = TextView(this)
        textViewPrueba2.layoutParams = paramsPrueba2
        textViewPrueba2.text = "Range:\n60 feet"
        textViewPrueba2.textSize = 16f
        textViewPrueba2.setPadding(30,10,10,10)
        textViewPrueba2.setBackgroundColor(Color.LTGRAY)

        val paramsPrueba3 : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsPrueba3.setMargins(20,20,20,20)

        val textViewPrueba3 = TextView(this)
        textViewPrueba3.layoutParams = paramsPrueba3
        textViewPrueba3.text = "Components:\n V,S,M"
        textViewPrueba3.textSize = 16f
        textViewPrueba3.setPadding(30,10,10,10)
        textViewPrueba3.setBackgroundColor(Color.LTGRAY)

        val paramsPrueba4 : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsPrueba4.setMargins(20,20,20,20)

        val textViewPrueba4 = TextView(this)
        textViewPrueba4.layoutParams = paramsPrueba4
        textViewPrueba4.text = "Duration:\ninstantaneous"
        textViewPrueba4.textSize = 16f
        textViewPrueba4.setPadding(30,10,10,10)
        textViewPrueba4.setBackgroundColor(Color.LTGRAY)


        val horizLay = LinearLayout(this)
        horizLay.orientation = LinearLayout.HORIZONTAL
        horizLay.addView(textViewPrueba)
        horizLay.addView(textViewPrueba2)
        horizLay.addView(textViewPrueba3)
        horizLay.addView(textViewPrueba4)


        val paramsDesc : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsDesc.setMargins(50,15,50,30)

        val textViewDescription = TextView(this)
        textViewDescription.layoutParams = paramsDesc
        textViewDescription.text = "You hurl a bubble of acid. Choose one creature within range, or choose two creatures within range that are within 5 feet of each other. A target must succeed on a Dexterity saving throw or take 1d6 acid damage.This spell's damage increases by 1d6 when you reach 5th Level (2d6), 11th level (3d6) and 17th level (4d6)."
        textViewDescription.textSize = 16f
        textViewDescription.setPadding(30,10,10,10)
        textViewDescription.setBackgroundColor(Color.WHITE)



//        val addToVertical = layoutInflater.inflate(R.layout.empty, horizLay)
        descLay.addView(horizLay)
        descLay.addView(textViewDescription)


/*        val to_add = layoutInflater.inflate(R.layout.empty,descLay)
        to_add.setTag("prueba" + tx.text.toString()) */
        descLay.setTag("prueba" + tx.text.toString())
        val prueba = linLay.findViewWithTag<View>("prueba" + tx.text.toString())

        if(prueba == null){
            linLay.addView(descLay, linLay.indexOfChild(tx)+1)
        }else{
            linLay.removeViewAt(linLay.indexOfChild(prueba))
        }




    }

}
