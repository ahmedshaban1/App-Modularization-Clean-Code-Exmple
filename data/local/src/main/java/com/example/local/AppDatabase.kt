package com.example.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dao.BlogPostDao
import com.example.model.BlogPost

@Database(entities = [BlogPost::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun blogPostDao(): BlogPostDao

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "appdatabase.db"
            ).build()

    }
}