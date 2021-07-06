 package eu.example.palindrome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var INPUT_EMPTY = ""
    private var texto = ""
    private var SUCCESS = ""
    private var FAILURE = ""

    private var FirstClicked = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        INPUT_EMPTY =  getString(R.string.INPUT_EMPTY)
        SUCCESS = getString(R.string.SUCCESS)
        FAILURE = getString(R.string.FAILURE)

        var btn = findViewById(R.id.button) as Button

    }

    fun evaluatePossiblePalindrome(view: View){
        var textoEdit = findViewById(R.id.editText) as EditText
        var resuTextView = findViewById(R.id.textView3) as TextView
        var phrase = textoEdit.text

        //validating text input
        var cleanInput:String = phrase.trim().toString()
        cleanInput = cleanInput.replace(" ","")
        if(cleanInput.isEmpty()){
            resuTextView.text = INPUT_EMPTY
            return
        }
        cleanInput = cleanInput.lowercase()

        val flag:Boolean = evaluatePalindrome(cleanInput)
        if(flag) resuTextView.text = SUCCESS
        else resuTextView.text = FAILURE

    }

    private fun evaluatePalindrome(cleanInput: String): Boolean {

        for( i in 0 until (cleanInput.length/2) ){
            var a = cleanInput[i]
            var b = cleanInput[ (cleanInput.length - 1) - i ]
            if( cleanInput[i] != cleanInput[ (cleanInput.length - 1) - i ]) return false
        }
        return true
    }

    fun reset(view: View) {
        if(FirstClicked){
            (view as TextView).text = ""
            FirstClicked = false
        }

    }
}