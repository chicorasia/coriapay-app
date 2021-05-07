package br.com.chicorialabs.picpayclonekt.di

import br.com.chicorialabs.picpayclonekt.ui.componente.ComponenteViewModel
import br.com.chicorialabs.picpayclonekt.ui.pagar.PagarViewModel
import br.com.chicorialabs.picpayclonekt.ui.home.HomeViewModel
import br.com.chicorialabs.picpayclonekt.ui.login.LoginViewModel
import br.com.chicorialabs.picpayclonekt.ui.ajustes.AjustesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PagarViewModel() }
    viewModel { HomeViewModel() }
    viewModel { AjustesViewModel() }
    viewModel { LoginViewModel() }
    viewModel { ComponenteViewModel() }
}