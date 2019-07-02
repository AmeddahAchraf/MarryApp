package com.example.zawaj1

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.view.View
import android.widget.Toast
import com.example.zawjouna.R
import com.example.zawjouna.Zwadj
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val ACTION_ZWADJ = "com.example.zawjouna.Zwadj"
    }

    var bmp: Bitmap? = null
    private lateinit var notificationManager : NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView.setOnClickListener(loadPersonnalPhoto)
        notificationManager = NotificationManagerCompat.from(this)
        btn_add.setOnClickListener({
            notif();
        })
    }


    fun notif() {

        val interestedIntent = Intent()
        interestedIntent.setComponent(ComponentName("com.example.exercice2avanc","com.example.exercice2avanc.ZwadjReciever"))
        interestedIntent.setAction(ACTION_ZWADJ)
        interestedIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
//        this.sendBroadcast(interestedIntent)

        interestedIntent.putExtra("actionInterested","Intersse")
        val interestedPendingIntent: PendingIntent = PendingIntent.getBroadcast(this,0,interestedIntent,PendingIntent.FLAG_UPDATE_CURRENT)

        val notInterestedIntent = Intent()
        notInterestedIntent.putExtra("actionNotInterested","NOT")
        notInterestedIntent.setComponent(ComponentName("com.example.exercice2avanc","com.example.exercice2avanc.ZwadjReciever"))
        notInterestedIntent.setAction(ACTION_ZWADJ)
        notInterestedIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
//        this.sendBroadcast(notInterestedIntent)
        val notInterestedPendingIntent: PendingIntent = PendingIntent.getBroadcast(this,1,notInterestedIntent,PendingIntent.FLAG_UPDATE_CURRENT)


        val notification = NotificationCompat.Builder(this, Zwadj.NOTIFICATION_CHANEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(txt_name.text)
            .setContentText(txt_bibio.text)
            .setLargeIcon(bmp)
            .setAutoCancel(true)
            .addAction(R.mipmap.ic_launcher,"Interesse",interestedPendingIntent)
            .addAction(R.mipmap.ic_launcher,"Non interesse",notInterestedPendingIntent)
            .build()




//            val noti = Notification.Builder(this, "1")
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle(txt_name.text)
//                .setContentText(txt_bibio.text)
//                .setLargeIcon(bmp)
//                .setContentIntent(pendingIntent)
//                .addAction(R.drawable.ic_launcher_background, "Intéressé", pendingIntent)
//                .addAction(R.drawable.ic_launcher_background, "non Intéressé", pendingIntent)
//                .build()

            notificationManager.notify(0, notification)


    }


    private val loadPersonnalPhoto: View.OnClickListener = View.OnClickListener {
        var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(i, 147)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 147) {
            bmp = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(bmp)
        }
    }

}
