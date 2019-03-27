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
import kotlinx.android.synthetic.main.activity_main.view.*


class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


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
        val textViewPrueba = TextView(this)
        val paramsPrueba : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        paramsPrueba.setMargins(50,20,50,0)
        textViewPrueba.layoutParams = paramsPrueba

        textViewPrueba.tag = tx.text.toString() + "Son"
        textViewPrueba.text = "Created from " + tx.text
        textViewPrueba.textSize = 25f
        textViewPrueba.setPadding(30,10,0,10)
        textViewPrueba.setBackgroundColor(Color.YELLOW)

        linLay.addView(textViewPrueba, linLay.indexOfChild(tx)+1)

    }

}
