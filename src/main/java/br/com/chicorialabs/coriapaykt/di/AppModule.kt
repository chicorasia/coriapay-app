package br.com.chicorialabs.coriapaykt.di

import br.com.chicorialabs.coriapaykt.AppDatabase
import br.com.chicorialabs.coriapaykt.repository.TransacaoRepository
import br.com.chicorialabs.coriapaykt.repository.TransacaoRepositoryImpl
import br.com.chicorialabs.coriapaykt.service.ApiService
import br.com.chicorialabs.coriapaykt.service.RetrofitService
import br.com.chicorialabs.coriapaykt.ui.ajustes.AjustesViewModel
import br.com.chicorialabs.coriapaykt.ui.componente.ComponenteViewModel
import br.com.chicorialabs.coriapaykt.ui.home.HomeViewModel
import br.com.chicorialabs.coriapaykt.ui.login.LoginViewModel
import br.com.chicorialabs.coriapaykt.ui.pagar.PagarViewModel
import br.com.chicorialabs.coriapaykt.ui.transacao.TransacaoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PagarViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { AjustesViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { ComponenteViewModel() }
    viewModel { TransacaoViewModel(get()) }
}

val serviceModule = module {
    single { RetrofitService.criarService<ApiService>() }
}

val repositoryModule = module {
    single<TransacaoRepository> { TransacaoRepositoryImpl(get(), get()) }
}

val daoModule = module {
    single { AppDatabase.getInstance(androidContext()).transacaoDAO() }
}