package com.example.plumbingstore.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.plumbingstore.data.model.Product

@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
} 