package com.engineerskasa.crypto.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.engineerskasa.crypto.data.repository.CryptoCurrencyRepository
import com.engineerskasa.crypto.model.CryptoCurrency
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CryptoCurrencyVM @Inject constructor(
    private val repository: CryptoCurrencyRepository
) : ViewModel() {
    var cryptoCurrencyResults: MutableLiveData<List<CryptoCurrency>> = MutableLiveData()
    var cryptocurrenciesError: MutableLiveData<String> = MutableLiveData()
    var cryptocurrenciesLoader: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var disposableObserver: DisposableObserver<List<CryptoCurrency>>

    fun cryptocurrenciesResult(): LiveData<List<CryptoCurrency>> {
        return cryptoCurrencyResults
    }
    fun cryptocurrenciesError(): LiveData<String> {
        return cryptocurrenciesError
    }
    fun cryptocurrenciesLoader(): LiveData<Boolean> {
        return cryptocurrenciesLoader
    }

    fun loadCryptoCurrencies(limit: Int, offset: Int ) {
        disposableObserver = object : DisposableObserver<List<CryptoCurrency>>() {
            override fun onNext(cryptocurrencies: List<CryptoCurrency>) {
                cryptoCurrencyResults.postValue(cryptocurrencies)
                cryptocurrenciesLoader.postValue(false)
            }

            override fun onError(e: Throwable) {
                cryptocurrenciesError.postValue(e.message)
                cryptocurrenciesLoader.postValue(false)
            }

            override fun onComplete() {
            }
        }

        repository.getCryptoCurrencies(limit, offset)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(disposableObserver)
    }

    fun disposeElements(){
        if(!disposableObserver.isDisposed) disposableObserver.dispose()
    }
}