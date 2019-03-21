package com.example.dnd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlin.math.floor

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun calcMod(x: Int): Int{
        return floor((x.toDouble()-10)/2).toInt()
    }
}
