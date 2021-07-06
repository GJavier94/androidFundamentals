package eu.example.sendsms

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private var message: String? = null
    private var phone: String? = null
    private var buttonSend: Button? = null
    private var editTextPhone: EditText? = null
    private var editTextMessage: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getComponentsFromView()
        buttonSend?.setOnClickListener(View.OnClickListener {
            message = editTextMessage?.text.toString()
            phone = editTextPhone?.text.toString()
            if(checkAppPermisions()){
                Log.i("had permissions ", "sending the message")
                sendSMS(this.message!!, this.phone!!)
            }
        })
    }

    private fun checkAppPermisions():Boolean {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            Log.i("checkPermisions", "not Granted... asking for permissions")
            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.SEND_SMS) ,  1)

            return false;
        }
        return true
    }


    private fun sendSMS(message: String, phone: String) {
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(phone, null ,    message, null , null)
    }


    private fun getComponentsFromView() {
        editTextMessage = findViewById<EditText>(R.id.editTextMessage)
        editTextPhone = findViewById<EditText>(R.id.editTextPhone)
        buttonSend = findViewById<Button>(R.id.buttonSend)
    }

    @Override
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if(requestCode == 1  && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            sendSMS(this.message!!, this.phone!!)

        }
    }

}