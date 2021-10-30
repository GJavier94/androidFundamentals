package eu.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
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
    private val RC_MAIN_ACTIVITY = 0
    private val RC_BC_ACTION = 1
    private val RC_BC_DISMISS = 2

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
        // This notification has 3 main components
            // 1st intent: such that when the notifications is clicked it will open the app in the mainActivity section
            //2nd intent: for the open action button when clicked a toast message is displayed by using a broadcast receiver
            //3rd intent: for the dismiss action button when clicked the notification is closed

        //1 intent
        val intent = Intent(this,MainActivity::class.java )
        val pendingIntent = PendingIntent.getActivity(this, RC_MAIN_ACTIVITY, intent, 0 )

        //2nd intent
        val intentAction = Intent(this, Receiver::class.java)
        intentAction.putExtra("toast", "This a toast message")

        val pendingActionIntent = PendingIntent.getBroadcast(this, RC_BC_ACTION,intentAction, 0 )
        val notificationAction = Notification.Action.Builder( Notification.Action(R.drawable.ic_baseline_notifications_active_24, "Toast message", pendingActionIntent)).build()

        //3rd intent
        val intentDismiss = Intent(this, DismissReceiver::class.java)
        val pendingDismissAction = PendingIntent.getBroadcast(this, RC_BC_DISMISS, intentDismiss, 0  )
        val notificationActionDismiss = Notification.Action(R.drawable.ic_baseline_notifications_active_24, "Dismiss", pendingDismissAction)


        //creating a channel
        val notificationChannel = NotificationChannel(CHANNEL_ID, "IMP1", NotificationManager.IMPORTANCE_DEFAULT)
        //getting the notificaction service
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as  NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)


        //creating a bitmap
        val icon = BitmapFactory.decodeResource(this.resources, R.drawable.android)
        val text = this.resources.getString(R.string.big_text)

        //creating and setting a notification to the notification Service

        // In order to create a notification objet we need to use the nested class "notification.builder"
        // and set all the features we want
        val notificationBuilder = Notification.Builder(this.applicationContext, CHANNEL_ID)
        notificationBuilder
            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
            .setContentTitle("Title")
            .setContentText("This the content of the notification")
            .setContentIntent(pendingIntent)
            .addAction(notificationAction)
            .addAction(notificationActionDismiss)
            .setAutoCancel(true)
            .setColor(Color.BLUE) // the color displayed on the bell icon and the letters
            .setLargeIcon(icon) //icon displayed in the not...
            //.setStyle(Notification.BigPictureStyle().bigPicture(icon))
            .setStyle(Notification.BigTextStyle().bigText(text)) // big text get from the strings xml

        val notificationManagerCompat = NotificationManagerCompat.from(this.applicationContext)

        //then we invoke the method build and we obtain a notification objet
        val notification = notificationBuilder.build()

        // then we use the method notify() from the objet notificationManagerCompat to sent the notification to the status bar
        notificationManagerCompat.notify(1, notification)




    }


}