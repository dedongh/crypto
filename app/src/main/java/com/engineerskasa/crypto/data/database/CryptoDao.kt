package com.engineerskasa.crypto.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.engineerskasa.crypto.model.CryptoCurrency
import io.reactivex.Single

@Dao
interface CryptoDao {
    @Query("SELECT * FROM cryptocurrency ORDER BY rank limit :limit offset :offset")
    fun queryCryptocurrencies(limit:Int, offset:Int): Single<List<CryptoCurrency>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertCryptocurrency(cryptocurrency: CryptoCurrency)

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertAllCryptocurrencies(cryptocurrencies: List<CryptoCurrency>)
}