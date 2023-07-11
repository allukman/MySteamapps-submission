package com.karsatech.steamapps.core.data.source.local.room

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.karsatech.steamapps.core.data.source.local.entity.SteamEntity

@Database(entities = [SteamEntity::class], version = 1, exportSchema = false)
abstract class SteamDatabase : RoomDatabase() {

    abstract fun steamDao(): SteamDao

    companion object {
        @Volatile
        private var INSTANCE: SteamDatabase? = null

        fun getInstance(context: Context): SteamDatabase =
            INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                SteamDatabase::class.java,
                "Steam.db"
            )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            instance
        }
    }
}