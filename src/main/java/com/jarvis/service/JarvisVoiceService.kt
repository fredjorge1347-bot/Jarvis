package com.jarvis.service

import android.app.*
import android.content.Intent
import android.os.IBinder
import android.speech.tts.TextToSpeech
import androidx.core.app.NotificationCompat
import com.jarvis.R
import java.util.Locale

class JarvisVoiceService : Service(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    override fun onCreate() { super.onCreate(); tts = TextToSpeech(this, this); createChannel(); startForeground(42, notification("Escuta autorizada pronta")) }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int { speak(intent?.getStringExtra("say") ?: "JARVIS está disponível em segundo plano."); return START_STICKY }
    override fun onBind(intent: Intent?): IBinder? = null
    override fun onDestroy() { tts?.shutdown(); super.onDestroy() }
    override fun onInit(status: Int) { if (status == TextToSpeech.SUCCESS) tts?.language = Locale("pt", "BR") }
    private fun speak(text: String) { tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "jarvis-response") }
    private fun createChannel() { getSystemService(NotificationManager::class.java).createNotificationChannel(NotificationChannel("jarvis_voice", "JARVIS Voice", NotificationManager.IMPORTANCE_LOW)) }
    private fun notification(text: String): Notification = NotificationCompat.Builder(this, "jarvis_voice").setSmallIcon(R.drawable.ic_jarvis).setContentTitle("JARVIS ativo").setContentText(text).setOngoing(true).build()
}
