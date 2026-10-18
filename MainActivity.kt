package com.naol.privateapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.app.Activity

class MainActivity : Activity() {
    private lateinit var status: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestNotificationPermissionIfNeeded()

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(42, 48, 42, 32)
            setBackgroundColor(0xFF0B1020.toInt())
        }

        val title = TextView(this).apply {
            text = "NAOL PRIVATE"
            textSize = 28f
            setTextColor(0xFF65D6FF.toInt())
        }
        val subtitle = TextView(this).apply {
            text = "Personal phone protection"
            textSize = 16f
            setTextColor(0xFFE7ECF5.toInt())
            setPadding(0, 8, 0, 24)
        }
        status = TextView(this).apply {
            text = "Protection is OFF"
            textSize = 17f
            setTextColor(0xFFFFFFFF.toInt())
            setPadding(0, 8, 0, 24)
        }
        val start = Button(this).apply {
            text = "START CHARGING GUARD"
            setOnClickListener {
                val intent = Intent(this@MainActivity, AlarmService::class.java)
                    .setAction(AlarmService.ACTION_START)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) startForegroundService(intent)
                else startService(intent)
                status.text = "Protection is ON. Keep the persistent notification enabled."
                Toast.makeText(this@MainActivity, "Charging guard enabled", Toast.LENGTH_SHORT).show()
            }
        }
        val stop = Button(this).apply {
            text = "STOP PROTECTION"
            setOnClickListener {
                stopService(Intent(this@MainActivity, AlarmService::class.java))
                status.text = "Protection is OFF"
                Toast.makeText(this@MainActivity, "Protection stopped", Toast.LENGTH_SHORT).show()
            }
        }
        val note = TextView(this).apply {
            text = "\\nHow it works\\n• Starts a loud alarm when charging is unplugged.\\n• While charging, movement can trigger the alarm.\\n• Stop the alarm using the notification or this app.\\n\\nLimitations\\nAndroid does not let ordinary apps reliably detect failed lock-screen patterns or silently take selfies in the background. This starter app does not secretly photograph anyone."
            textSize = 14f
            setTextColor(0xFFCAD3E2.toInt())
            setPadding(0, 20, 0, 0)
        }

        layout.addView(title)
        layout.addView(subtitle)
        layout.addView(status)
        layout.addView(start)
        layout.addView(stop)
        layout.addView(note)
        setContentView(layout)
    }

    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT >= 33 &&
            checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 100)
        }
    }
}
