package com.example.iniciojsonkot

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class SpellView: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spell_view)
        val labelComponents = findViewById(R.id.prueba) as TextView
        val labelMaterial = findViewById(R.id.material) as TextView
        labelComponents.text = intent.getStringExtra("components")
        labelMaterial.text = intent.getStringExtra("material")
    }
}