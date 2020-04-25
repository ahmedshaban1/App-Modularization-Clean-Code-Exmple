package com.example.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dao.BlogPostDao
import com.example.local.dao.CategoryDao
import com.example.local.dao.ProductDao
import com.example.local.dao.UserDao
import com.example.model.BlogPostApi
import com.example.model.Category
import com.example.model.Product
import com.example.model.User

@Database(entities = [BlogPostApi::class,Category::class,Product::class,User::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun blogPostDao(): BlogPostDao
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao


    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "appdatabase.db"
            ).build()

    }
}