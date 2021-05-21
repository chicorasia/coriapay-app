package br.com.chicorialabs.picpayclonekt

import android.app.Application
import br.com.chicorialabs.picpayclonekt.di.repositoryModule
import br.com.chicorialabs.picpayclonekt.di.serviceModule
import br.com.chicorialabs.picpayclonekt.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(viewModelModule, serviceModule, repositoryModule)
        }
    }

}