package br.com.chicorialabs.coriapaykt

import android.app.Application
import br.com.chicorialabs.coriapaykt.di.daoModule
import br.com.chicorialabs.coriapaykt.di.repositoryModule
import br.com.chicorialabs.coriapaykt.di.serviceModule
import br.com.chicorialabs.coriapaykt.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(
                viewModelModule,
                serviceModule,
                repositoryModule,
                daoModule
            )
        }
    }

}