package eu.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    private val CHANNEL_ID: String = "1"
    private var button: Button? = null
    private var times = 0
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById<Button>(R.id.button)
        button?.setOnClickListener(View.OnClickListener {
            this.times++
            button?.setText( times.toString())
            if(times == 5){
                sendNotification()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendNotification() {
        //creating an intent such that when the notifications is clicked it will open the app in the mainActivity section
        val intent = Intent(this,MainActivity::class.java )
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0 )

        //creating a channel
        val notificationChannel = NotificationChannel(CHANNEL_ID, "IMP1", NotificationManager.IMPORTANCE_DEFAULT)
        //getting the notificaction service
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as  NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)


        //creating and setting a notification to the notification Service

        // In order to create a notification objet we need to use the nested class "notification.builder"
        // and set all the features we want
        val notificationBuilder = Notification.Builder(this.applicationContext, CHANNEL_ID)
        notificationBuilder
            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
            .setContentTitle("Title")
            .setContentText("This the content of the notification")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        val notificationManagerCompat = NotificationManagerCompat.from(this.applicationContext)

        //then we invoke the method build and we obtain a notification objet
        val notification = notificationBuilder.build()

        // then we use the method notify() from the objet notificationManagerCompat to sent the notification to the status bar
        notificationManagerCompat.notify(1, notification)




    }


}