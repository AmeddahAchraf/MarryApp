package com.example.zawaj1

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var bmp: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView.setOnClickListener(loadPersonnalPhoto)

        btn_add.setOnClickListener({
            notif();
        })
    }


    fun notif() {
        val notificationManager =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel("1", "notif", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(mChannel)
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

            val noti = Notification.Builder(this, "1")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(txt_name.text)
                .setContentText(txt_bibio.text)
                .setLargeIcon(bmp)
                .addAction(R.drawable.ic_launcher_background, "Intéressé", pendingIntent)
                .addAction(R.drawable.ic_launcher_background, "non Intéressé", pendingIntent)
                .build()

            notificationManager.notify(0, noti)
        }

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
