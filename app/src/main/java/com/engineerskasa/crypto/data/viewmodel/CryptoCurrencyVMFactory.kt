package com.engineerskasa.crypto.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class CryptoCurrencyVMFactory @Inject constructor(private val cryptoCurrencyVM: CryptoCurrencyVM): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CryptoCurrencyVM::class.java)) {
            return cryptoCurrencyVM as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}