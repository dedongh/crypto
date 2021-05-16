package com.engineerskasa.crypto.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.engineerskasa.crypto.model.CryptoCurrency
import java.util.concurrent.locks.Lock

@Database(entities = [CryptoCurrency::class],  version = 1, exportSchema = false)
abstract class CryptoDB: RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao

  /*  companion object {
        @Volatile
        private var instance: CryptoDB? = null
        private val Lock = Any()

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
        CryptoDB::class.java, "crypto_db")
            .fallbackToDestructiveMigration()
            .build()

        operator fun invoke(context: Context) = instance ?: synchronized(Lock){
            instance ?: buildDatabase(context).also { instance = it }
        }
    }*/
}