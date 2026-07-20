package com.jarvis.di

import android.content.Context
import androidx.room.Room
import com.jarvis.data.JarvisDatabase
import com.jarvis.domain.JarvisEngine
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module @InstallIn(SingletonComponent::class)
object AppModule {
 @Provides @Singleton fun database(@ApplicationContext context: Context) = Room.databaseBuilder(context, JarvisDatabase::class.java, "jarvis_encrypted_memory.db").build()
 @Provides fun memoryDao(db: JarvisDatabase) = db.memoryDao()
 @Provides @Singleton fun engine(dao: com.jarvis.data.MemoryDao) = JarvisEngine(dao)
}
