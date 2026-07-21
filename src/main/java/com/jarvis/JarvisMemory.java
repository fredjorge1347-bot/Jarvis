package com.jarvis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class JarvisMemory extends SQLiteOpenHelper {
    public JarvisMemory(Context context) { super(context, "jarvis_memory.db", null, 1); }
    @Override public void onCreate(SQLiteDatabase db) { db.execSQL("CREATE TABLE memories(id INTEGER PRIMARY KEY AUTOINCREMENT, command TEXT NOT NULL, response TEXT NOT NULL, created_at INTEGER NOT NULL)"); }
    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
    public void save(String command, String response) {
        SQLiteDatabase db = getWritableDatabase();
        android.content.ContentValues values = new android.content.ContentValues();
        values.put("command", command);
        values.put("response", response);
        values.put("created_at", System.currentTimeMillis());
        db.insert("memories", null, values);
    }
}
