package br.com.chicorialabs.picpayclonekt.di

import br.com.chicorialabs.picpayclonekt.ui.dashboard.DashboardViewModel
import br.com.chicorialabs.picpayclonekt.ui.home.HomeViewModel
import br.com.chicorialabs.picpayclonekt.ui.login.LoginViewModel
import br.com.chicorialabs.picpayclonekt.ui.notifications.NotificationsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DashboardViewModel() }
    viewModel { HomeViewModel() }
    viewModel { NotificationsViewModel() }
    viewModel { LoginViewModel() }
}