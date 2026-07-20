package com.jarvis.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "memories")
data class Memory(@PrimaryKey(autoGenerate = true) val id: Long = 0, val kind: String, val content: String, val importance: Int = 1, val createdAt: Long = System.currentTimeMillis())

@Dao
interface MemoryDao {
    @Query("SELECT * FROM memories ORDER BY importance DESC, createdAt DESC LIMIT :limit") fun observeRecent(limit: Int = 50): Flow<List<Memory>>
    @Query("SELECT * FROM memories WHERE content LIKE '%' || :query || '%' ORDER BY importance DESC, createdAt DESC") suspend fun search(query: String): List<Memory>
    @Insert suspend fun insert(memory: Memory): Long
}

@Database(entities = [Memory::class], version = 1)
abstract class JarvisDatabase : RoomDatabase() { abstract fun memoryDao(): MemoryDao }
