package com.engineerskasa.crypto.di.modules

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.engineerskasa.crypto.data.database.CryptoDB
import com.engineerskasa.crypto.data.database.CryptoDao
import com.engineerskasa.crypto.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                // Change the table name to the correct one
                database.execSQL("ALTER TABLE cryptocurrency RENAME TO cryptocurrencies")
            }
        }
    }
    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideCryptoDB(application: Application): CryptoDB
    = Room.databaseBuilder(application, CryptoDB::class.java, "crypto_db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideCryptoDao(database: CryptoDB): CryptoDao = database.cryptoDao()

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(application)
}