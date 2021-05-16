package com.engineerskasa.crypto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.engineerskasa.crypto.data.viewmodel.CryptoCurrencyVM
import com.engineerskasa.crypto.data.viewmodel.CryptoCurrencyVMFactory
import com.engineerskasa.crypto.databinding.ActivityMainBinding
import com.engineerskasa.crypto.utils.Constants.Companion.LIMIT
import com.engineerskasa.crypto.utils.Constants.Companion.OFFSET
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var cryptoCurrencyVMFactory: CryptoCurrencyVMFactory
    lateinit var cryptoCurrencyVM: CryptoCurrencyVM
    lateinit var binding: ActivityMainBinding
    var currentPage = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        AndroidInjection.inject(this)
        cryptoCurrencyVM = ViewModelProvider(this, cryptoCurrencyVMFactory).get(CryptoCurrencyVM::class.java)

        loadData()
        cryptoCurrencyVM.cryptoCurrencyResults.observe(this, Observer {
            if (it != null) {
                Log.e("ZSH", "onCreate: $it" )
            }
        })

        cryptoCurrencyVM.cryptocurrenciesError.observe(this, Observer {
            if (it != null) {
                Toast.makeText(this, resources.getString(R.string.cryptocurrency_error_message) + it,
                    Toast.LENGTH_SHORT).show()
            }
        })

        cryptoCurrencyVM.cryptocurrenciesLoader.observe(this, Observer {
            if (it == false) binding.progressBar.visibility = View.GONE
        })
    }

    private fun loadData() {
        cryptoCurrencyVM.loadCryptoCurrencies(LIMIT, currentPage * OFFSET)
        currentPage++
    }

    override fun onDestroy() {
        super.onDestroy()
        cryptoCurrencyVM.disposeElements()
    }
}