package eu.example.dialogfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    internal var textView_resultDialog: TextView? = null
    private var btn_acmain_showdf: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_acmain_showdf  =findViewById<Button>(R.id.acmain_btn_showdf)
        textView_resultDialog = findViewById<TextView>(R.id.act_main_resDialog)
        btn_acmain_showdf?.setOnClickListener{
            val fragmentManager = supportFragmentManager

            var dialogFragment = FragmentoDialogo()
            dialogFragment.show(fragmentManager, "FragmentoDialogo")

        }

    }
}