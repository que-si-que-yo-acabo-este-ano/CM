package com.example.iniciojsonkot

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.floor
import android.content.Intent
import android.os.AsyncTask
import android.view.View
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.URL
import com.example.iniciojsonkot.Global.Companion.basicitems
import com.example.iniciojsonkot.Global.Companion.items
import com.example.iniciojsonkot.Global.Companion.spells


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Carga de datos
        loadDataFromGit()



        editText2.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }
                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    var stat: Int
                    if (s.any()){
                        stat = Integer . parseInt (s.toString())
                    }else{
                        stat = 0
                    }
                    if (stat < 10) {
                        textView8.setText(calcMod(stat))
                    }else{
                        textView8.setText("+" + calcMod(stat))
                    }
                }
            })

        editText3.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }
                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    var stat: Int
                    if (s.any()){
                        stat = Integer . parseInt (s.toString())
                    }else{
                        stat = 0
                    }
                    if (stat < 10) {
                        textView9.setText(calcMod(stat))
                    }else{
                        textView9.setText("+" + calcMod(stat))
                    }
                }
            })

        editText4.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }
                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    var stat: Int
                    if (s.any()){
                        stat = Integer . parseInt (s.toString())
                    }else{
                        stat = 0
                    }
                    if (stat < 10) {
                        textView15.setText(calcMod(stat))
                    }else{
                        textView15.setText("+" + calcMod(stat))
                    }
                }
            })

        editText5.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }
                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    var stat: Int
                    if (s.any()){
                        stat = Integer . parseInt (s.toString())
                    }else{
                        stat = 0
                    }
                    if (stat < 10) {
                        textView16.setText(calcMod(stat))
                    }else{
                        textView16.setText("+" + calcMod(stat))
                    }
                }
            })

        editText6.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }
                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    var stat: Int
                    if (s.any()){
                        stat = Integer . parseInt (s.toString())
                    }else{
                        stat = 0
                    }
                    if (stat < 10) {
                        textView17.setText(calcMod(stat))
                    }else{
                        textView17.setText("+" + calcMod(stat))
                    }
                }
            })

        editText7.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }
                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    var stat: Int
                    if (s.any()){
                        stat = Integer . parseInt (s.toString())
                    }else{
                        stat = 0
                    }
                    if (stat < 10) {
                        textView18.setText(calcMod(stat))
                    }else{
                        textView18.setText("+" + calcMod(stat))
                    }
                }
            })


        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                startActivity(Intent(this@MainActivity, Main2Activity::class.java))
            }
        })



    }





    fun calcMod(x: Int): String{
        return floor((x.toDouble()-10)/2).toString()
    }


    class DownloadGit : AsyncTask<String, Void, JsonObject>(){
        override fun doInBackground(vararg params: String?): JsonObject {
            val gitJson = URL(params[0]).readText()
            val stringBuilder = StringBuilder(gitJson)
            val parser = Parser.default()
            return parser.parse(stringBuilder) as JsonObject
        }

        override fun onPostExecute(result: JsonObject?) {
            super.onPostExecute(result)
        }
    }

    fun loadDataFromGit(){
        spells = DownloadGit().execute("https://raw.githubusercontent.com/TheGiddyLimit/TheGiddyLimit.github.io/master/data/spells/spells-phb.json").get()
        basicitems = DownloadGit().execute("https://raw.githubusercontent.com/TheGiddyLimit/TheGiddyLimit.github.io/master/data/basicitems.json").get()
        items = DownloadGit().execute("https://raw.githubusercontent.com/TheGiddyLimit/TheGiddyLimit.github.io/master/data/items.json").get()


        /*val file = File("prueba.json")
        file.createNewFile()
        File("prueba.json").writeText("{}")*/
        val prueba = openFileOutput("dondeEsta.json", Context.MODE_PRIVATE)
        prueba.use {
            prueba.write("DONDE ESTÁS ==========================================================".toByteArray())
        }
        val fileInputStream = openFileInput("dondeEsta.json")
        val inputStream = InputStreamReader(fileInputStream)
        val buffered = BufferedReader(inputStream)
        val stringBuilder = StringBuilder()

        stringBuilder.append(buffered.readText())
        println("==========")
        println(stringBuilder.toString())
    }


}
