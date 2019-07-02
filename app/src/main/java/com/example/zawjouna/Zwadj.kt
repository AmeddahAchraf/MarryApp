package com.example.zawjouna

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class Zwadj : Application() {

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANEL_ID,
                "Zwadj Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            notificationChannel.description = "Zwadj"
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(notificationChannel)
        }
    }

    companion object{
        val NOTIFICATION_CHANEL_ID = "NOTIFICATION"
    }
}