package eu.example.notification

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat

class DismissReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManagerCompat = NotificationManagerCompat.from(context!!)
        notificationManagerCompat.cancel(1)
    }

}
