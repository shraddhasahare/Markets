package com.example.markets.baseUtils.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.markets.search.CommonDataCaching

// Non-encrypted or plain Database
@Database(
    entities = [CommonDataCaching::class], version = 1, exportSchema = false
)
abstract class BMarketsDatabase : RoomDatabase() {

    abstract fun recentSearchDao(): RecentSearchDao

    companion object {
        const val DATABASE_NAME = "b_markets_db"

        @Volatile
        private var INSTANCE: BMarketsDatabase? = null
        fun getAppDataBase(context: Context): BMarketsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BMarketsDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}