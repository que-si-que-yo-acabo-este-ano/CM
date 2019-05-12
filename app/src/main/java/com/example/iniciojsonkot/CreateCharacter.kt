package com.example.iniciojsonkot

import android.app.AlertDialog
import android.content.*
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.create_character_1.*
import kotlinx.android.synthetic.main.create_character_1.editCha
import kotlinx.android.synthetic.main.create_character_1.editCon
import kotlinx.android.synthetic.main.create_character_1.editDex
import kotlinx.android.synthetic.main.create_character_1.editInt
import kotlinx.android.synthetic.main.create_character_1.editStr
import kotlinx.android.synthetic.main.create_character_1.editWis
import kotlinx.android.synthetic.main.create_character_1.view.*
import kotlinx.android.synthetic.main.modify_stats_layout.*
import kotlin.math.floor

class CreateCharacter : AppCompatActivity(), AdapterView.OnItemSelectedListener{

    val listOfClasses = arrayOf("Bard","Cleric","Druid","Paladin","Ranger","Sorcerer","Warlock","Wizard")
    val listOfRaces = arrayOf("Dwarf","Elf","Halfling","Human","Dragonborn","Gnome","Half-Elf","Half-Orc","Tiefling")

    var spinnerClass: Spinner? = null
    var spinnerRace: Spinner? = null
    var selectedClasses : MutableMap<String,Int> = mutableMapOf()

    val broadcast_reciever = object : BroadcastReceiver() {

        override fun onReceive(arg0: Context, intent: Intent) {
            val action = intent.action
            if (action == "finish_activity") {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_character_1)
        Global.loadedCharacter = Character("","",0,0,0,0,mutableMapOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),
            mutableListOf(mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf()),
            mutableListOf(mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf(),mutableSetOf()),
            10,10,10,10,10,10)
        registerReceiver(broadcast_reciever, IntentFilter("finish_activity"))

        spinnerClass = this.classSpinner
        spinnerClass!!.setOnItemSelectedListener(this)
        val arrayAdapterClasses = ArrayAdapter(this,android.R.layout.simple_spinner_item,listOfClasses)
        arrayAdapterClasses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerClass!!.setAdapter(arrayAdapterClasses)

        spinnerRace = this.raceSpinner
        spinnerRace!!.setOnItemSelectedListener(this)
        val arrayAdapterRaces = ArrayAdapter(this,android.R.layout.simple_spinner_item,listOfRaces)
        arrayAdapterRaces.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRace!!.setAdapter(arrayAdapterRaces)

        nameInput.validate("Name required"){s-> (s.length>=2)}
        speedInput.validate("Speed required"){s-> (s!="")}
        hpInput.validate("HP required"){s-> (s!="")}
        acInput.validate("AC required"){s-> (s!="")}


        addClassButton.setOnClickListener (object : View.OnClickListener {
            override fun onClick(v: View) {
                val classView = LinearLayout(v.context)
                classView.orientation = LinearLayout.HORIZONTAL

                val classEmptySpace1 = TextView(v.context)
                classEmptySpace1.text=" "
                classEmptySpace1.textSize=24.toFloat()
                val classEmptySpace2 = TextView(v.context)
                classEmptySpace2.text=" "
                classEmptySpace2.textSize=24.toFloat()

                val className = TextView(v.context)
                className.setText(classSpinner.selectedItem.toString())
                className.textSize=24.toFloat()

                val classLevel = EditText(v.context)

                classLevel.minEms = 2
                classLevel.gravity = Gravity.CENTER_HORIZONTAL
                classLevel.inputType = InputType.TYPE_CLASS_NUMBER
                classLevel.setText("1")
                classLevel.textSize=24.toFloat()
                //classLevel.width=25

                selectedClasses.put(classSpinner.selectedItem.toString(),1)
                classLevel.addTextChangedListener(
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
                            var level: Int
                            if (s.any()){
                                level = Integer . parseInt (s.toString())
                                selectedClasses.put(classSpinner.selectedItem.toString(),level)
                            }
                        }
                    })


                classLevel.validate("Level required"){s-> (s!="")}


                classView.addView(className)
                classView.addView(classEmptySpace1)
                classView.addView(classLevel)
                classView.addView(classEmptySpace2)
                classView.setTag(className.text)

                val alreadyExists = classesList.findViewWithTag<View>(className.text)

                if(alreadyExists == null){
                    classesList.addView(classView)
                }

                val removeClass = TextView(v.context)
                removeClass.setBackgroundColor(Color.parseColor("#86AC41"))
                removeClass.text = "Remove"
                removeClass.setOnClickListener(object : View.OnClickListener{
                    override fun onClick(w: View) {
                        selectedClasses.remove(className.text)
                        classesList.removeView(classesList.findViewWithTag<View>(className.text))
                    }
                })

                classView.addView(removeClass)


            }
        })

        editStr.addTextChangedListener(
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
                        strMod.setText(calcMod(stat))
                    }else{
                        strMod.setText("+" + calcMod(stat))
                    }
                }
            })

        editDex.addTextChangedListener(
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
                        dexMod.setText(calcMod(stat))
                    }else{
                        dexMod.setText("+" + calcMod(stat))
                    }
                }
            })

        editCon.addTextChangedListener(
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
                        conMod.setText(calcMod(stat))
                    }else{
                        conMod.setText("+" + calcMod(stat))
                    }
                }
            })

        editInt.addTextChangedListener(
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
                        intMod.setText(calcMod(stat))
                    }else{
                        intMod.setText("+" + calcMod(stat))
                    }
                }
            })

        editWis.addTextChangedListener(
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
                        wisMod.setText(calcMod(stat))
                    }else{
                        wisMod.setText("+" + calcMod(stat))
                    }
                }
            })

        editCha.addTextChangedListener(
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
                        chaMod.setText(calcMod(stat))
                    }else{
                        chaMod.setText("+" + calcMod(stat))
                    }
                }
            })

        nextButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                // Inicializar CharacterTemp.spellsKnown
                Global.loadedCharacter.spellsKnown = mutableListOf()
                for(i in 0..9){
                    var set:MutableSet<String> = mutableSetOf()
                    Global.loadedCharacter.spellsKnown.add(i,set)
                }

                var emptyData : String = ""

                if (nameInput.text.toString().length >= 1){
                    println(nameInput.text.toString().length)
                    Global.loadedCharacter.name = nameInput.text.toString()
                }else{
                    emptyData = emptyData.plus("Name\n")
                }

                if (raceSpinner.selectedItem.toString().length >= 1){
                    Global.loadedCharacter.race = raceSpinner.selectedItem.toString()
                }else{
                    emptyData = emptyData.plus("Race\n")
                }

                if (selectedClasses.size >= 1){
                    Global.loadedCharacter.classes = selectedClasses
                }else{
                    emptyData = emptyData.plus("Classes\n")
                }

                if (speedInput.text.toString().isNotEmpty()){
                    Global.loadedCharacter.speed = Integer.parseInt(speedInput.text.toString())
                }else{
                    emptyData = emptyData.plus("Speed\n")
                }

                if (hpInput.text.toString().isNotEmpty()){
                    Global.loadedCharacter.maxHealth = Integer.parseInt(hpInput.text.toString())
                }else{
                    emptyData = emptyData.plus("MaxHP\n")
                }

                if (acInput.text.toString().isNotEmpty()){
                    Global.loadedCharacter.armor = Integer.parseInt(acInput.text.toString())
                }else{
                    emptyData = emptyData.plus("Armor\n")
                }

                if (editStr.text.toString().isNotEmpty()){
                    Global.loadedCharacter.strength = Integer.parseInt(editStr.text.toString())
                }else{
                    emptyData = emptyData.plus("Strength\n")
                }

                if (editDex.text.toString().isNotEmpty()){
                    Global.loadedCharacter.dexterity = Integer.parseInt(editDex.text.toString())
                }else{
                    emptyData = emptyData.plus("Dexterity\n")
                }

                if (editCon.text.toString().isNotEmpty()){
                    Global.loadedCharacter.constitution = Integer.parseInt(editCon.text.toString())
                }else{
                    emptyData = emptyData.plus("Constitution\n")
                }

                if (editInt.text.toString().isNotEmpty()){
                    Global.loadedCharacter.intelligence = Integer.parseInt(editInt.text.toString())
                }else{
                    emptyData = emptyData.plus("Intelligence\n")
                }

                if (editWis.text.toString().isNotEmpty()){
                    Global.loadedCharacter.wisdom = Integer.parseInt(editWis.text.toString())
                }else{
                    emptyData = emptyData.plus("Wisdom\n")
                }

                if (editCha.text.toString().isNotEmpty()){
                    Global.loadedCharacter.charisma = Integer.parseInt(editCha.text.toString())
                }else{
                    emptyData = emptyData.plus("Charisma\n")
                }



                if (emptyData.length < 1){
                    val profsIntent = Intent(this@CreateCharacter, ProficiencyActivityAfterCreateCharacter::class.java)
                    startActivity(profsIntent)
                }else{
                    showDialog(emptyData)
                }

                //finish()
            }
        })

        cancelButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                finish()
            }
        })

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }


    fun calcMod(x: Int): String{
        return floor((x.toDouble()-10)/2).toString()
    }

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                afterTextChanged.invoke(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })
    }

    fun EditText.validate(message: String, validator: (String) -> Boolean) {
        this.afterTextChanged {
            this.error = if (validator(it)) null else message
        }
        this.error = if (validator(this.text.toString())) null else message
    }


    private fun showDialog(emptyData: String){
        // Late initialize an alert dialog object
        lateinit var dialog: AlertDialog

        // Initialize a new instance of alert dialog builder object
        val builder = AlertDialog.Builder(this)

        // Set a title for alert dialog
        //builder.setTitle("Delete")

        // Set a message for alert dialog
        builder.setMessage("These inputs can't be empty: \n" + emptyData)

        // On click listener for dialog buttons
        val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
            when(which){
                DialogInterface.BUTTON_NEUTRAL -> dialog.dismiss()
            }
        }
        // Set the alert dialog OK button
        builder.setNeutralButton("OK",dialogClickListener)

        // Initialize the AlertDialog using builder object
        dialog = builder.create()

        // Finally, display the alert dialog
        dialog.show()
    }
}