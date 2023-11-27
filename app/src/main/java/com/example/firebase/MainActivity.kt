package com.example.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val channelId = "TEST NOTIF"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dokja.setOnClickListener {

            val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_IMMUTABLE
            } else {
                0
            }
            val intent = Intent(
                this,
                NotifReceiver::class.java
            ).putExtra("MESSAGE", "Baca selengkapnya ...")
            val pendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                intent,
                flag
            )

            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notif)
                .setContentTitle("Character")
//                .setContentText("hello ada sira, andro, dan fina disini. apa kabar semua?")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(0, "Further Information", pendingIntent)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText("Hi!, this is Kim Dok Ja, Let's having adventure with me!!")
                )

            val notifManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notifChannel = NotificationChannel(
                    channelId,
                    "Character",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                with(notifManager) {
                    createNotificationChannel(notifChannel)
                    notify(0, builder.build())
                }
            } else {
                notifManager.notify(0, builder.build())
            }

            Toast.makeText(this, "You Choose Kim Dok Ja!!", Toast.LENGTH_SHORT).show()
        }
        binding.joohyuk.setOnClickListener {
            val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_IMMUTABLE

            } else {
                0
            }
            val intent = Intent(
                this,
                NotifReceiver::class.java
            ).putExtra("MESSAGE", "Further Information ...")
            val pendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                intent,
                flag,


            )

            val image = BitmapFactory.decodeResource(resources, R.drawable.anime)
            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notif)
                .setContentTitle("Character")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(0, "Baca Notif", pendingIntent)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText("update terbaru!")
                )
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(image)
                        .bigLargeIcon(null)
                )

            val notifManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notifChannel = NotificationChannel(
                    channelId,
                    "Notification Test",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                with(notifManager) {
                    createNotificationChannel(notifChannel)
                    notify(0, builder.build())
                }
            } else {
                notifManager.notify(0, builder.build())
            }

            Toast.makeText(this, "You Choose Nam Joo Hyuk!!", Toast.LENGTH_SHORT).show()

        }
    }
}