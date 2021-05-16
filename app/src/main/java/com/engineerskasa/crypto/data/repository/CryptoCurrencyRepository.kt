package com.engineerskasa.crypto.data.repository

import android.util.Log
import com.engineerskasa.crypto.data.database.CryptoDao
import com.engineerskasa.crypto.data.remote.ApiInterface
import com.engineerskasa.crypto.model.CryptoCurrency
import com.engineerskasa.crypto.utils.Constants.Companion.START_ZERO_VALUE
import com.engineerskasa.crypto.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

class CryptoCurrencyRepository @Inject constructor(
    val apiInterface: ApiInterface,
    val cryptoDao: CryptoDao,
    val utils: Utils
) {
    fun getCryptoCurrencies(limit: Int, offset: Int): Observable<List<CryptoCurrency>> {
        val hasConnection = utils.isConnectedToInternet()
        Log.e("ZSH", "getCryptoCurrencies: $hasConnection")
        var observableFromApi: Observable<List<CryptoCurrency>>? = null
        if (hasConnection) {
            observableFromApi = getCryptoCurrenciesFromApi()
        }
        val observableFromDB = getCryptoCurrenciesFromDB(limit, offset)

        return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDB)
        else observableFromDB
    }

    private fun getCryptoCurrenciesFromDB(
        limit: Int,
        offset: Int
    ): Observable<List<CryptoCurrency>> {
        return cryptoDao.queryCryptocurrencies(limit, offset)
            .toObservable()
            .doOnNext {
                Log.e("REPOSITORY DB *** ", it.size.toString())
            }
    }

    private fun getCryptoCurrenciesFromApi(): Observable<List<CryptoCurrency>> {
        return apiInterface.getCryptocurrencies(START_ZERO_VALUE)
            .doOnNext{
                Log.e("REPOSITORY API * ", it.size.toString())
                for (item in it) {
                    cryptoDao.insertCryptocurrency(item)
                }
            }
    }
}