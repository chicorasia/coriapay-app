package br.com.chicorialabs.picpayclonekt.di

import br.com.chicorialabs.picpayclonekt.AppDatabase
import br.com.chicorialabs.picpayclonekt.repository.TransacaoRepository
import br.com.chicorialabs.picpayclonekt.repository.TransacaoRepositoryImpl
import br.com.chicorialabs.picpayclonekt.service.ApiService
import br.com.chicorialabs.picpayclonekt.service.RetrofitService
import br.com.chicorialabs.picpayclonekt.ui.ajustes.AjustesViewModel
import br.com.chicorialabs.picpayclonekt.ui.componente.ComponenteViewModel
import br.com.chicorialabs.picpayclonekt.ui.home.HomeViewModel
import br.com.chicorialabs.picpayclonekt.ui.login.LoginViewModel
import br.com.chicorialabs.picpayclonekt.ui.pagar.PagarViewModel
import br.com.chicorialabs.picpayclonekt.ui.transacao.TransacaoViewModel
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