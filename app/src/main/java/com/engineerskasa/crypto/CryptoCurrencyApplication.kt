package com.engineerskasa.crypto

import android.app.Application
import com.engineerskasa.crypto.di.component.DaggerAppComponent
import com.engineerskasa.crypto.di.modules.AppModule
import com.engineerskasa.crypto.di.modules.NetModule
import com.engineerskasa.crypto.utils.Constants
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class CryptoCurrencyApplication(): Application(), HasAndroidInjector {
    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule(Constants.BASE_URL))
            .build()
            .inject(this)
    }
}