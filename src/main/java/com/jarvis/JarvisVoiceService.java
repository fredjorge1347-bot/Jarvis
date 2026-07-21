package com.jarvis;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class JarvisVoiceService extends Service implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    @Override public void onCreate() { super.onCreate(); tts = new TextToSpeech(this, this); createChannel(); startForeground(42, notification("JARVIS ativo")); }
    @Override public int onStartCommand(Intent intent, int flags, int startId) { speak(intent != null ? intent.getStringExtra("say") : null); return START_STICKY; }
    @Override public IBinder onBind(Intent intent) { return null; }
    @Override public void onDestroy() { if (tts != null) tts.shutdown(); super.onDestroy(); }
    @Override public void onInit(int status) { if (status == TextToSpeech.SUCCESS) tts.setLanguage(new Locale("pt", "BR")); }
    private void speak(String text) { if (tts != null && text != null) tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "jarvis-response"); }
    private void createChannel() { getSystemService(NotificationManager.class).createNotificationChannel(new NotificationChannel("jarvis_voice", "JARVIS Voice", NotificationManager.IMPORTANCE_LOW)); }
    private Notification notification(String text) { return new Notification.Builder(this, "jarvis_voice").setSmallIcon(R.drawable.ic_jarvis).setContentTitle("JARVIS").setContentText(text).setOngoing(true).build(); }
}
