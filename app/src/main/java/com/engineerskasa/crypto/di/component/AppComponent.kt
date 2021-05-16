package com.engineerskasa.crypto.di.component

import com.engineerskasa.crypto.CryptoCurrencyApplication
import com.engineerskasa.crypto.di.modules.AppModule
import com.engineerskasa.crypto.di.modules.BuildersModule
import com.engineerskasa.crypto.di.modules.NetModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, BuildersModule::class, AppModule::class, NetModule::class])
interface AppComponent {
    fun inject(application: CryptoCurrencyApplication)
}