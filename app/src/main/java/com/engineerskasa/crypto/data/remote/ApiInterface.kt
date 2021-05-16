package com.engineerskasa.crypto.data.remote

import com.engineerskasa.crypto.model.CryptoCurrency
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("ticker/")
    fun getCryptocurrencies(@Query("start") start: String): Observable<List<CryptoCurrency>>

}